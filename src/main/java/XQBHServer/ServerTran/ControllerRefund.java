package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerRefund extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sZFZHLX = tranObj.getString("ZFZHLX");
        BigDecimal bTHJYJE = tranObj.getBigDecimal("THJYJE");
        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQTLS_U_head = tranObj.getHead("QTLS_U");


        Logger.log(tranObj, "LOG_IO", "sQTDX_U_head=" + sQTDX_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sZFZHLX=" + sZFZHLX);
        Logger.log(tranObj, "LOG_IO", "bTHJYJE=" + bTHJYJE);
        Logger.log(tranObj, "LOG_IO", "sQTRQ_U=" + sQTRQ_U_head);
        Logger.log(tranObj, "LOG_IO", "sQTLS_U=" + sQTLS_U_head);

        /*==================================codeBegin=====================================*/

        /*=================合法性检查==============*/
        MDZSJKey mdzsjKey = new MDZSJKey();
        mdzsjKey.setFRDM_U("9999");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(sYHTRQ_);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        mdzsjKey.setHTRQ_U(date);
        mdzsjKey.setHTLS_U(sYHTLS_);

        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);

        MDZSJ mdzsj_old = null;
        try {
            mdzsj_old = mdzsjMapper.selectByPrimaryKey(mdzsjKey);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (mdzsj_old == null) {
            Logger.log(tranObj, "LOG_DEBUG", "查询无数据 sYHTRQ_=" + sYHTRQ_ + " sYHTLS_" + sYHTLS_);
            runERR(tranObj, "SQLNFD");
            return false;
        }

        if (!mdzsj_old.getZFZHLX().equals(sZFZHLX)) {
            runERR(tranObj, "REF001", sZFZHLX, mdzsj_old.getZFZHLX());
            return false;
        }
        //只允许处理正常数据
        if (!mdzsj_old.getJYZT_U().equals("1")) {
            runERR(tranObj, "REF002");
            return false;
        }
        if (mdzsj_old.getYTHJE_()==null)
            mdzsj_old.setYTHJE_(new BigDecimal(0));
        //只允许一次退货
        if (mdzsj_old.getJYJE_U().compareTo(bTHJYJE) != 0 || mdzsj_old.getYTHJE_().compareTo(new BigDecimal(0)) != 0) {
            runERR(tranObj, "REF003");
            return false;
        }


        Date dQTRQ_U = null;
        try {
            dQTRQ_U = formatter.parse(sQTRQ_U_head);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }

        //=================================生成流水============================
        if (Com.getHTLS(tranObj) == false)
            return false;
        String sSFDH_U = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");


        //=============================插入对账数据表===========================
        MDZSJ mdzsj_new = new MDZSJ();
        mdzsj_new.setFRDM_U("9999");
        mdzsj_new.setHTRQ_U(tranObj.date);
        mdzsj_new.setHTLS_U(tranObj.getHead("HTLS_U"));
        mdzsj_new.setJYSJ_U(tranObj.date);
        mdzsj_new.setHTJYM_(tranObj.getHead("HTJYM_"));
        mdzsj_new.setJYLX_U("1");
        mdzsj_new.setJYJE_U(bTHJYJE.multiply(new BigDecimal(-1)));
        mdzsj_new.setKHBH_U(sKHBH_U_head);
        mdzsj_new.setSHBH_U(sSHBH_U_head);
        mdzsj_new.setZDBH_U(sZDBH_U_head);

        mdzsj_new.setZFZHLX(sZFZHLX);
        mdzsj_new.setSFDH_U(sSFDH_U);
        mdzsj_new.setSFLS_U(null);//交易成功后更新
        mdzsj_new.setSFRQ_U(null);//交易成功后更新
        mdzsj_new.setFKRID_(null);//交易成功后更新
        mdzsj_new.setFKRZH_(null);//交易成功后更新
        mdzsj_new.setQTRQ_U(dQTRQ_U);
        mdzsj_new.setQTLS_U(sQTLS_U_head);
        mdzsj_new.setQTJYM_(tranObj.getHead("QTJYM_"));
        mdzsj_new.setJYZT_U("u");
        mdzsj_new.setSPXX_U(mdzsj_old.getSPXX_U());
        mdzsj_new.setFKM_UU("");
        mdzsj_new.setJLZT_U("0");
        try {
            mdzsjMapper.insert(mdzsj_new);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }



        /*
        开始调用各自交易
        */
        if (sZFZHLX.equals("z")) {

            AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.appPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\"" + mdzsj_old.getSFDH_U() + "\"," +
//                    "\"trade_no\":\"2014112611001004680073956707\"," +
                    "\"refund_amount\":" + bTHJYJE.toString() + "," +
//                    "\"refund_currency\":\"USD\"," +
                    "\"refund_reason\":\"正常退款\"," +
                    "\"out_request_no\":\"" + sSFDH_U + "\"," +
                    "\"operator_id\":\"" + sZDBH_U_head + "\"," +
                    "\"store_id\":\"" + sSHBH_U_head + "\"," +
                    "\"terminal_id\":\"" + sZDBH_U_head + "\"," +
                    "      \"goods_detail\":[{" +
                    "        \"goods_id\":\"" + mdzsj_old.getSPXX_U() + "\"," +
                    "\"alipay_goods_id\":\"\"," +
                    "\"goods_name\":\"" + mdzsj_old.getSPXX_U() + "\"," +
                    "\"quantity\":1," +
                    "\"price\":" + bTHJYJE.toString() + "," +
                    "\"goods_category\":\"" + mdzsj_old.getSPXX_U() + "\"," +
                    "\"body\":\"" + mdzsj_old.getSPXX_U() + "\"," +
                    "\"show_url\":\"\"" +
                    "        }]" +
                    "  }");
            AlipayTradeRefundResponse response = null;
            if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent(), "z")) {
                runERR(tranObj, "ZF0005");
                return false;
            }

            try {
                response = alipayClient.execute(request);
            } catch (Exception e) {
                //标记为未知交易
                Logger.logException(tranObj, "LOG_ERR", e);
                runERR(tranObj, "ZF0004");
                return false;
            }

            if (true != InsertMJYBWAfterDSF.exec(tranObj, response))//完成交易更新报文表，直接插入，报错不返回
            {
                //标记为未知交易
                Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
            }

            if (response.isSuccess()) {
                Logger.log(tranObj, "LOG_ERR", "调用支付宝退货成功");
                //插入本次数据
                mdzsj_new.setSFLS_U(response.getTradeNo());//交易成功后更新
                mdzsj_new.setSFRQ_U(response.getGmtRefundPay());//交易成功后更新
                mdzsj_new.setFKRID_(response.getBuyerUserId());//交易成功后更新
                mdzsj_new.setFKRZH_(response.getBuyerLogonId());//交易成功后更新
                mdzsj_new.setJYZT_U("t");
                mdzsj_new.setYHTRQ_(mdzsj_old.getHTRQ_U());
                mdzsj_new.setYHTLS_(mdzsj_old.getHTLS_U());
                try {
                    mdzsjMapper.updateByPrimaryKey(mdzsj_new);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "SQLUPD");
                    return false;
                }

                mdzsj_old.setJYZT_U("b");
                mdzsj_old.setYTHJE_(bTHJYJE);
                try {
                    mdzsjMapper.updateByPrimaryKey(mdzsj_old);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "SQLUPD");
                    return false;
                }

            } else {
                Logger.log(tranObj, "LOG_ERR", "调用支付宝退货失败");
                runERR(tranObj, "ZFB001", response.getSubMsg());
                return false;
            }


        } else if (sZFZHLX.equals("w")) {
            Logger.log(tranObj, "LOG_ERR", "暂不支持微信");
            runERR(tranObj, "WCT001", "暂不支持");
            return false;

        } else if (sZFZHLX.equals("c")) {
            Logger.log(tranObj, "LOG_ERR", "暂不支持现金");
            runERR(tranObj, "SYSERR");
            return false;
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);

        return true;
    }
}
