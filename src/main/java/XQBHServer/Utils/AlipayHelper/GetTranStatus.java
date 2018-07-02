package XQBHServer.Utils.AlipayHelper;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.Data.DataUtils;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;

public class GetTranStatus {
    public static void main(String[] args) {

        String loggerFile=Logger.getLogPath("Temp");
        MDC.put("logFileName", loggerFile); //获取日志文件
        
        Logger.comLog("LOG_IO",Com.getIn);

        if (args.length != 1) {
            Logger.comLog("LOG_ERR","参数不对");
            Logger.comLog("LOG_ERR","out_tran_no");
            Logger.comLog("LOG_IO",Com.getOut);
            return;
        }
        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");
        Com.ERRMap = readAll(inputStream);

        String sSYSZDBH = Com.getSYSZDBH_U();
        if (sSYSZDBH==null||"".equals(sSYSZDBH))
        {
            Logger.comLog("LOG_ERR","获取临时系统终端信息失败!!!");
            return;
        }else {
            Logger.comLog("LOG_IO","获取临时系统终端编号="+sSYSZDBH);
        }
        try {


            Map XMLMapIn = new HashMap();
            Map head = new HashMap();
            Map body = new HashMap();
            body.put("SFDH_U", args[0]);

            head.put("ZDBH_U", sSYSZDBH);
            head.put("ZDJYM_", "SERVER");
            head.put("HTJYM_", "AlipayQuery");
            head.put("QTRQ_U", Com.getDate());
            head.put("QTLS_U", Com.getSYSQTLS(sSYSZDBH));
            XMLMapIn.put("head", head);
            XMLMapIn.put("body", body);
            String XMLIn = XmlUtils.map2XML(XMLMapIn);
            SystemTran systemTran = new SystemTran();

            String XMLOut = systemTran.SystemTran(XMLIn);

            Map XMLMapOut = XmlUtils.XML2map(XMLOut);
            Logger.comLog("LOG_IO","CALL " + head.get("HTJYM_"));
            Map headOut = (Map) XMLMapOut.get("head");
            Map bodyOut=(Map)XMLMapOut.get("body");
            if (!"AAAAAA".equals((headOut.get("CWDM_U")))) {
                Logger.comLog("LOG_ERR","Call FAIL!");
                Logger.comLog("LOG_ERR"," 查询失败!返回错误码[" + headOut.get("CWDM_U") + "]  错误信息[" + headOut.get("CWXX_U") + "]");
            } else {
                Logger.comLog("LOG_IO","Call SUCCESS!");
                Logger.comLog("LOG_IO","Code=["+ DataUtils.getValue(bodyOut,"CODE_U")+"]");
                Logger.comLog("LOG_IO","Subcode=["+ DataUtils.getValue(bodyOut,"SUBCOD")+"]");

            }
        }finally {
            if (!Com.releaseSYSZHBH_U(sSYSZDBH))
            {
                Logger.comLog("LOG_ERR","释放柜员时出错!!!");
            }
        }


        Logger.comLog("LOG_IO",Com.getOut);
        Com.logFile.remove(loggerFile);


    }
}
