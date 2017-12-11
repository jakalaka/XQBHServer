package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.ServerInit;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;

import java.text.ParseException;
import java.util.*;

/**
 * ɨ�貢ȡ������״̬����������ѭ����
 */
public class AlipayCancel extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sZDBH_U = tranObj.getHead("ZDBH_U");

        /*==================================codeBegin=====================================*/
        CXTCSMapper cxtcsMapper = tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("AlipayTOut");
        CXTCS cxtcs = null;
        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        long sTimeOut = 0;
        try {
            sTimeOut = Long.parseLong(cxtcs.getVALUE_());
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SYSTRS");
            return false;
        }


        Date dateLimit = new Date();
        dateLimit.setTime(dateLimit.getTime() - sTimeOut);//CXTCS�ж����֧������ʱʱ��
        Date []dArrary;
        try {
            dArrary = Com.getRQSJ(dateLimit);
        } catch (ParseException e) {
            Logger.sysLogException(e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MDZSJExample mdzsjExample = new MDZSJExample();
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        mdzsjExample.clear();
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_UEqualTo(dArrary[0]).andJYSJ_ULessThan(dArrary[1]);
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_ULessThan(dArrary[0]);
        List<MDZSJ> mdzsjList = null;
        try {
            mdzsjList = mdzsjMapper.selectByExample(mdzsjExample);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }

        AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        for (int i = 0; i < mdzsjList.size(); i++) {
            //call cancel
            AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();


            cancelRequest.setBizContent("{" +
                    "\"out_trade_no\":\"" + mdzsjList.get(i).getSFDDH_() + "\"," +
                    "\"trade_no\":\"\"" +
                    "  }");

            AlipayTradeCancelResponse calcelResponse = null;
            if (true != InsertMJYBWBeforeDSF.exec(tranObj, cancelRequest.getBizContent())) {
                runERR(tranObj, "ZF0005");
                return false;
            }
            try {
                calcelResponse = alipayClient.execute(cancelRequest);
            } catch (Exception e) {
                //���Ϊδ֪����
                Logger.logException(tranObj, "LOG_ERR", e);
                runERR(tranObj, "ZF0004");
                return false;
            }
            if (true != InsertMJYBWAfterDSF.exec(tranObj, calcelResponse))//��ɽ��׸��±��ı�ֱ�Ӳ��룬��������
            {
                //���Ϊδ֪����
                Logger.log(tranObj, "LOG_ERR", "������������뱨��ʧ��");
            }

            if (calcelResponse.isSuccess()) {
                if ("N".equals(calcelResponse.getRetryFlag())) {
                    mdzsjList.get(i).setJYZT_U("c");
                    try {
                        mdzsjMapper.updateByPrimaryKey(mdzsjList.get(i));
                    } catch (Exception e) {
                        //���Ϊδ֪����
                        Logger.logException(tranObj, "LOG_ERR", e);
                        runERR(tranObj, "SQLUPD");
                        return false;
                    }
                    tranObj.sqlSession.commit();

                    Logger.log(tranObj, "LOG_DEBUG", "����cancel�ɹ�");
                } else {
                    Logger.log(tranObj, "LOG_ERR", "����cancel�ɹ���������Ҫretry,getRetryFlag=" + calcelResponse.getRetryFlag());
                }
            } else {
                if ("ACQ.SYSTEM_ERROR".equals(calcelResponse.getSubCode())) {
                    Logger.log(tranObj, "LOG_ERR", "ACQ.TRADE_STATUS_ERROR");
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTRQ_U" + mdzsjList.get(i).getHTRQ_U());
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTLS_U" + mdzsjList.get(i).getHTLS_U());
                    continue;
                } else if ("ACQ.SELLER_BALANCE_NOT_ENOUGH".equals(calcelResponse.getSubCode())) {
                    //���ò�ѯ����
                    Logger.log(tranObj, "LOG_ERR", "ACQ.SELLER_BALANCE_NOT_ENOUGH");
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTRQ_U" + mdzsjList.get(i).getHTRQ_U());
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTLS_U" + mdzsjList.get(i).getHTLS_U());
                    continue;
                } else if ("ACQ.REASON_TRADE_BEEN_FREEZEN".equals(calcelResponse.getSubCode())) {
                    //���ò�ѯ����
                    Logger.log(tranObj, "LOG_ERR", "ACQ.REASON_TRADE_BEEN_FREEZEN");
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTRQ_U" + mdzsjList.get(i).getHTRQ_U());
                    Logger.log(tranObj, "LOG_ERR", "mdzsjList.get(i).getHTLS_U" + mdzsjList.get(i).getHTLS_U());
                    continue;
                }

                return false;
            }

        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

    public static void main(String[] args) {
        if (false == ServerInit.Init()) {
            Logger.sysLog("ServerInit Fail!!!");
            return;
        }

    }


}
