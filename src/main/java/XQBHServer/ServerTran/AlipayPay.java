package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.AlipayHelper.MyAlipayClient;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import static XQBHServer.Utils.Data.DataUtils.YearString2Date;

/*
支付功能，正常扣款返回AAAAAA，10003返回ZFWAIT，其他则为支付失败
20000时自动调用1次查询，如还是未知，则交易状态为u
登记对账数据表
交易状态JYZT_U
1-完成
w-等待用户支付
c-关闭
x-撤销
t-退货
u-未知
客户端调用，如为ZFWAIT，则需要调用AliPayQuery查询并更新对账数据状态
 */
public class AlipayPay extends Tran {
    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQRCODE = tranObj.getString("QRCODE");
        String sSPMC_U = tranObj.getString("SPMC_U");
        BigDecimal bJYJE_U = tranObj.getBigDecimal("JYJE_U");

        Logger.log(tranObj, "LOG_IO", "sQTDX_U_head=" + sQTDX_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sQTRQ_U_head=" + sQTRQ_U_head);

        Logger.log(tranObj, "LOG_IO", "sQRCODE=" + sQRCODE);
        Logger.log(tranObj, "LOG_IO", "sSPMC_U=" + sSPMC_U);
        Logger.log(tranObj, "LOG_IO", "bJYJE_U=" + bJYJE_U);
        /*==================================codeBegin=====================================*/
        String pay_mode="z";
        //终端号为空则取商户号，说明是在商户控制端做的交易
        if ("kh".equals(sQTDX_U_head))
        {
            sZDBH_U_head=sKHBH_U_head;
        }else if ("sh".equals(sQTDX_U_head))
        {
            Tran.runERR(tranObj, "PAY001");
            return false;
        }else if ("zd".equals(sQTDX_U_head))
        {

        }

        Date dQTRQ_U=YearString2Date(tranObj,sQTRQ_U_head);

        //============================检查系统支付合法性============================
        CXTCSMapper cxtcsMapper = tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setKEY_UU("ALLOWPAY");
        cxtcsKey.setFRDM_U("9999");
        CXTCS cxtcs = null;
        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == cxtcs || !"1".equals(cxtcs.getVALUE_())) {//系统不允许支付，需人工将系统置为正常
            runERR(tranObj, "SYSPAY");
            return false;
        }

        //=================================生成流水============================
        if (Com.getHTLS(tranObj) == false)
            return false;
        String sSFDH_U = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");

