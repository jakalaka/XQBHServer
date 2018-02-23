package XQBHServer.Utils.AlipayHelper;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.Data.DataUtils;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;

public class GetTranStatus {
    public static void main(String[] args) {
        Logger.tmpLog(Com.getIn);

        if (args.length != 1) {
            Logger.tmpLog("参数不对");
            Logger.tmpLog("out_tran_no");
            Logger.tmpLog(Com.getOut);
        }
        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");
        Com.ERRMap = readAll(inputStream);

        String sSYSZDBH = Com.getSYSZDBH_U();
        if (sSYSZDBH==null||"".equals(sSYSZDBH))
        {
            Logger.tmpLog("获取临时系统终端信息失败!!!");
            Com.cancelThreadBusy=false;
            return;
        }else {
            Logger.tmpLog("获取临时系统终端编号="+sSYSZDBH);
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
            Logger.tmpLog("CALL " + head.get("HTJYM_"));
            Map headOut = (Map) XMLMapOut.get("head");
            Map bodyOut=(Map)XMLMapOut.get("body");
            if (!"AAAAAA".equals((headOut.get("CWDM_U")))) {
                Logger.tmpLog("Call FAIL!");
                Logger.tmpLog(" 查询失败!返回错误码[" + headOut.get("CWDM_U") + "]  错误信息[" + headOut.get("CWXX_U") + "]");
            } else {
                Logger.tmpLog("Call SUCCESS!");
                Logger.tmpLog("Code=["+ DataUtils.getValue(bodyOut,"CODE_U")+"]");
                Logger.tmpLog("Subcode=["+ DataUtils.getValue(bodyOut,"SUBCOD")+"]");

            }
        }finally {
            if (!Com.releaseSYSZHBH_U(sSYSZDBH))
            {
                Logger.tmpLog("释放柜员时出错!!!");
            }
        }


        Logger.tmpLog(Com.getOut);


    }
}
