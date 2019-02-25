package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CDSRWMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.SystemTran;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;


/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {


        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");

        Com.ERRMap = readAll(inputStream);


        /*
        起任务扫描进程
         */

        new Thread(new Runnable() {
            @Override
            public void run() {
                String sysLogfile=Logger.getLogPath("Timer");
                MDC.put("logFileName",sysLogfile ); //获取日志文件
                while (true) {
                    Logger.comLog("LOG_DEBUG",Com.TRAN_IN);
                    List<CDSRW> cdsrwList;
                    try {
                        DBAccess dbAccess = new DBAccess();
                        SqlSession sqlSession = null;


                        sqlSession = dbAccess.getSqlSession();
                        CDSRWMapper cxtcsMapper = sqlSession.getMapper(CDSRWMapper.class);
                        CDSRWExample cdsrwExample = new CDSRWExample();
                        cdsrwExample.or().andJLZT_UEqualTo("0").andBUSY_UEqualTo("0");
                        cdsrwList = cxtcsMapper.selectByExample(cdsrwExample);
                        Logger.comLog("LOG_DEBUG","cdsrwList.size()="+cdsrwList.size());
                        if (cdsrwList != null) {
                            for (int i = 0; i < cdsrwList.size(); i++) {
                                if ("0".equals(cdsrwList.get(i).getRWKZZL()))//时间间隔控制
                                {
                                    Date date=new Date();
                                    if(cdsrwList.get(i).getSCZXSJ()==null) {
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                                        cdsrwList.get(i).setSCZXSJ(formatter.parse("19920101"));
                                    }
                                    long diff=date.getTime()-cdsrwList.get(i).getSCZXSJ().getTime();
                                    if(diff>1000*Double.parseDouble((cdsrwList.get(i).getRWJGS_())))
                                    {
                                        final String sHTJYM_=cdsrwList.get(i).getHTJYM_();
                                        final String sDSRWBH=cdsrwList.get(i).getDSRWBH();
                                        Logger.comLog("LOG_DEBUG","begin to call dsrwbh="+sDSRWBH+" jym="+sHTJYM_);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {

                                                try {
                                                    callRW(sDSRWBH,sHTJYM_);
                                                } catch (Exception e) {
                                                    Logger.comLogException("LOG_ERR",e);
                                                }
                                            }
                                        }).start();


                                        cdsrwList.get(i).setSCZXSJ(date);
                                        cxtcsMapper.updateByPrimaryKey(cdsrwList.get(i));
                                        sqlSession.commit();
                                    }

                                } else if ("1".equals(cdsrwList.get(i).getRWKZZL())) {//指定时间控制
                                    //暂不开放
                                }
                            }
                        }
                        sqlSession.close();




                    } catch (Exception e) {
                        Logger.comLogException("LOG_ERR", e);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Logger.comLogException("LOG_ERR", e);
                    }
                    Logger.comLog("LOG_DEBUG",Com.TRAN_OUT);
                }
            }
        }
        ).start();


        return true;


    }

    public static void callRW(String sDSRWBH,String sHTJYM_) throws Exception {
        //======================日志文件设置
        String loggerFile = Logger.getLogPath("Timer" + "_" + sHTJYM_);
        MDC.put("logFileName", loggerFile); //获取日志文件
        Logger.comLog("LOG_IO", Com.TRAN_IN);


        //=======================任务置为忙
        Logger.comLog("LOG_IO", "任务置为忙");
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR",e);
            Logger.comLog("LOG_ERR", "获取sqlsession失败");
            return;
        }
        CDSRWMapper cdsrwMapper=sqlSession.getMapper(CDSRWMapper.class);
        CDSRWKey cdsrwKey=new CDSRWKey();
        cdsrwKey.setFRDM_U("9999");
        cdsrwKey.setDSRWBH(sDSRWBH);
        CDSRW cdsrw=cdsrwMapper.selectByPrimaryKey(cdsrwKey);

        cdsrw.setBUSY_U("1");
        sqlSession.commit();


        //==========================调用
        String sZDBH_U = Com.getSYSZDBH_U();//获取系统分配终端号


//        if (sZDBH_U != null && !sZDBH_U.equals("")) {
//            try {
//
//                //按固定时间跑的，可以在这里先计算出时间差，然后先sleep，暂无需求，不做
//
//
//                Map XMLMapIn = new HashMap();
//                CallResult callResult=new CallResult();
//                Map head = new HashMap();
//                head.put("ZDBH_U", sZDBH_U);
//                head.put("ZDJYM_", "SERVER");
//                head.put("HTJYM_", sHTJYM_);
//                head.put("QTRQ_U", Com.getDate());
//                head.put("QTLS_U", Com.getSYSQTLS(sZDBH_U));
//                XMLMapIn.put("head",head);
//                if(SystemTran.call(XMLMapIn,callResult)!=true)
//                    Logger.comLog("LOG_ERR", " FAIL!");
//                else
//                    Logger.comLog("LOG_IO", " SUCCESS!");
//
//
//
//            } catch (Exception e) {
//                Logger.comLogException("LOG_ERR", e);
//            } finally {
//                if (Com.releaseSYSZHBH_U(sZDBH_U)) {
//                    Logger.comLog("LOG_IO", "释放系统终端[" + sZDBH_U + "]");
//
//                } else {
//                    Logger.comLog("LOG_ERR", "释放系统终端[" + sZDBH_U + "]失败!");
//                }
//            }
//        } else {
//            Logger.comLog("LOG_ERR", "获取系统终端失败!");
//        }


        //任务置为闲
        Logger.comLog("LOG_IO", "任务置为闲置");
        cdsrw.setBUSY_U("0");
        sqlSession.commit();
        sqlSession.close();

        Logger.comLog("LOG_IO", Com.TRAN_OUT);
        Com.logFile.remove(loggerFile);

    }


}