        //==============================查询商户信息合法性=========================
        DSHXXMapper dshxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U_head);
        dshxxKey.setFRDM_U("9999");
        DSHXX dshxx = null;
        try {
            dshxx = dshxxMapper.selectByPrimaryKey(dshxxKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshxx) {
            runERR(tranObj, "SH0001");
            return false;
        }
        if (dshxx.getDQYE_U().compareTo(BigDecimal.ZERO) < 0) {
            runERR(tranObj, "SH0002");
            return false;
        }
        if (!"0".equals(dshxx.getJLZT_U())) {
            runERR(tranObj, "SH0003");
            return false;
        }

        //==============================查询商户收款账号=======================
        DSHZHMapper dshzhMapper = tranObj.sqlSession.getMapper(DSHZHMapper.class);
        DSHZHKey dshzhKey = new DSHZHKey();
        dshzhKey.setFRDM_U("9999");
        dshzhKey.setSHBH_U(dshxx.getSHBH_U());
        dshzhKey.setZFZHLX(pay_mode);
        DSHZH dshzh = null;
        try {
            dshzh = dshzhMapper.selectByPrimaryKey(dshzhKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshzh) {
            runERR(tranObj, "ZF0001", pay_mode);
            return false;
        }
        String seller_id = dshzh.getZFZH_U();



        //=============================插入对账数据表===========================
        MDZSJ mdzsj = new MDZSJ();
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        mdzsj.setFRDM_U("9999");
        mdzsj.setHTRQ_U(tranObj.date);
        mdzsj.setHTLS_U(tranObj.getHead("HTLS_U"));
        mdzsj.setJYSJ_U(tranObj.date);
        mdzsj.setHTJYM_(tranObj.getHead("HTJYM_"));
        mdzsj.setJYLX_U("0");
        mdzsj.setJYJE_U(bJYJE_U);
        mdzsj.setKHBH_U(sKHBH_U_head);
        mdzsj.setSHBH_U(sSHBH_U_head);
        mdzsj.setZDBH_U(sZDBH_U_head);
        mdzsj.setZFZH_U(seller_id);
        mdzsj.setZFZHLX(pay_mode);
        mdzsj.setSFDH_U(sSFDH_U);
        mdzsj.setSFLS_U(null);//交易成功后更新
        mdzsj.setSFRQ_U(null);//交易成功后更新
        mdzsj.setFKRID_(null);//交易成功后更新
        mdzsj.setFKRZH_(null);//交易成功后更新
        mdzsj.setQTRQ_U(dQTRQ_U);
        mdzsj.setQTLS_U(tranObj.getHead("QTLS_U"));
        mdzsj.setQTJYM_(tranObj.getHead("QTJYM_"));
        mdzsj.setJYZT_U("u");
        mdzsj.setSPXX_U(sSPMC_U);
        mdzsj.setFKM_UU(sQRCODE);
        mdzsj.setJLZT_U("0");
        try {
            mdzsjMapper.insert(mdzsj);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }


        //===============================组织收款================================
        String goods_category = "";
        String show_url = "";
        String timeout_express = "";
        AlipayClient alipayClient;


        alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppID, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sSFDH_U + "\"," +//我的流水
                "\"scene\":\"bar_code\"," +
                "\"auth_code\":\"" + sQRCODE + "\"," + //付款码
                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
                "\"subject\":\"" + dshxx.getSHMC_U() + "\"," +
                "\"buyer_id\":\"" + sQRCODE + "\"," + //付款码
                "\"seller_id\":\"" + seller_id + "\"," +
                "\"total_amount\":" + bJYJE_U + "," +
                "\"discountable_amount\":0," +
                "\"body\":\"" + "订单描述" + "\"," +
                "\"goods_detail\":[{" +
                "\"goods_id\":\"" + sSPMC_U + "\"," +
                "\"goods_name\":\"" + sSPMC_U + "\"," +
                "\"quantity\":" + 1 + "," +
                "\"price\":" + bJYJE_U + "," +
                "\"goods_category\":\"" + goods_category + "\"," +
                "\"body\":\"" + "商品描述" + "\"," +
                "\"show_url\":\"" + show_url + "\"" +
                "}]," +
                "\"operator_id\":\"" + sZDBH_U_head + "\"," +
                "\"store_id\":\"" + sSHBH_U_head + "\"," +
                "\"terminal_id\":\"" + sZDBH_U_head + "\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"" + Com.alipaySys_service_provider_id + "\"" +
                "}," +
                "\"timeout_express\":\"" + timeout_express + "\"" +
                "}");

        AlipayTradePayResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent(),pay_mode)) {
            runERR(tranObj, "ZF0005");
            return false;
        }
        boolean intsert_flg=true;

        CallResult callResult=new CallResult();
        try {
            response = alipayClient.execute(request);

        } catch (Exception e) {
            //通讯失败或调用异常，标记为未知交易
            String query_result=judgeSuccess(tranObj,sSFDH_U,callResult);
            Logger.log(tranObj,"LOG_DEBUG","query_result="+query_result);
            if(query_result.equals("WAIT_BUYER_PAY"))
            {
                Logger.log(tranObj,"LOG_IO","调用支付异常，通过查询得用户正在支付");
                mdzsj.setJYZT_U("w");
                try {
                    mdzsjMapper.updateByPrimaryKey(mdzsj);
                }catch (Exception ee)
                {
                    Logger.logException(tranObj,"LOG_ERR",ee);
                }
                runERR(tranObj, "ZFWAIT");//前端做一个轮循
                tranObj.commitFlg=true;
                return false;
            }else if(query_result.equals(""))
            {
                Logger.log(tranObj,"LOG_ERR","调用支付异常，通过查询也异常，做未知交易处理，有cancelloop处理，不行有后续手工调账");
                tranObj.commitFlg = true;
                Logger.logException(tranObj, "LOG_ERR", e);
                runERR(tranObj, "ZF0004");
                return false;
            }else if(query_result.equals("TRADE_SUCCESS"))
            {
                Logger.log(tranObj,"LOG_IO","调用支付异常，通过查询得知扣账成功");
                intsert_flg=false;
            }else {
                Logger.log(tranObj,"LOG_IO","调用支付异常，通过查询得知扣账失败");
                runERR(tranObj, "ZF0002");
                return false;
            }

        }
        if (intsert_flg) {
            //完成交易更新报文表，直接插入，报错不返回
            if (true != InsertMJYBWAfterDSF.exec(tranObj, response.getBody(), response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg()))
            {
                //本方插入失败不做处理
                Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
            }

            if (response.isSuccess()) {
                Logger.log(tranObj, "LOG_DEBUG", "调用记账成功");
            } else {
                if ("20000".equals(response.getCode()) || "isp.unknow-error".equals(response.getSubCode()) || "ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                    //查询一次，失败则直接返回
                    String query_result=judgeSuccess(tranObj,sSFDH_U,callResult);
                    Logger.log(tranObj,"LOG_DEBUG","query_result="+query_result);
                    if(query_result.equals("WAIT_BUYER_PAY"))
                    {
                        Logger.log(tranObj,"LOG_IO","调用支付状态未知，通过查询得用户正在支付");
                        mdzsj.setJYZT_U("w");
                        try {
                            mdzsjMapper.updateByPrimaryKey(mdzsj);
                        }catch (Exception ee)
                        {
                            Logger.logException(tranObj,"LOG_ERR",ee);
                        }
                        runERR(tranObj, "ZFWAIT");//前端做一个轮循
                        tranObj.commitFlg=true;
                        return false;
                    }else if(query_result.equals(""))
                    {
                        Logger.log(tranObj,"LOG_ERR","调用支付状态未知，通过查询也异常，做未知交易处理，有cancelloop处理，不行有后续手工调账");
                        tranObj.commitFlg = true;

                        runERR(tranObj, "ZF0004");
                        return false;
                    }else if(query_result.equals("TRADE_SUCCESS"))
                    {
                        Logger.log(tranObj,"LOG_IO","调用支付状态未知，通过查询得知扣账成功");
                    }else {
                        Logger.log(tranObj,"LOG_IO","调用支付状态未知，通过查询得知扣账失败");
                        runERR(tranObj, "ZF0002");
                        return false;
                    }


                } else {
                    Logger.log(tranObj, "LOG_ERR", "调用记账失败");
                    runERR(tranObj, "ZF0002");
                    return false;
                }
            }
            Logger.log(tranObj, "LOG_DEBUG", "response.getCode()=" + response.getCode());
            if ("10003".equals(response.getCode())) {
                Logger.log(tranObj,"LOG_IO","用户正在支付...");
                mdzsj.setJYZT_U("w");
                try {
                    mdzsjMapper.updateByPrimaryKey(mdzsj);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                }
                runERR(tranObj, "ZFWAIT");//前端做一个轮循
                tranObj.commitFlg = true;
                return false;
            } else if (!"10000".equals(response.getCode())) {
                runERR(tranObj, "ZF0003");
                return false;
            }

            mdzsj.setSFLS_U(response.getTradeNo());
            mdzsj.setSFRQ_U(response.getGmtPayment());
            mdzsj.setFKRID_(response.getBuyerUserId());
            mdzsj.setFKRZH_(response.getBuyerLogonId());


        }else {
            mdzsj.setSFLS_U(callResult.getBody().get("SFLS_U"));
            mdzsj.setSFRQ_U(YearString2Date(tranObj,callResult.getBody().get("SFRQ_U")));
            mdzsj.setFKRID_(callResult.getBody().get("FKRID_"));
            mdzsj.setFKRZH_(callResult.getBody().get("FKRZH_"));
        }


        mdzsj.setJYZT_U("1");



        Logger.log(tranObj,"LOG_IO","记账成功");
        try {
            mdzsjMapper.updateByPrimaryKey(mdzsj);
        } catch (Exception e) {
            //不操作，认为成功，需人工核对状态为u的情况
            Logger.logException(tranObj,"LOG_ERR",e);
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }
    public String judgeSuccess(TranObj tranObj,String sSFDH_U,CallResult callResult){
        String result="";
        try {
            Map XMLMapIn = new HashMap();
            Map head = null;
            Map body = new HashMap();
            head=tranObj.getHeadMap();
            head.put("HTJYM_", "AlipayQuery");

            body.put("SFDH_U",sSFDH_U);
            body.put("SFGX_U","0");
            XMLMapIn.put("head",head);
            XMLMapIn.put("body",body);
            boolean call_success=false;
            try {
                if(SystemTran.call(tranObj,XMLMapIn,callResult)!=true) {
                    Logger.log(tranObj, "LOG_ERR", "call failed!");
                    return result;
                }

            } catch (Exception e) {
                Logger.logException(tranObj,"LOG_ERR",e);
                return result;
            }


            result=callResult.getBody().get("TSTAT_").trim();
        }catch (Exception e)
        {
            Logger.logException(tranObj,"LOG_ERR",e);
        }

        return result;
    }

}
