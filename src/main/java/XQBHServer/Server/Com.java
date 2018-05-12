package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class Com {
    public static Map<String, String> ERRMap;

    public static boolean cancelThreadBusy = false;
    public static long cancelThreadLoopTime = 1000;
    /**
     * 日志等级
     * SYS,ERR,IO,DEBUG
     */
    public static String LOGLV = "";

    /**
     * 接口接入标志
     */
    public static final String getIn = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    /**
     * 接口退出标志
     */
    public static final String getOut = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
    /**
     * 临时用于压力测试记录笔数
     */
    public static long tmpCount = 0;


    /**
     * 支付宝网关_测试专用
     */
    public static final String alipayGateway = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝APPID_测试专用
     */
    public static final String alipayAppid = "2016080500173689";

    /**
     * 应用私钥_测试专用
     */
    public static final String appPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClV7IkP/AOnHD+rL/+sAZjsowKXCSiLAKXILoIO/TrDYChNbmxrEZ3AsGwshRIUre332khPWfcKG04rwVns6RnYj4A6BaJ2WwrPHnA0NllKXCzq0RrPxaTFltlcXQm2funbIJjxCwlHXrJzzzi7dAsu1QIf5EYQbELN/dHIAtarZge8fbAsBkkqYD3xSMuZq0K0kmhybCqSWjYFFfvlWVyR+7i4K3dPiUeC69OHdhfsaP+BtKoPgtlXxtQN/H4yn88oIdL05nXwqLVrqCr53UVXssbnZ0iwTw5X8r6/RYdMSGOAo8aQPmtsP8HBfT0Y0xFVjODo2FfYmtFXjCsYtBDAgMBAAECggEAd8bTshbvXGZQJO4YF/SEbGzHrimaEDE5nymCGrz0a+LYL/CvmNpoIYbJsasPrmTd2kHp8r59IqvWk52WmM02Z/5vVFDNIwdyqM+ik9+33OTsU/vaEKcfP2aOxyotLofzGhItUwClyi1U6iVKwkns6Lq74XwoLB5KlwnwBIJeN00nji3jacUOahZPn8472In5dtM+PqKtm6vJFVYpfwtTKoxblmpOqsTTSAqx50o3Y/ilVxvZo1f34xo+gXkGQ/i4NH8qRgg+L/FOG4VKAATDP72l8tpjUOxpbkuzhattP6MBCeY+RWg+9xlt174zqkGB19HBSJorqicgYXaqnLghwQKBgQDbzHf01vvU5tGGBmaOVrjWvhqvfE1W9CEpQWUGH576Vc3IwxUuMqA6ed4t8N51THYAVNh6kcDwQoBNSdBERabwQoxVaazzEhtnLOgzZPP5PE04GfeWdlzglUDH5iIzB1sa07gKSUNrsTP6PlR+f/ijdVX/eCaFBOJawoN8jqU3GwKBgQDAkydTYUzzeJfY1Mfh1ACmhT7EmvHFBnNIO01Ls65z90YG9sLHcQ1wD6p8ag8O93PBFP8729AbW157vdmgu0abWc35Nznuyg5Xpq+p4N9oPTqyYuaBS7NaDU5YghBBC0QWk22RMuCsCP8CO4Z1LgKA0RpcPIQ8YEQA2VmtvDEV+QKBgBn382hDCifiSXj7QpyolFgSx9ZZ6k1OtKhKKKRrkf3jq1d/7P2zT4j5Iw3semwDZ6GsZJM+qzv3r27yPKAEVq/mPOxOeveQ+RncjWadE9IrlLf/IWhufZSvLaMwhnPe952YzBKzCzsrCYgUWylC915gm5N+X5axuAifGKfbtptnAoGADxp7dxqmgmHu5t6pXpjWBDlnFtxgIefDmuKryUgqYoX+RAWOeT3wo91WrbNTwwS9W2NeMT+oLr0Xx/S34NdPTlfYw7cFIClQvqcgF96/JtnGhL8k/PcG9gUdI+vvgmpzyKF/cmffHx8FgRNSFFarc5byzlEgvet+6eIiGnIsF2ECgYEAvaBB3ncRzXHFaBRD6k/VSzJEA+BWEeD0gplBMATRSieofwIEi9gunD9+DAkc3WIrXEMvCMaAtasjcx2Z/HHEwuPm+T1otyJZZJWVLQrUs8mcG3xLOoYx6XTtooT1q6kdOCa5d5qVQ2IOWQzCdG2y44t2TNgDkP3Xvo960sXCfeY=";

    /*
     * 支付宝公钥_测试专用
     */
    public static final String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtm13QopHlAfSUtMkRHy/fUt+sV0dFaLJXyX5y2j/Ic/YxctgrrzkHt5eL5KTrPFKvhue7t/cEV+adblAcw1JWVRjci9xu7LMRgU+qeKqSowr3VYZKLpQJTlJsULEFsDn5b6DQyX7xPB4CpIGtnHizqCthwvKP/P5e0rYDcqsU4Ccmn1/DAxboV/tFpz/UO7kMA6G8tCtUKwn8tNITEQd6r2DWjidg3tNtg4WbvjxtzCU43HnvREqonDnO6R+InSP+9VBMqz+5b0QcQ71ql0GpS8ecZhhGrZRTTJqowxtZ2Grs44dAzP4G514LC2+3Z8+LeWbsB6Mk3an0AhpUbGXcQIDAQAB";


    public static final String alipaySys_service_provider_id = "";


    public static final String clientEncryptPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhdpfIPubUqSL8COWgg9dKXeOn4UQTSYi5HRi4ds7pWnwUgHklRpwwIYxtxeN8xTL/gQXwOJMILV5C2O3Z6dnJIhKcthyzQVKeqpsfwVVYoeTzhsf7o2ZvFgxOlTt5VnNlIuIJJjcYr+L5j0zbmdT8+ZRhAU6O4zyKojzT8+Mf1noh/wpiPeWMNOs78xw2L7uqcXbRX4PCqBm7k67hh2WxNHNYLet4Z/hWmLZG5PX73Cmca95TC7fATHRH4UYYBX7A6OvwpEMkt5RS9A6QrVxemMiOL8H0T+c4Ix8dUpiqtubCuzEoWjrc1R5ELU3erMxa9FnWWLLMHC0eNtjmzYwXwIDAQAB";
    public static final String serverSignPrivatebKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI+Pz+lG+zrHaUO7hzGhvQrRSL+CbuQAgQRDp9PhofRcTM4tv0kTIUGFxqDo+o3s9j8pi1kHB4xFrh8ZHXGLYNJqajUYsZz2hkqk3whhlXJro+zRGC9IHacfDoXr1wWGbke47TUUzffWV/Vq9mTFqh9RJdrgLLTLxpyOwK++g6SfAgMBAAECgYBhpMAV+2he90i6f+8ZK9fnZqdjUnTX8RvPabDZpSrBiJHY3javnipNZY7O3QelxplKJpiXyfcxSid/EVb8Zn9INvWHLmrg6dNSkkUuFxcuGxgDcxaDPFBdop3gYSlF6luk+xHF+ZPJfFoIhLZRk4NalzTdO9AaBLoWdVy9ZKw22QJBAN3XBOgghb3tT2K3G9rl6Qkw6RuN2LN6glC2saz2UzyuTwa8BkgT69MJwwQJypdgjEGeiRXXh5ID5SqwZ3M8P6MCQQClqwzV15pbDJyCt0U3OOHp0GxiAbxrNWj7mgwhvTQVvh1PZnSxC9Wf6ukQUZrjX9n5Ph1zB4w5RZb5XGFZVabVAkB5nxqnkL0CJYqyWiVBW27phREn9BpsO6waSC8c3mdAC2h0Dr164sEVkHKEUkHpwBrlFt+dQ+6llNEdGF1/Bfb1AkEAklKNt6ICN0DgS6PLgoa/ImBwGfAmlxpJa1AYPwR5qGqOQL7hei5VtDUrPLqW7xSN1VJDeIHGHB+WxRAO+3RnMQJBAJ9ucNZ2EBUGPq4mYmSgBzJK34bYjQZfWEgrGIYTCVzvnV4Lfp34oSVZW0ywhjwclbYeH3cgMf9yPlCAn7enEVw=";
    public static final String clientSignPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2EcJVFk614PYz4lPSQ6vH4dqXzcLU+iqRNfIT36OconABlFLHn8S/aAuYqFnD5Ffl7vvtqMXZmhi6/7o49ptVownDu9DruEq22zW5rXCQcuzhBpYDan+7ZdWaMNJmIU+ro3ILWOe4ZbvExhUFeJBkH/FykGCe5HlZGNdu2F7ilOgpixqlwmHkCSR4Lj/SdE6jS58ugTpTo0kuOHUqZ19FD1ODYa6DUuRyw7/YmpssYHLXoUjfYYj2PPdaQfNgtKlwePRsToV5mysnYT7bu+bFhHhKkAwKdWJ6Gmqrhew2URQII0tJJK3FMJFdj8T5nmEYYudooZ+PHryQhojeh7PVQIDAQAB";

    public static final AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.appPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

    public static final String charset="GBK";

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
        Logger.log(tranObj, "LOG_IO", Com.getIn);
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


        Logger.log(tranObj, "LOG_IO", Com.getOut);
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
    public static String getSYSQTLS(String sZDBH_U) {
        Logger.timerLog(Com.getIn);
        String sSYSQTLS = "";
        int XH = 1;
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.sysLogException(e);
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
            Logger.sysLogException(e);
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
                Logger.sysLogException(e);
                return "";
            }
        }


        if (cxtcs.getVALUE_().equals(Com.getDate())) {//日期相同，取流水并更新流水
            cxtcsKey.setKEY_UU(sZDBH_U+"_QTLSXH");
            try {
                cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
            } catch (Exception e) {
                Logger.sysLogException(e);
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
                    Logger.sysLogException(e);
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
                Logger.sysLogException(e);
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
            Logger.sysLogException(e);
            return "";
        }
        sqlSession.commit();
        sqlSession.close();
        Logger.timerLog("sSYSQTLS=" + sSYSQTLS);
        Logger.timerLog(Com.getOut);
        return sSYSQTLS;
    }

    /**
     * @return
     * @apiNote 获取系统终端编号
     */
    public static String getSYSZDBH_U() {
        String re = "";
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession=null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.sysLogException(e);
            return "";
        }

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
            Logger.sysLogException(e);
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
        } catch (IOException e) {
            Logger.sysLogException(e);
            return re;
        }

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
            Logger.sysLogException(e);
        }finally {
            sqlSession.close();
        }
        return re;
    }

    public static Date[] getRQSJ(Date date) throws ParseException {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd,HHmmss");
        String[] str = myFmt.format(date).split(",");


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date d1 = sdf1.parse(str[0]);

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        Date d2 = sdf2.parse(str[1]);
        Date[] reDate = {d1, d2};

        return reDate;
    }

//    public static void main(String[] args) {
//        Logger.log(tranObj,"LOG_DEBUG",);
//    }
}
