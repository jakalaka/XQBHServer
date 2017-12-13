package XQBHServer.Utils.AlipayHelper;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
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
        while(true) {
            Logger.tmpLog(Com.getIn);

            if (args.length != 1) {
                Logger.tmpLog("参数不对");
                Logger.tmpLog("out_tran_no");
                Logger.tmpLog(Com.getOut);
            }
            InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");

            Com.ERRMap = readAll(inputStream);


            Map XMLMapIn = new HashMap();
            Map head = new HashMap();
            Map body = new HashMap();
            body.put("SFDDH_", args[0]);


            head.put("ZDBH_U", "SVR00001");
            head.put("ZDJYM_", "SERVER");
            head.put("HTJYM_", "AlipayQuery");
            head.put("QTRQ_U", Com.getDate());
            head.put("QTLS_U", Com.getSYSQTLS());
            XMLMapIn.put("head", head);
            XMLMapIn.put("body", body);
            String XMLIn = XmlUtils.map2XML(XMLMapIn);
            SystemTran systemTran = new SystemTran();

            String XMLOut = systemTran.SystemTran(XMLIn);

            Map XMLMapOut = XmlUtils.XML2map(XMLOut);
            Logger.tmpLog("CALL " + head.get("HTJYM_"));
            Map headOut = (Map) XMLMapOut.get("head");
            if (!"AAAAAA".equals((headOut.get("CWDM_U")))) {
                Logger.tmpLog(" FAIL!");
                Logger.tmpLog(" 查询失败!返回错误码[" + headOut.get("CWDM_U") + "]  错误信息[" + headOut.get("CWXX_U") + "]");
            } else
                Logger.tmpLog(" SUCCESS!");


            Logger.tmpLog(Com.getOut);
        }
    }
}
