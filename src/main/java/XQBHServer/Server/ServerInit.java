package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.ServerTran.Tran;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;


/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {
        DBAccess dbAccess=new DBAccess();
        SqlSession sqlSession;
        /*
        尝试连接数据库
         */

        try {
            sqlSession = dbAccess.getSqlSession();

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
        sqlSession.close();



        //创建撤销进程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Com.cancelThreadBusy) {
                    Com.cancelThreadBusy=true;

                    try {
                        Thread.sleep(Com.cancelThreadLoopTime);
                    } catch (InterruptedException e) {
                        Logger.sysLogException(e);
                        Com.cancelThreadBusy=false;
                        continue;
                    }

                    Logger.timerLog("===========================================================");
                    Logger.timerLog(Com.getIn);

                    DBAccess dbAccess=new DBAccess();
                    SqlSession sqlSession_thread= null;
                    try {
                        sqlSession_thread = dbAccess.getSqlSession();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    CXTCSMapper cxtcsMapper = sqlSession_thread.getMapper(CXTCSMapper.class);
                    CXTCSKey cxtcsKey = new CXTCSKey();
                    cxtcsKey.setFRDM_U("9999");
                    cxtcsKey.setKEY_UU("AlipayTOut");
                    CXTCS cxtcs = null;
                    try {
                        cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
                    } catch (Exception e) {
                        Logger.timerLogException(e);
                        Logger.timerLog("获取超时时间失败 ERR");
                        Com.cancelThreadBusy=false;
                        continue;
                    }

                    long sTimeOut = 0;
                    try {
                        sTimeOut = Long.parseLong(cxtcs.getVALUE_());
                    } catch (Exception e) {
                        Logger.timerLogException(e);
                        Logger.timerLog("转换string2long出错 ERR");
                        Com.cancelThreadBusy=false;
                        continue;
                    }

                    Date dateLimit = new Date();
                    dateLimit.setTime(dateLimit.getTime() - sTimeOut);//CXTCS中定义的支付宝超时时间
                    Date[] dArrary;
                    try {
                        dArrary = Com.getRQSJ(dateLimit);
                    } catch (ParseException e) {
                        Logger.timerLogException(e);
                        Logger.timerLog("获取当前日期、时间出错 ERR");
                        Com.cancelThreadBusy=false;
                        continue;
                    }

                    MDZSJMapper mdzsjMapper = sqlSession_thread.getMapper(MDZSJMapper.class);
                    MDZSJExample mdzsjExample = new MDZSJExample();
                    mdzsjExample.or().andFRDM_UEqualTo("9999");
                    mdzsjExample.or().andJYZT_UEqualTo("w");
                    mdzsjExample.clear();
                    mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_UEqualTo(dArrary[0]).andJYSJ_ULessThan(dArrary[1]);
                    mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_ULessThan(dArrary[0]);
                    int iCount = 0;
                    try {
                        iCount = mdzsjMapper.countByExample(mdzsjExample);
                    } catch (Exception e) {
                        Logger.timerLogException(e);
                        Logger.timerLog("查询总笔数异常 ERR");
                        Com.cancelThreadBusy=false;
                        continue;
                    }



                    Logger.timerLog(" dArrary[0]=" +  dArrary[0]);
                    Logger.timerLog(" dArrary[1]=" +  dArrary[1]);

                    if (iCount > 0) {
                        Logger.timerLog("开始调用 iCount=" + iCount);
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
                    } else
                        Logger.timerLog("iCount=" + iCount);

                    sqlSession_thread.close();
                    Logger.timerLog(Com.getOut);
                    Logger.timerLog("===========================================================");
                    Com.cancelThreadBusy=false;
                }

            }
        });
        thread.start();


        return true;


    }
}