package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJExample;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.AlipayHelper.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static XQBHServer.Utils.Data.DataUtils.Date2StringofYear;

/**
 * 查询支付宝交易状态
 * <p>
 * 1.传入后台流水和日期，认为交易已经登记MDZSJ
 * 场景如：前端通讯后轮训查询结果，此时需根据结果更新MDZSJ状态
 * <p>
 * 2.传入三方单号,认为交易未执行登记数据库，此时不更新本地数据，只是返回三方状态，
 * 场景如：服务器手工查询，支付交易不明确时的立即查询
 */
public class AlipayQuery extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sSFDH_U = tranObj.getString("SFDH_U");

        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sSFDH_U" + sSFDH_U);
        /*==================================codeBegin=====================================*/
        AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppID, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        String sSFDH_UFINAL = "";

        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            Logger.log(tranObj, "LOG_DEBUG", "通过后台日期流水查询");

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
            sSFDH_UFINAL = mdzsj.getSFDH_U();
        } else if (null != sSFDH_U && !"".equals(sSFDH_U)) {//通过三方订单号直接查询
            Logger.log(tranObj, "LOG_DEBUG", "通过三方订单查询");
            sSFDH_UFINAL = sSFDH_U;


        } else {
            Logger.log(tranObj, "LOG_ERR", "传入参数错误!");
            runERR(tranObj, "ERRPIN");
            return false;
        }


        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sSFDH_UFINAL + "\"," +
                "\"trade_no\":\"\"" +
                "}");

        AlipayTradeQueryResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent(), "z")) {
            runERR(tranObj, "ZF0005");
            return false;
        }


        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
        if (true != InsertMJYBWAfterDSF.exec(tranObj, response.getBody(), response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg()))//完成交易更新报文表，直接插入，报错不返回
        {
            //标记为未知交易
            Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
        }
        if (!response.isSuccess()) {
            if ("ACQ.TRADE_NOT_EXIST".equals(response.getSubCode())) {
                runERR(tranObj, "ZF0007");
                return false;
            } else if ("ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                runERR(tranObj, "ZF0004");
                return false;
            }
            runERR(tranObj, "ZF0002");
            return false;
        }
        Logger.log(tranObj, "LOG_DEBUG", "调用成功");


        Logger.log(tranObj, "LOG_DEBUG", "response.getTradeStatus()=" + response.getTradeStatus());
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//正常前台调用更新mdzsj
            if ("TRADE_CLOSED".equals(response.getTradeStatus())) {
                if (!"c".equals(mdzsj.getJYZT_U())) {
                    mdzsj.setJYZT_U("c");
                    mdzsj.setSFLS_U(response.getTradeNo());
                    mdzsj.setSFRQ_U(response.getSendPayDate());
                    mdzsj.setFKRID_(response.getBuyerUserId());
                    mdzsj.setFKRZH_(response.getBuyerLogonId());
                    try {
                        mdzsjMapper.updateByPrimaryKey(mdzsj);
                    } catch (Exception e) {
                        Logger.logException(tranObj, "LOG_ERR", e);
                        runERR(tranObj, "SQLUPD");
                        return false;
                    }
                }
            } else if ("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())) {
                if ("w".equals(mdzsj.getJYZT_U())) {
                    mdzsj.setJYZT_U("1");
                    mdzsj.setSFLS_U(response.getTradeNo());
                    mdzsj.setSFRQ_U(response.getSendPayDate());
                    mdzsj.setFKRID_(response.getBuyerUserId());
                    mdzsj.setFKRZH_(response.getBuyerLogonId());
                    try {
                        mdzsjMapper.updateByPrimaryKey(mdzsj);
                    } catch (Exception e) {
                        Logger.logException(tranObj, "LOG_ERR", e);
                        runERR(tranObj, "SQLUPD");
                        return false;
                    }
                }
            }
        }


        tranObj.BodyMap.put("CODE_U", response.getCode());
        tranObj.BodyMap.put("SUBCOD", response.getSubCode());
        tranObj.BodyMap.put("TSTAT_", response.getTradeStatus());
        tranObj.BodyMap.put("SFLS_U", response.getTradeNo());
        tranObj.BodyMap.put("SFRQ_U", Date2StringofYear(response.getSendPayDate()));
        tranObj.BodyMap.put("FKRID_", response.getBuyerUserId());
        tranObj.BodyMap.put("FKRZH_", response.getBuyerLogonId());









        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);

        return true;
    }

}
