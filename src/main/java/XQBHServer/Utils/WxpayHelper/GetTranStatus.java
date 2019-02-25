package XQBHServer.Utils.WxpayHelper;

import XQBHServer.Server.Com;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.Data.DataUtils;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.slf4j.MDC;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;

public class GetTranStatus {
    public static void main(String[] args) {

        String loggerFile=Logger.getLogPath("Temp");
        MDC.put("logFileName", loggerFile); //��ȡ��־�ļ�

        Logger.comLog("LOG_IO",Com.METHOD_IN);

        if (args.length != 1) {
            Logger.comLog("LOG_ERR","��������");
            Logger.comLog("LOG_ERR","out_tran_no");
            Logger.comLog("LOG_IO",Com.METHOD_OUT);
            return;
        }
        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");
        Com.ERRMap = readAll(inputStream);

        String sSYSZDBH = Com.getSYSZDBH_U();
        if (sSYSZDBH==null||"".equals(sSYSZDBH))
        {
            Logger.comLog("LOG_ERR","��ȡ��ʱϵͳ�ն���Ϣʧ��!!!");
            return;
        }else {
            Logger.comLog("LOG_IO","��ȡ��ʱϵͳ�ն˱��="+sSYSZDBH);
        }
        try {



            Map XMLMapIn = new HashMap();
            CallResult callResult=new CallResult();
            Map head = new HashMap();
            Map body = new HashMap();
            head.put("ZDBH_U", sSYSZDBH);
            head.put("ZDJYM_", "SERVER");
            head.put("HTJYM_", "WxpayQuery");
            head.put("QTRQ_U", Com.getDate());
            head.put("QTLS_U", Com.getSYSQTLS(sSYSZDBH));
            body.put("SFDH_U", args[0]);
            XMLMapIn.put("head",head);
            XMLMapIn.put("body", body);
            if(SystemTran.call(XMLMapIn,callResult)!=true) {
                Logger.comLog("LOG_ERR","Call FAIL!");
                Logger.comLog("LOG_ERR"," ��ѯʧ��!���ش�����[" + callResult.getHead().get("CWDM_U") + "]  ������Ϣ[" + callResult.getHead().get("CWXX_U") + "]");
            }
            else
            {
                Logger.comLog("LOG_IO","Call SUCCESS!");
                Logger.comLog("LOG_IO","Code=["+ DataUtils.getValue(callResult.getBody(),"CODE_U")+"]");
                Logger.comLog("LOG_IO","Subcode=["+ DataUtils.getValue(callResult.getBody(),"SUBCOD")+"]");
            }

        } catch (Exception e) {
            Logger.comLogException("LOG_ERR",e);
        } finally {
            if (!Com.releaseSYSZHBH_U(sSYSZDBH))
            {
                Logger.comLog("LOG_ERR","�ͷŹ�Աʱ����!!!");
            }
        }


        Logger.comLog("LOG_IO",Com.METHOD_OUT);
        Com.logFile.remove(loggerFile);


    }
}
