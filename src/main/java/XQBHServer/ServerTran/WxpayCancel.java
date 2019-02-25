package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.ServerInit;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.WxpayHelper.MyWxConfig;
import XQBHServer.Utils.log.Logger;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.Data.DataUtils.YearString2Date;

/**
 * �����������ţ��ҷ�������������ⲿ��ˮ��
 * �����ѯ
 */
public class WxpayCancel extends Tran {

    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");

        /*==================================codeBegin=====================================*/
        String pay_mode = "w";
        //��ѯ
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;

        MDZSJKey mdzsjKey = new MDZSJKey();
        mdzsjKey.setFRDM_U("9999");

        Date date = YearString2Date(tranObj, sYHTRQ_);

        mdzsjKey.setHTRQ_U(date);
        mdzsjKey.setHTLS_U(sYHTLS_);
        try {
            mdzsj = mdzsjMapper.selectByPrimaryKey(mdzsjKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (mdzsj == null) {
            Tran.runERR(tranObj, "SQLNFD");
            return false;
        }
        String sSFDH_U = mdzsj.getSFDH_U();


         /*
        ��֯��ѯ
         */

        MyWxConfig config = null;
        WXPay wxpay = null;
        Map<String, String> response = new HashMap<String, String>();
        Map<String, String> require = new HashMap<String, String>();
        try {
            config = new MyWxConfig(mdzsj.getZFZH_U());
            wxpay = new WXPay(config, true, Com.wxpayUseSandbox);

            require.put("out_trade_no", mdzsj.getSFDH_U());


        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "ZF0004");
            return false;

        }


        if (true != InsertMJYBWBeforeDSF.exec(tranObj, JSON.toJSONString(require), pay_mode)) {
            runERR(tranObj, "ZF0005");
            return false;
        }

        boolean insert_flg = true;
        try {
            response = wxpay.reverse(require);

        } catch (Exception e) {

            CallResult callResult = new CallResult();
            String query_result = judgeSuccess(tranObj, sSFDH_U, mdzsj.getZFZH_U(), callResult);
            Logger.log(tranObj, "LOG_DEBUG", "query_result=" + query_result);
            if (query_result.equals("REVOKED")) {
                Logger.log(tranObj, "LOG_INFO", "���ó����쳣�����ǲ�ѯ��֪�ñ��ѳ����������ɹ�");
                insert_flg = false;

            } else {
                Logger.logException(tranObj, "LOG_ERR", e);
                runERR(tranObj, "ZF0004");
                return false;
            }


        }

        if (insert_flg) {
            if (true != InsertMJYBWAfterDSF.exec(tranObj, JSON.toJSONString(response), response.get("return_code"), response.get("return_msg"), response.get("err_code"), response.get("err_code_des"))) {

                Logger.log(tranObj, "LOG_ERR", "������������뱨��ʧ��");
            }

            if (!"SUCCESS".equals(response.get("return_code"))) {
                runERR(tranObj, "ZF0002");
                return false;
            }

            if (!"SUCCESS".equals(response.get("result_code"))) {
                if ("SYSTEMERROR".equals(response.get("err_code"))) {
                    //����ϵͳ������������ѯ
                    CallResult callResult = new CallResult();
                    String query_result = judgeSuccess(tranObj, sSFDH_U, mdzsj.getZFZH_U(), callResult);
                    Logger.log(tranObj, "LOG_DEBUG", "query_result=" + query_result);
                    if (query_result.equals("REVOKED")) {
                        Logger.log(tranObj, "LOG_INFO", "���ó����쳣�����ǲ�ѯ��֪�ñ��ѳ���������ɹ�");
                    } else {
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                } else {
                    runERR(tranObj, "ZF0002");
                    return false;
                }
            }
        }

        if (!"c".equals(mdzsj.getJYZT_U())) {
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

    public String judgeSuccess(TranObj tranObj, String sSFDH_U, String sZFZH_U, CallResult callResult) {
        String result = "";
        try {
            Map XMLMapIn = new HashMap();
            Map head = null;
            Map body = new HashMap();
            head = tranObj.getHeadMap();
            head.put("HTJYM_", "WxpayQuery");

            body.put("SFDH_U", sSFDH_U);
            body.put("SFGX_U", "0");
            body.put("SHZH_U", sZFZH_U);

            XMLMapIn.put("head", head);
            XMLMapIn.put("body", body);
            boolean call_success = false;
            try {
                if (SystemTran.call(tranObj, XMLMapIn, callResult) != true) {
                    Logger.log(tranObj, "LOG_ERR", "call failed!");
                    return result;
                }

            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                return result;
            }

            result = callResult.getBody().get("TSTAT_").trim();
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
        }

        return result;
    }
}


