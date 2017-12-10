package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;


/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {

        SqlSession sqlSession;
        /*
        尝试连接数据库
         */

        try {
            sqlSession = Com.dbAccess.getSqlSession();

            DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey = new DZDXXKey();
            dzdxxKey.setZDBH_U("SVR0000001");
            dzdxxKey.setFRDM_U("9999");
            DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        } catch (Exception e) {
            Logger.sysLogException(e);
            return false;
        }


        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");
        Com.ERRMap = readAll(inputStream);

        try {
            CXTCSMapper cxtcsMapper = sqlSession.getMapper(CXTCSMapper.class);
            CXTCSKey cxtcsKey = new CXTCSKey();
            cxtcsKey.setKEY_UU("LoopSleep");
            cxtcsKey.setFRDM_U("9999");
            CXTCS cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
            Com.cancelThreadLoopTime = Long.parseLong(cxtcs.getVALUE_());
        } catch (Exception e) {
            Logger.sysLogException(e);
            return false;
        }

        //创建撤销进程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Com.cancelThreadBusy) {
                    Logger.timerLog("===========================================================");
                    Logger.timerLog(Com.getIn);
                    Com.cancelThreadBusy = true;
                    Map XMLMapIn = new HashMap();
                    Map head = new HashMap();
                    head.put("ZDBH_U", "SVR00001");
                    head.put("ZDJYM_", "SERVER");
                    head.put("HTJYM_", "AlipayCancel");
                    head.put("QTRQ_U", Com.getDate());
                    head.put("QTLS_U", Com.getSYSQTLS());
                    XMLMapIn.put("head", head);
                    String XMLIn = XmlUtils.map2XML(XMLMapIn);
                    SystemTran systemTran = new SystemTran();
                    String XMLOut = systemTran.SystemTran(XMLIn);
                    Map XMLMapOut = XmlUtils.XML2map(XMLOut);
                    Logger.timerLog("CALL " + head.get("HTJYM_"));
                    if (!"AAAAAA".equals(((Map) XMLMapOut.get("head")).get("CWDM_U")))
                        Logger.timerLog(" FAIL!");
                    else
                        Logger.timerLog(" SUCCESS!");
                    Logger.timerLog(Com.getOut);
                    Logger.timerLog("===========================================================");
                    Com.cancelThreadBusy = false;
                    try {
                        Thread.sleep(Com.cancelThreadLoopTime);
                    } catch (InterruptedException e) {
                        Logger.sysLogException(e);
                    }
                }

            }
        });
        thread.start();

        sqlSession.close();

        return true;


    }
}