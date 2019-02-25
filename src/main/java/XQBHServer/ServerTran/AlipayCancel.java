package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.ServerInit;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.AlipayHelper.MyAlipayClient;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;

import java.text.ParseException;
import java.util.*;

import static XQBHServer.Utils.Data.DataUtils.YearString2Date;

/**
 * 扫描并取消交易状态，由其他轮循调用
 */
public class AlipayCancel extends Tran {

    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");

        /*==================================codeBegin=====================================*/

        //查询
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;

        MDZSJKey mdzsjKey = new MDZSJKey();
        mdzsjKey.setFRDM_U("9999");

        Date date=YearString2Date(tranObj,sYHTRQ_);

        mdzsjKey.setHTRQ_U(date);
        mdzsjKey.setHTLS_U(sYHTLS_);
        try {
            mdzsj = mdzsjMapper.selectByPrimaryKey(mdzsjKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if(mdzsj==null)
        {
            Tran.runERR(tranObj,"SQLNFD");
            return false;
        }
        String sSFDH_U = mdzsj.getSFDH_U();


        //取消
        AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppID, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");
        //call cancel
        AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();


        cancelRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + sSFDH_U + "\"," +
                "\"trade_no\":\"\"" +
                "  }");

        AlipayTradeCancelResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, cancelRequest.getBizContent(), "z")) {
            runERR(tranObj, "ZF0005");
            return false;
        }
        boolean insert_flg=true;
        try {
            response = alipayClient.execute(cancelRequest);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            CallResult callResult=new CallResult();
            String query_result=judgeSuccess(tranObj,sSFDH_U,callResult);
            Logger.log(tranObj,"LOG_DEBUG","query_result="+query_result);
            if(query_result.equals("TRADE_CLOSED"))
            {
                Logger.log(tranObj, "LOG_DEBUG", "调用撤销异常，但是查询得知该笔已撤销，处理成功");
                insert_flg=false;
            }else {
                runERR(tranObj, "ZF0004");
                return false;
            }

        }
        if(insert_flg) {
            if (true != InsertMJYBWAfterDSF.exec(tranObj, response.getBody(), response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg()))//完成交易更新报文表，直接插入，报错不返回
            {

                Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
            }

            if (response.isSuccess()) {
                if ("N".equals(response.getRetryFlag())) {
                    Logger.log(tranObj, "LOG_DEBUG", "调用cancel成功");
                } else {
                    Logger.log(tranObj, "LOG_ERR", "调用cancel成功，但是需要retry,getRetryFlag=" + response.getRetryFlag());
                    return true;
                }
            } else {
                if ("AQC.SYSTEM_ERROR".equals(response.getSubCode())) {
                    CallResult callResult = new CallResult();
                    String query_result = judgeSuccess(tranObj, sSFDH_U, callResult);
                    if (!"TRADE_CLOSED".equals(query_result)) {
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                    Logger.log(tranObj, "LOG_DEBUG", "调用撤销异常，但是查询得知该笔已撤销，处理成功");
                } else {
                    runERR(tranObj, "ZF0002");
                    return false;
                }

            }
        }
        if(!"c".equals(mdzsj.getJYZT_U())) {
            mdzsj.setJYZT_U("c");
            try {
                mdzsjMapper.updateByPrimaryKey(mdzsj);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                runERR(tranObj, "SQLUPD");
                return false;
            }
        }








        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }
    public String judgeSuccess(TranObj tranObj, String sSFDH_U, CallResult callResult){
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
