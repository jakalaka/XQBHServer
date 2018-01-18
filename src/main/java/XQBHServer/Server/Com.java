package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
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
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class Com {
    public static Map<String, String> ERRMap;

    public static boolean cancelThreadBusy =false;
    public static long cancelThreadLoopTime =1000;
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
     * 支付宝私钥_测试专用
     */
    public static final String alipayPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClV7IkP/AOnHD+rL/+sAZjsowKXCSiLAKXILoIO/TrDYChNbmxrEZ3AsGwshRIUre332khPWfcKG04rwVns6RnYj4A6BaJ2WwrPHnA0NllKXCzq0RrPxaTFltlcXQm2funbIJjxCwlHXrJzzzi7dAsu1QIf5EYQbELN/dHIAtarZge8fbAsBkkqYD3xSMuZq0K0kmhybCqSWjYFFfvlWVyR+7i4K3dPiUeC69OHdhfsaP+BtKoPgtlXxtQN/H4yn88oIdL05nXwqLVrqCr53UVXssbnZ0iwTw5X8r6/RYdMSGOAo8aQPmtsP8HBfT0Y0xFVjODo2FfYmtFXjCsYtBDAgMBAAECggEAd8bTshbvXGZQJO4YF/SEbGzHrimaEDE5nymCGrz0a+LYL/CvmNpoIYbJsasPrmTd2kHp8r59IqvWk52WmM02Z/5vVFDNIwdyqM+ik9+33OTsU/vaEKcfP2aOxyotLofzGhItUwClyi1U6iVKwkns6Lq74XwoLB5KlwnwBIJeN00nji3jacUOahZPn8472In5dtM+PqKtm6vJFVYpfwtTKoxblmpOqsTTSAqx50o3Y/ilVxvZo1f34xo+gXkGQ/i4NH8qRgg+L/FOG4VKAATDP72l8tpjUOxpbkuzhattP6MBCeY+RWg+9xlt174zqkGB19HBSJorqicgYXaqnLghwQKBgQDbzHf01vvU5tGGBmaOVrjWvhqvfE1W9CEpQWUGH576Vc3IwxUuMqA6ed4t8N51THYAVNh6kcDwQoBNSdBERabwQoxVaazzEhtnLOgzZPP5PE04GfeWdlzglUDH5iIzB1sa07gKSUNrsTP6PlR+f/ijdVX/eCaFBOJawoN8jqU3GwKBgQDAkydTYUzzeJfY1Mfh1ACmhT7EmvHFBnNIO01Ls65z90YG9sLHcQ1wD6p8ag8O93PBFP8729AbW157vdmgu0abWc35Nznuyg5Xpq+p4N9oPTqyYuaBS7NaDU5YghBBC0QWk22RMuCsCP8CO4Z1LgKA0RpcPIQ8YEQA2VmtvDEV+QKBgBn382hDCifiSXj7QpyolFgSx9ZZ6k1OtKhKKKRrkf3jq1d/7P2zT4j5Iw3semwDZ6GsZJM+qzv3r27yPKAEVq/mPOxOeveQ+RncjWadE9IrlLf/IWhufZSvLaMwhnPe952YzBKzCzsrCYgUWylC915gm5N+X5axuAifGKfbtptnAoGADxp7dxqmgmHu5t6pXpjWBDlnFtxgIefDmuKryUgqYoX+RAWOeT3wo91WrbNTwwS9W2NeMT+oLr0Xx/S34NdPTlfYw7cFIClQvqcgF96/JtnGhL8k/PcG9gUdI+vvgmpzyKF/cmffHx8FgRNSFFarc5byzlEgvet+6eIiGnIsF2ECgYEAvaBB3ncRzXHFaBRD6k/VSzJEA+BWEeD0gplBMATRSieofwIEi9gunD9+DAkc3WIrXEMvCMaAtasjcx2Z/HHEwuPm+T1otyJZZJWVLQrUs8mcG3xLOoYx6XTtooT1q6kdOCa5d5qVQ2IOWQzCdG2y44t2TNgDkP3Xvo960sXCfeY=";

    /*
     * 支付宝公钥_测试专用
     */
    public static final String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtm13QopHlAfSUtMkRHy/fUt+sV0dFaLJXyX5y2j/Ic/YxctgrrzkHt5eL5KTrPFKvhue7t/cEV+adblAcw1JWVRjci9xu7LMRgU+qeKqSowr3VYZKLpQJTlJsULEFsDn5b6DQyX7xPB4CpIGtnHizqCthwvKP/P5e0rYDcqsU4Ccmn1/DAxboV/tFpz/UO7kMA6G8tCtUKwn8tNITEQd6r2DWjidg3tNtg4WbvjxtzCU43HnvREqonDnO6R+InSP+9VBMqz+5b0QcQ71ql0GpS8ecZhhGrZRTTJqowxtZ2Grs44dAzP4G514LC2+3Z8+LeWbsB6Mk3an0AhpUbGXcQIDAQAB";


    public static final String alipaySys_service_provider_id = "";


    public static final String upPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhGq+VmOPBeSJ1BUHbnS1iiPVdrXsmqXg17qIiV/7dcff/OwbsXbbvz77yN50iZNNeiCG4Rj/TK5oTQgTjbLikAfEtPLROGHh5mH3eyefHYtuIETUXGstXm6+xMFHFvuH91yjOoVH0C55f6JpLhcy37/p3WzWGbvWyEVoYWs6VBt8bWYd/rqHadHpk+9II+/qe1S6I0a3qGdtOMpSucjSSo5EZd6ByVb5V5Vv3YEcF2NISc3gue0O2InhidEA5LfMNaMm+ZVmo6d+RaApnL8B8bcvZpn51l7d6lEZ/GbSaPeZGMQq5xZvdo3SFa92GmdX0OmnVS7CVGoHdLOnpkIerwIDAQAB";
    public static final String rePrivatebKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXpHXRZcJ0LAN2GjNJVWxhUKQjq+esiAah9HbLsn53b/HuxGg+4d/vPucwR+v+2VcDdkfo/BYo4zb7sqvQHrMxXgdhjLAOYzsPHEEaeHanFAptNdwfFkdEU2Jh4Mq3yC5P9ATf6DlS5MTkgKhiu9iEZXs9sAOsEppMzDx36jF7RjubWxRHCfDu/QBmuTQ9wHFjipqiQSIbjxnSC3O684Et+w4QV+qtwFPuGnxz4ZdSXB9XfHsdkOS9dRY9wSlrNiN7oouN4aQPgRqZtAYMeHsgomIgplcKohNga/vvz+Eu2S0MZ9zJA+R77JAt8xAU227s7fNT6IWtVht9dVT2mBl5AgMBAAECggEAcrtizPCyBFV8tPGz1gtEughaeAbLfXVZl1WwQAQnvVQ8dwuxV8psNMaiApyYCLUGKb9nPvbkCUGISz1u00M3DuX2ra7VUb1wZMla1J1KILWAAgsbJYYkm6R/LlD278AMkj+tJH++R2KBOeXCARnhAqMJcK3oyVs11V89bPkLQRnCZ2FwN9bxIFf4AmgxCQEmYO1Fp9kDhK0Vvz2mxSmWQ0cSqQ/4ZymkNQwtj02mq7vT9zEgybJOxsXHbyVP48oXTEL38X26rYGyw+il/DbdX/fMDelFactMST/Njug1WtjsLWuiiVClwn4lHBWteMs+FcmtCaBd3jtbTEuhtcLzNQKBgQDUcHCnIOp8xTXkUSM/3pmg4oQNlQmmt6xGYEeLUD5dYRRNV7xCo642T9qVfCjTQMAXqWSJX3Yv2KvniXKfB5Pr7IXtSDpVidCAuX33nYBIOAAEXpVe5rzUVzDqYKOu+wCTaTJHdIoCWFxLuXME/e5wMqbsZ7yzxW9jBGtXRTU5dwKBgQC2vJ4MmNSNIRfmvrWcY/kHQCAMG9mWptG9aa7vsolXV4MLAOfFGbArWQjRDdVJHl/o/0NuXUo/tkf8dbgHHOMfuwvZJuu4A3hwLB5RcV2pTdnfN9RJ43L4pJXs6jM4rSvy9/VfhzXjYefM6ckoIM8WVlNsh/P140onhbrMSEsAjwKBgQDBZtwoSz8u+BtoELYWn4EdhxrRgq2WqgCc0WpQ2mzsdkUVZJpv0LP5nhlRngdMy9YVtJFivxXJ2WgnEmrXfP6d+may7t6soRrBFh4oH6t6FAFv+Ovjygx/BCH0PqbF1p1flpW/l8lM+USNa/faw5Mfcf2lm4uf3k7Zxlwyxz79YQKBgBIh3zDyzUx/tyZvUAgHp4M/qq5o6QtY3EEGRpSES7p99YPXACh/pwp2GDjePYa22997zM4bta1iwfvcFnmmNSdF4SQYB5XHymCfEBcWv6t4DSFoIcl/Jz5i3nmWt6RtEopWbmOfQAqjAWRE4m/36XSW9kozp1KDzizjyShSGBcjAoGAfo/tLYnSTPL8grRDStGjOI/+yQ2qaySJps3lencSSi6gyKNFEe3PL589qTSu9Kg7IUzV8ThSKfssMmrDzoY2wFGfiqaaK//jE3M5epF/c2vRtXVYkD0oopewBhS96i0UvKMyuBENIRzdDLgJmP19ii5jhRzSzGL4xWMTnBkO+Bs=";

    public static final AlipayClient alipayClient= new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

    /**
     * 获取后台流水=S+10位终端编号+5位序号
     *
     * @return
     */
    public static boolean getHTLS(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        int iSCLSXH = 0;
        String sZDBH_U = tranObj.getHead("ZDBH_U");
        String sHTLS_U = tranObj.getHead("HTLS_U");
        if (null == sHTLS_U || "".equals(sHTLS_U)) {
            DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey = new DZDXXKey();
            dzdxxKey.setZDBH_U(sZDBH_U);
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
                iSCLSXH = dzdxx.getSCLSXH();
                if (iSCLSXH > 0)
                    iSCLSXH++;
                else
                    iSCLSXH = 1;
                dzdxx.setSCLSXH(iSCLSXH);
            } else {
                iSCLSXH = 1;
                dzdxx.setSCJYRQ(tranObj.date);
                dzdxx.setSCLSXH(iSCLSXH);

            }
            try {
                dzdxxMapper.updateByPrimaryKey(dzdxx);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "SQLUPD");
                return false;
            }
            tranObj.setHead("HTLS_U", "S" + sZDBH_U + String.format("%07d", iSCLSXH));
            Logger.log(tranObj, "LOG_IO", "后台流水[" + tranObj.getHead("HTLS_U") + "]");
        } else
            Logger.log(tranObj, "LOG_IO", "已经生成后台流水[" + sHTLS_U + "],不再重复生成");


        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

    /**
     * 获取后台机器日期
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
     * 自动生成后台的前台流水
     *
     * @return
     */
    public static String getSYSQTLS() {
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
        cxtcsKey.setKEY_UU("SYSQTRQ");
        cxtcsKey.setFRDM_U("9999");
        CXTCS cxtcs = null;
        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        } catch (Exception e) {
            Logger.sysLogException(e);
            return "";
        }
        if (cxtcs.getVALUE_().equals(Com.getDate())) {//日期相同，取流水并更新流水
            cxtcsKey.setKEY_UU("SYSQTLSXH");
            try {
                cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
            } catch (Exception e) {
                Logger.sysLogException(e);
                return "";
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
        sSYSQTLS = "CSVR00001" + String.format("%07d", XH);

        XH++;
        cxtcs.setKEY_UU("SYSQTLSXH");
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
        Logger.timerLog("sSYSQTLS="+sSYSQTLS);
        Logger.timerLog(Com.getOut);
        return sSYSQTLS;
    }
    public static Date[] getRQSJ(Date date) throws ParseException {
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd,HHmmss");
        String []str=myFmt.format(date).split(",");


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date d1 = sdf1.parse(str[0]);

        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        Date d2 = sdf2.parse(str[1]);
        Date []reDate={d1,d2};

        return reDate;
    }

//    public static void main(String[] args) {
//        Logger.log(tranObj,"LOG_DEBUG",);
//    }
}
