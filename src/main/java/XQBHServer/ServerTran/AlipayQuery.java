package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.ServerInit;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询支付宝交易状态
 * 如为已支付，返回成功，前端出货
 * 如为待支付，返回失败，错误代码ZFWAIT
 * 如为已关闭，返回失败，返回码CLOSED
 */
public class AlipayQuery extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sSFDDH_ = tranObj.getString("SFDDH_");


        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sSFDDH_" + sSFDDH_);
        /*==================================codeBegin=====================================*/

        String sSFDDH_FINAL = "";

        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            Logger.log(tranObj, "LOG_DEBUG", "通过前台日期流水查询");

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
            try {
                mdzsj = mdzsjMapper.selectByPrimaryKey(mdzsjKey);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "SQLSEL");
                return false;
            }
            sSFDDH_FINAL = mdzsj.getSFDDH_();
        } else if (null != sSFDDH_ && !"".equals(sSFDDH_)) {//通过三方订单号直接查询
            Logger.log(tranObj, "LOG_DEBUG", "通过三方订单查询");
            sSFDDH_FINAL = sSFDDH_;
        } else {
            Logger.log(tranObj, "LOG_ERR", "传入参数错误!");
            runERR(tranObj, "ERRPIN");
            return false;
        }


        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sSFDDH_FINAL + "\"," +
                "\"trade_no\":\"\"" +
                "}");

        AlipayTradeQueryResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent())) {
            runERR(tranObj, "ZF0005");
            return false;
        }



        try {
            response = Com.alipayClient.execute(request);
        } catch (AlipayApiException e) {
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
            Logger.log(tranObj, "LOG_DEBUG", "response.getTradeStatus()=" + response.getTradeStatus());

            if ("TRADE_CLOSED".equals(response.getTradeStatus())) {
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//正常前台调用更新mdzsj

                    if ("w".equals(mdzsj.getJYZT_U())) {
                        mdzsj.setJYZT_U("c");
                        try {
                            mdzsjMapper.updateByPrimaryKey(mdzsj);
                        } catch (Exception e) {
                            Logger.sysLogException(e);
                            runERR(tranObj, "SQLUPD");
                            return false;
                        }
                    }
                    tranObj.commitFlg = true;
                }

                runERR(tranObj, "ZF0008");
                return false;
            } else if ("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())) {
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//正常前台调用更新mdzsj

                    if ("w".equals(mdzsj.getJYZT_U())) {
                        mdzsj.setJYZT_U("1");
                        mdzsj.setSFLS_U(response.getTradeNo());
                        mdzsj.setSFRQ_U(response.getSendPayDate());
                        mdzsj.setFKRID_(response.getBuyerUserId());
                        mdzsj.setFKRZH_(response.getBuyerLogonId());
                        try {
                            mdzsjMapper.updateByPrimaryKey(mdzsj);
                        } catch (Exception e) {
                            Logger.sysLogException(e);
                            runERR(tranObj, "SQLUPD");
                            return false;
                        }

                    }
                }
            } else if ("WAIT_BUYER_PAY".equals(response.getTradeStatus())) {
                runERR(tranObj, "ZFWAIT");
                return false;
            } else {
                runERR(tranObj, "ZFILEG", response.getTradeStatus());
                return false;
            }

        } else {
            if ("ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                runERR(tranObj, "ZFSYSE");
                return false;
            } else {
                runERR(tranObj, "ZF0007");
                return false;
            }
        }


        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);

        return true;
    }

//    public static void main(String[] args) {
//        if (false == ServerInit.Init()) {
//            Logger.sysLog("ServerInit Fail!!!");
//            return;
//        }
//        Map XMLMapIn = new HashMap();
//        Map head = new HashMap();
//        head.put("ZDBH_U", "SVR00001");
//        head.put("ZDJYM_", "SERVER");
//        head.put("HTJYM_", "AlipayQuery");
//        head.put("QTRQ_U", Com.getDate());
//        head.put("QTLS_U", Com.getSYSQTLS());
//        XMLMapIn.put("head", head);
//        Map body = new HashMap();
//        body.put("YHTLS_", "SZD0000010001006");
//        body.put("YHTRQ_", "20171210");
//        XMLMapIn.put("body", body);
//        String XMLIn = XmlUtils.map2XML(XMLMapIn);
//        SystemTran systemTran = new SystemTran();
//        String XMLOut = systemTran.SystemTran(XMLIn);
//        Map out = XmlUtils.XML2map(XMLOut);
////        Logger.log(tranObj, "LOG_DEBUG", ((Map) out.get("head")).get("CWDM_U"));
////        Logger.log(tranObj, "LOG_DEBUG", ((Map) out.get("head")).get("CWXX_U"));
//    }
}
