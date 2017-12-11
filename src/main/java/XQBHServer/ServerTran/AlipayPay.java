package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
客户端调用，如为ZFWAIT，则需要调用ZFBQuery查询并更新对账数据状态
 */
public class AlipayPay extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sZDJYM_ = tranObj.getHead("ZDJYM_");
        String sSHBH_U = tranObj.getHead("SHBH_U");
        String sZDBH_U = tranObj.getHead("ZDBH_U");
        String sZFZHLX = tranObj.getString("ZFZHLX");
        String sQRCODE = tranObj.getString("QRCODE");
        String sSPMC_U = tranObj.getString("SPMC_U");
        BigDecimal bJYJE_U = tranObj.getBigDecimal("JYJE_U");

        Logger.log(tranObj, "LOG_IO", "sZDJYM_=" + sZDJYM_);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U=" + sSHBH_U);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U=" + sZDBH_U);
        Logger.log(tranObj, "LOG_IO", "sZFZHLX=" + sZFZHLX);
        Logger.log(tranObj, "LOG_IO", "sQRCODE=" + sQRCODE);
        Logger.log(tranObj, "LOG_IO", "sSPMC_U=" + sSPMC_U);
        Logger.log(tranObj, "LOG_IO", "bJYJE_U=" + bJYJE_U);
        /*==================================codeBegin=====================================*/
        String rqTmp = tranObj.getHead("QTRQ_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dQTRQ_U = null;
        try {
            dQTRQ_U = formatter.parse(rqTmp);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
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
        if (null == cxtcs || !"1".equals(cxtcs.getVALUE_())) {//系统穿参错误，需人工将系统置为正常
            runERR(tranObj, "SYSPAY");
            return false;
        }

        //=================================生成流水============================
        if (Com.getHTLS(tranObj) == false)
            return false;
        String sZFBLS_ = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");

        //==============================查询商户信息合法性=========================
        DSHXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U);
        dshxxKey.setFRDM_U("9999");
        DSHXX dshxx = null;
        try {
            dshxx = dzdxxMapper.selectByPrimaryKey(dshxxKey);
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
        dshzhKey.setZFZHLX(sZFZHLX);
        DSHZH dshzh = null;
        try {
            dshzh = dshzhMapper.selectByPrimaryKey(dshzhKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshzh) {
            runERR(tranObj, "ZF0001", sZFZHLX);
            return false;
        }

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
        mdzsj.setSHBH_U(sSHBH_U);
        mdzsj.setZDBH_U(sZDBH_U);
        mdzsj.setZFZHLX(sZFZHLX);
        mdzsj.setSFDDH_(sZFBLS_);
        mdzsj.setSFLS_U(null);//交易成功后更新
        mdzsj.setSFRQ_U(null);//交易成功后更新
        mdzsj.setFKRID_(null);//交易成功后更新
        mdzsj.setFKRZH_(null);//交易成功后更新
        mdzsj.setQTRQ_U(dQTRQ_U);
        mdzsj.setQTLS_U(tranObj.getHead("QTLS_U"));
        mdzsj.setQTJYM_(tranObj.getHead("QTJYM_"));
        mdzsj.setJYZT_U("u");
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
        String seller_id = dshzh.getZFZH_U();

        alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sZFBLS_ + "\"," +//我的流水
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
                "\"operator_id\":\"" + sZDBH_U + "\"," +
                "\"store_id\":\"" + sSHBH_U + "\"," +
                "\"terminal_id\":\"" + sZDBH_U + "\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"" + Com.alipaySys_service_provider_id + "\"" +
                "}," +
                "\"timeout_express\":\"" + timeout_express + "\"" +
                "}");

        AlipayTradePayResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent())) {
            runERR(tranObj, "ZF0005");
            return false;
        }
        try {
            response = alipayClient.execute(request);
        } catch (Exception e) {
            //标记为未知交易
            tranObj.commitFlg = true;
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
//        if (true != InsertMJYBWAfterDSF.exec(tranObj, response))//完成交易更新报文表
//        {
//            //标记为未知交易
//            tranObj.commitFlg = true;
//            runERR(tranObj, "ZF0006");
//            return false;
//        }
        if (true != InsertMJYBWAfterDSF.exec(tranObj, response))//完成交易更新报文表，直接插入，报错不返回
        {
            //标记为未知交易
            Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
        }

        if (response.isSuccess()) {
            Logger.log(tranObj, "LOG_DEBUG", "调用记账成功");
        } else {
            if (response.getSubCode().contains("INVALID_PARAMETER")) {//参数错误，立即停止所有交易等待确认

                tranObj.sqlSession.rollback();
                cxtcs.setVALUE_("0");
                try {
                    cxtcsMapper.updateByPrimaryKey(cxtcs);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "SQLUPD");
                    return false;
                }
                tranObj.sqlSession.commit();
            } else if ("20000".equals(response.getCode()) || "isp.unknow-error".equals(response.getSubCode()) || "ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                //查询一次，失败则直接返回
                AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                queryRequest.setBizContent("{" +
                        "\"out_trade_no\":\"" + sZFBLS_ + "\"," +//我的流水
                        "\"trade_no\":\"\"" +
                        "}");
                AlipayTradeQueryResponse queryResponse = null;
                try {
                    queryResponse = alipayClient.execute(queryRequest);
                } catch (Exception e) {
                    //标记为未知交易
                    tranObj.commitFlg = true;
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "ZF0004");
                    return false;
                }
                if (queryResponse.isSuccess()) {
                    Logger.log(tranObj, "LOG_DEBUG", "调用查询成功");
                } else {
                    Logger.log(tranObj, "LOG_ERR", request.getBizContent());
                    Logger.log(tranObj, "LOG_ERR", response.getBody());
                    if (queryResponse.getSubCode().contains("TRADE_NOT_EXIST")) {
                        Logger.log(tranObj, "LOG_ERR", "调用查询无记账信息");
                        runERR(tranObj, "ZF0002");
                        return false;
                    } else {
                        //标记为未知交易
                        tranObj.commitFlg = true;
                        Logger.log(tranObj, "LOG_ERR", "调用查询失败");
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                }
                if ("TRADE_SUCCESS".equals(queryResponse.getTradeStatus())) {
                    Logger.log(tranObj, "LOG_IO", "记账状态未知，通过查询得知该笔交易成功");
                } else {
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
            mdzsj.setJYZT_U("w");
            try {
                mdzsjMapper.updateByPrimaryKey(mdzsj);
            }catch (Exception e)
            {
                Logger.sysLogException(e);
            }
            runERR(tranObj, "ZFWAIT");//前端做一个轮循
            tranObj.commitFlg=true;
            return false;
        } else if (!"10000".equals(response.getCode())) {
            runERR(tranObj, "ZF0003");
            return false;
        }


        mdzsj.setSFLS_U(response.getTradeNo());
        mdzsj.setSFRQ_U(response.getGmtPayment());
        mdzsj.setFKRID_(response.getBuyerUserId());
        mdzsj.setFKRZH_(response.getBuyerLogonId());
        mdzsj.setJYZT_U("1");

        try {
            mdzsjMapper.updateByPrimaryKey(mdzsj);
        } catch (Exception e) {
            //不操作，认为成功，需人工核对状态为u的情况
            Logger.sysLogException(e);
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

}
