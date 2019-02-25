package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class Com {
    public static Map<String, String> ERRMap;


    /**
     * 日志等级
     * SYS,ERR,IO,DEBUG
     */
    public static String LOGLV = "";


    /**
     * 交易开始标志
     */
    public static final String TRAN_IN = "================================BEGIN======================================";


    /**
     * 交易结束标志
     */
    public static final String TRAN_OUT = "==================================END=======================================\n\n\n";

    /**
     * 接口接入标志
     */
    public static final String METHOD_IN = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    /**
     * 接口退出标志
     */
    public static final String METHOD_OUT = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
    /**
     * 临时用于压力测试记录笔数
     */
    public static long tmpCount = 0;


    private static final File config_file=new File("resources/sysInfo.properties");


    private static final Map<String, String> ConfigMap;
    static
    {
        ConfigMap = readAll(Class.class.getResourceAsStream("/resources/sysInfo.properties"));
        alipayGateway= ConfigMap.get("alipayGateway");
        alipayAppID= ConfigMap.get("alipayAppID");
        alipayPrivateKey= ConfigMap.get("alipayPrivateKey");
        alipayPulicKey= ConfigMap.get("alipayPulicKey");
        alipayConnectTimeout= Integer.parseInt(ConfigMap.get("alipayConnectTimeout"));
        alipayReadTimeout= Integer.parseInt(ConfigMap.get("alipayReadTimeout"));
        alipayCancelTimeThreshold=Integer.parseInt(ConfigMap.get("alipayCancelTimeThreshold"));


        wxpayUseSandbox= "true".equals(ConfigMap.get("wxpayUseSandbox"));
        wxpayAppID= ConfigMap.get("wxpayAppID");
        wxpayKey= ConfigMap.get("wxpayKey");
        wxpayConnectTimeout= Integer.parseInt(ConfigMap.get("wxpayConnectTimeout"));
        wxpayReadTimeout= Integer.parseInt(ConfigMap.get("wxpayReadTimeout"));
        wxpayCancelTimeThreshold= Integer.parseInt(ConfigMap.get("wxpayCancelTimeThreshold"));
        wxpayCertPath= ConfigMap.get("wxpayCertPath");
        WxTitle= ConfigMap.get("WxTitle");


        clientEncryptPublicKey= ConfigMap.get("clientEncryptPublicKey");
        serverSignPrivatebKey= ConfigMap.get("serverSignPrivatebKey");
        clientSignPublicKey= ConfigMap.get("clientSignPublicKey");




    }






    /**
     * 支付宝网关 用作沙箱判断
     */
    public static final String alipayGateway ;

    /**
     * 支付宝APPID
     */
    public static final String alipayAppID ;

    /**
     * 应用私钥
     */
    public static final String alipayPrivateKey ;

    /*
     * 支付宝公钥
     */
    public static final String alipayPulicKey ;

    /*
     * 支付宝三方ID
     */
    public static final String alipaySys_service_provider_id = "";

    /*
     * 支付宝链接超时时间
     */
    public static final int alipayConnectTimeout;

    /*
     * 支付宝读取超时时间
     */
    public static final int alipayReadTimeout ;

    public static final int alipayCancelTimeThreshold;

    /*
     * 微信是否为沙箱
     */
    public static final boolean wxpayUseSandbox ;

    /*
     * 微信 APPID
     */
    public static final String wxpayAppID ;



    /*
     * 微信 Key
     */
    public static  String wxpayKey ;

    /*
     * 微信 链接超时时间
     */
    public static final int wxpayConnectTimeout ;

    /*
     * 微信 读取超时时间
     */
    public static final int wxpayReadTimeout ;


    /*
     * 微信 回滚证书路径
     */
    public static final String wxpayCertPath;

    /*
     * 微信支付抬头
     */
    public static final String WxTitle;

    /*
     * 微信支付超时撤销时间
     */
    public static final int wxpayCancelTimeThreshold;




    public static final String clientEncryptPublicKey;
    public static final String serverSignPrivatebKey;
    public static final String clientSignPublicKey ;





    public static final String charset="GBK";

    public static Map<String,String > logFile=new HashMap<String,String>();

    /**
     * 微信网关_测试专用
     */
    public static final String wechatpayGateway = "we don't know";

    /**
     * 获取后台流水=S+12位终端编号+7位序号
     *
     * @return
     */
    public static boolean getHTLS(TranObj tranObj) {

        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sQTDX_U_head = tranObj.getHead("QTDX_U");

        Logger.log(tranObj, "LOG_DEBUG", "ZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_DEBUG", "SHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_DEBUG", "QTDX_U_head=" + sQTDX_U_head);

        int iSCJYXH = 0;
        String sTmp = "";


        String sHTLS_U_head = tranObj.getHead("HTLS_U");
        if (null == sHTLS_U_head || "".equals(sHTLS_U_head)) {

            if ("zd".equals(sQTDX_U_head)) {//柜员、终端取流水
                DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
                DZDXXKey dzdxxKey = new DZDXXKey();
                dzdxxKey.setZDBH_U(sZDBH_U_head);
                dzdxxKey.setFRDM_U("9999");
                DZDXX dzdxx = null;
                try {
                    dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLSEL");
                    return false;
                }
                Date dSCJYRQ = dzdxx.getSCJYRQ();
                if (DateUtils.isSameDay(dSCJYRQ, tranObj.date)) {
                    iSCJYXH = dzdxx.getSCJYXH();
                    if (iSCJYXH > 0)
                        iSCJYXH++;
                    else
                        iSCJYXH = 1;
                    dzdxx.setSCJYXH(iSCJYXH);
                } else {
                    iSCJYXH = 1;
                    dzdxx.setSCJYRQ(tranObj.date);
                    dzdxx.setSCJYXH(iSCJYXH);

                }
                try {
                    dzdxxMapper.updateByPrimaryKey(dzdxx);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLUPD");
                    return false;
                }
                sTmp = sZDBH_U_head;
            } else if ("sh".equals(sQTDX_U_head)) {//商户取流水
                DSHXXMapper dshxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
                DSHXXKey dshxxKey = new DSHXXKey();
                dshxxKey.setSHBH_U(sSHBH_U_head);
                dshxxKey.setFRDM_U("9999");
                DSHXX dshxx = null;
                try {
                    dshxx = dshxxMapper.selectByPrimaryKey(dshxxKey);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLSEL");
                    return false;
                }
                Date dSCJYRQ = dshxx.getSCJYRQ();
                if (DateUtils.isSameDay(dSCJYRQ, tranObj.date)) {
                    iSCJYXH = dshxx.getSCJYXH();
                    if (iSCJYXH > 0)
                        iSCJYXH++;
                    else
                        iSCJYXH = 1;
                    dshxx.setSCJYXH(iSCJYXH);
                } else {
                    iSCJYXH = 1;
                    dshxx.setSCJYRQ(tranObj.date);
                    dshxx.setSCJYXH(iSCJYXH);

                }
                try {
                    dshxxMapper.updateByPrimaryKey(dshxx);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLUPD");
                    return false;
                }
                sTmp = sSHBH_U_head;
            } else if ("kh".equals(sQTDX_U_head)) {//客户取流水
                DKHXXMapper dkhxxMapper = tranObj.sqlSession.getMapper(DKHXXMapper.class);
                DKHXXKey dkhxxKey = new DKHXXKey();
                dkhxxKey.setKHBH_U(sKHBH_U_head);
                dkhxxKey.setFRDM_U("9999");
                DKHXX dkhxx = null;
                try {
                    dkhxx = dkhxxMapper.selectByPrimaryKey(dkhxxKey);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLSEL");
                    return false;
                }
                Date dSCJYRQ = dkhxx.getSCJYRQ();
                if (DateUtils.isSameDay(dSCJYRQ, tranObj.date)) {
                    iSCJYXH = dkhxx.getSCJYXH();
                    if (iSCJYXH > 0)
                        iSCJYXH++;
                    else
                        iSCJYXH = 1;
                    dkhxx.setSCJYXH(iSCJYXH);
                } else {
                    iSCJYXH = 1;
                    dkhxx.setSCJYRQ(tranObj.date);
                    dkhxx.setSCJYXH(iSCJYXH);

                }
                try {
                    dkhxxMapper.updateByPrimaryKey(dkhxx);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLUPD");
                    return false;
                }
                sTmp = sKHBH_U_head;
            }

            tranObj.setHead("HTLS_U", "S" + sTmp + String.format("%07d", iSCJYXH));


            Logger.log(tranObj, "LOG_IO", "生成后台流水[" + tranObj.getHead("HTLS_U") + "]");
        } else
            Logger.log(tranObj, "LOG_IO", "已经生成后台流水[" + sHTLS_U_head + "],不再重复生成");


        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }

    /**
     * @apiNote 获取后台机器日期yyyyMMdd
     *
     * @return
     */
    public static String getDate() {
        String Date = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Date = df.format(new Date());
        return Date;
    }

    /**
     * 获取后台机器时间
     *
     * @return
     */
    public static String getTime() {
        String Time = "";
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");//设置日期格式
        Time = df.format(new Date());
        return Time;
    }

    /**
     * @apiNote 生成后台的前台流水,用于自动发起的交易，规则:C+12位终端号+7位序号
     *
     * @return
     */
    public  static   String getSYSQTLS(String sZDBH_U) {

        Logger.comLog("LOG_IO",Com.METHOD_IN);
        String sSYSQTLS = "";
        int XH = 1;
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR",e);

            return "";
        }

        CXTCSKey cxtcsKey = new CXTCSKey();
        CXTCSMapper cxtcsMapper = sqlSession.getMapper(CXTCSMapper.class);
        cxtcsKey.setKEY_UU(sZDBH_U+"_QTRQ");
        cxtcsKey.setFRDM_U("9999");
        CXTCS cxtcs = null;
        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        } catch (Exception e) {
            Logger.comLogException("LOG_ERR",e);
            return "";
        }
        if (cxtcs==null)
        {
            cxtcs=new CXTCS();
            cxtcs.setJLZT_U("0");
            cxtcs.setVALUE_(Com.getDate());
            cxtcs.setFRDM_U("9999");
            cxtcs.setKEY_UU(sZDBH_U+"_QTRQ");
            try {
                cxtcsMapper.insert(cxtcs);
            }catch (Exception e)
            {
                Logger.comLogException("LOG_ERR",e);
                return "";
            }
        }


        if (cxtcs.getVALUE_().equals(Com.getDate())) {//日期相同，取流水并更新流水
            cxtcsKey.setKEY_UU(sZDBH_U+"_QTLSXH");
            try {
                cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
            } catch (Exception e) {
                Logger.comLogException("LOG_ERR",e);
                return "";
            }
            if (cxtcs==null)
            {
                cxtcs=new CXTCS();
                cxtcs.setKEY_UU(sZDBH_U+"_QTLSXH");
                cxtcs.setFRDM_U("9999");
                cxtcs.setVALUE_("1");
                cxtcs.setJLZT_U("0");
                try {
                    cxtcsMapper.insert(cxtcs);
                }catch (Exception e)
                {
                    Logger.comLogException("LOG_ERR",e);
                    return "";
                }
            }
            XH = Integer.parseInt(cxtcs.getVALUE_());
        } else {
            //日期不同，流水重置并更新日期和流水
            XH = 1;
            cxtcs.setVALUE_(getDate());
            try {
                cxtcsMapper.updateByPrimaryKey(cxtcs);
            } catch (Exception e) {
                Logger.comLogException("LOG_ERR",e);
                return "";
            }
        }
        sSYSQTLS = "C"+sZDBH_U + String.format("%07d", XH);

        XH++;
        cxtcs.setKEY_UU(sZDBH_U+"_QTLSXH");
        cxtcs.setFRDM_U("9999");
        cxtcs.setVALUE_(XH + "");
        cxtcs.setJLZT_U("0");
        try {
            cxtcsMapper.updateByPrimaryKey(cxtcs);
        } catch (Exception e) {
            Logger.comLogException("LOG_ERR",e);
            return "";
        }
        sqlSession.commit();
        sqlSession.close();
        Logger.comLog("LOG_IO","sSYSQTLS=" + sSYSQTLS);
        Logger.comLog("LOG_IO",Com.METHOD_OUT);
        return sSYSQTLS;
    }

    /**
     * @return
     * @apiNote 获取系统终端编号
     */
    public static  String  getSYSZDBH_U() {
        String re = "";
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession=null;
        try {
            sqlSession = dbAccess.getSqlSession();
            try {

                DZDXXExample dzdxxExample = new DZDXXExample();
                dzdxxExample.or().andFRDM_UEqualTo("9999").andZDLXBHEqualTo("SERVER").andJLZT_UEqualTo("0").andZDDLZTEqualTo("0");

                DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
                List<DZDXX> dzdxxList = null;

                dzdxxList = dzdxxMapper.selectByExample(dzdxxExample);


                if (dzdxxList.size() == 0)
                    re = "";
                else {
                    DZDXX dzdxx = dzdxxList.get(0);
                    dzdxx.setZDDLZT("1");
                    dzdxxMapper.updateByPrimaryKey(dzdxx);
                    re=dzdxxList.get(0).getZDBH_U();
                }
                sqlSession.commit();
            } catch (Exception e) {
                Logger.comLogException("LOG_ERR",e);
            }
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR",e);
            return "";
        }finally {
            sqlSession.close();
        }




        return re;
    }

    /**
     * @param sZDBH_U
     * @return
     * @apiNote 将指定终端编号释放
     */
    public static boolean releaseSYSZHBH_U( String sZDBH_U) {
        boolean re=false;
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession=null;
        try {
            sqlSession = dbAccess.getSqlSession();
            try {
                DZDXXKey dzdxxKey=new DZDXXKey();
                dzdxxKey.setFRDM_U("9999");
                dzdxxKey.setZDBH_U(sZDBH_U);

                DZDXXMapper dzdxxMapper=sqlSession.getMapper(DZDXXMapper.class);
                DZDXX dzdxx=dzdxxMapper.selectByPrimaryKey(dzdxxKey);
                dzdxx.setZDDLZT("0");
                dzdxxMapper.updateByPrimaryKey(dzdxx);
                sqlSession.commit();
                re=true;
            }catch (Exception e)
            {
                Logger.comLogException("LOG_ERR",e);
            }finally {
                sqlSession.close();
            }
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR",e);
            return re;
        }
        return re;
    }



//    public static void main(String[] args) {
//        Logger.log(tranObj,"LOG_DEBUG",);
//    }
}
