package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CDSRWMapper;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;


/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;


        /*
        尝试连接数据库
         */
        List<CDSRW> cdsrwList;

        try {



            InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");

            Com.ERRMap = readAll(inputStream);

            sqlSession = dbAccess.getSqlSession();
            CDSRWMapper cxtcsMapper = sqlSession.getMapper(CDSRWMapper.class);
            CDSRWExample cdsrwExample = new CDSRWExample();
            cdsrwExample.or().andJLZT_UEqualTo("0");
            cdsrwList = cxtcsMapper.selectByExample(cdsrwExample);
        } catch (Exception e) {
            Logger.comLogException("LOG_ERR", e);
            return false;
        }
        sqlSession.close();

        if (cdsrwList != null) {
            for (int i = 0; i < cdsrwList.size(); i++) {
                final int no = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String loggerFile = Logger.getLogPath("Timer" + "_" + cdsrwList.get(no).getHTJYM_());
                        MDC.put("logFileName", loggerFile); //获取日志文件

                        while (true) {
                            String sZDBH_U = Com.getSYSZDBH_U();
                            if (sZDBH_U != null && !sZDBH_U.equals("")) {
                                try {
                                    Logger.comLog("LOG_IO", "===========================================================");
                                    Logger.comLog("LOG_IO", Com.getIn);


                                    //按固定时间跑的，可以在这里先计算出时间差，然后先sleep，暂无需求，不做


                                    Map XMLMapIn = new HashMap();
                                    Map head = new HashMap();
                                    head.put("ZDBH_U", sZDBH_U);
                                    head.put("ZDJYM_", "SERVER");
                                    head.put("HTJYM_", cdsrwList.get(no).getHTJYM_());
                                    head.put("QTRQ_U", Com.getDate());
                                    head.put("QTLS_U", Com.getSYSQTLS(sZDBH_U));
                                    XMLMapIn.put("head", head);
                                    String XMLIn = XmlUtils.map2XML(XMLMapIn);
                                    SystemTran systemTran = new SystemTran();
                                    String XMLOut = systemTran.SystemTran(XMLIn);
                                    MDC.put("logFileName", loggerFile); //获取日志文件
                                    Map XMLMapOut = XmlUtils.XML2map(XMLOut);
                                    Logger.comLog("LOG_IO", "CALL " + head.get("HTJYM_"));
                                    if (!"AAAAAA".equals(((Map) XMLMapOut.get("head")).get("CWDM_U")))
                                        Logger.comLog("LOG_ERR", " FAIL!");
                                    else
                                        Logger.comLog("LOG_IO", " SUCCESS!");


                                    Logger.comLog("LOG_IO", Com.getOut);
                                    Logger.comLog("LOG_IO", "===========================================================\n\n\n");

                                } catch (Exception e) {
                                    Logger.comLogException("LOG_ERR", e);
                                } finally {
                                    if (Com.releaseSYSZHBH_U(sZDBH_U)) {
                                        Logger.comLog("LOG_IO", "释放系统终端[" + sZDBH_U + "]");

                                    } else {
                                        Logger.comLog("LOG_ERR", "释放系统终端[" + sZDBH_U + "]失败!");
                                    }
                                }
                            } else {
                                Logger.comLog("LOG_ERR", "获取系统终端失败!");
                            }


                            if ("0".equals(cdsrwList.get(no).getRWKZZL())) {
                                long baseTime = Long.parseLong(cdsrwList.get(no).getRWJGS_());
                                baseTime *= 1000;
                                try {
                                    Thread.sleep(baseTime);
                                } catch (InterruptedException e) {
                                    Logger.comLogException("LOG_ERR", e);
                                }
                            }

                        }

                    }
                }).start();
            }
        }


        return true;


    }
}