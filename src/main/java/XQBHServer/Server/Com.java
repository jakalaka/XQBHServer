package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
     * 数据库DBAccess
     */
    public static DBAccess dbAccess = new DBAccess();


    /**
     * 支付宝网关
     */
    public static String alipayGateway = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝APPID
     */
    public static String alipayAppid = "2016080500173689";

    /**
     * 支付宝私钥
     */
    public static String alipayPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClV7IkP/AOnHD+rL/+sAZjsowKXCSiLAKXILoIO/TrDYChNbmxrEZ3AsGwshRIUre332khPWfcKG04rwVns6RnYj4A6BaJ2WwrPHnA0NllKXCzq0RrPxaTFltlcXQm2funbIJjxCwlHXrJzzzi7dAsu1QIf5EYQbELN/dHIAtarZge8fbAsBkkqYD3xSMuZq0K0kmhybCqSWjYFFfvlWVyR+7i4K3dPiUeC69OHdhfsaP+BtKoPgtlXxtQN/H4yn88oIdL05nXwqLVrqCr53UVXssbnZ0iwTw5X8r6/RYdMSGOAo8aQPmtsP8HBfT0Y0xFVjODo2FfYmtFXjCsYtBDAgMBAAECggEAd8bTshbvXGZQJO4YF/SEbGzHrimaEDE5nymCGrz0a+LYL/CvmNpoIYbJsasPrmTd2kHp8r59IqvWk52WmM02Z/5vVFDNIwdyqM+ik9+33OTsU/vaEKcfP2aOxyotLofzGhItUwClyi1U6iVKwkns6Lq74XwoLB5KlwnwBIJeN00nji3jacUOahZPn8472In5dtM+PqKtm6vJFVYpfwtTKoxblmpOqsTTSAqx50o3Y/ilVxvZo1f34xo+gXkGQ/i4NH8qRgg+L/FOG4VKAATDP72l8tpjUOxpbkuzhattP6MBCeY+RWg+9xlt174zqkGB19HBSJorqicgYXaqnLghwQKBgQDbzHf01vvU5tGGBmaOVrjWvhqvfE1W9CEpQWUGH576Vc3IwxUuMqA6ed4t8N51THYAVNh6kcDwQoBNSdBERabwQoxVaazzEhtnLOgzZPP5PE04GfeWdlzglUDH5iIzB1sa07gKSUNrsTP6PlR+f/ijdVX/eCaFBOJawoN8jqU3GwKBgQDAkydTYUzzeJfY1Mfh1ACmhT7EmvHFBnNIO01Ls65z90YG9sLHcQ1wD6p8ag8O93PBFP8729AbW157vdmgu0abWc35Nznuyg5Xpq+p4N9oPTqyYuaBS7NaDU5YghBBC0QWk22RMuCsCP8CO4Z1LgKA0RpcPIQ8YEQA2VmtvDEV+QKBgBn382hDCifiSXj7QpyolFgSx9ZZ6k1OtKhKKKRrkf3jq1d/7P2zT4j5Iw3semwDZ6GsZJM+qzv3r27yPKAEVq/mPOxOeveQ+RncjWadE9IrlLf/IWhufZSvLaMwhnPe952YzBKzCzsrCYgUWylC915gm5N+X5axuAifGKfbtptnAoGADxp7dxqmgmHu5t6pXpjWBDlnFtxgIefDmuKryUgqYoX+RAWOeT3wo91WrbNTwwS9W2NeMT+oLr0Xx/S34NdPTlfYw7cFIClQvqcgF96/JtnGhL8k/PcG9gUdI+vvgmpzyKF/cmffHx8FgRNSFFarc5byzlEgvet+6eIiGnIsF2ECgYEAvaBB3ncRzXHFaBRD6k/VSzJEA+BWEeD0gplBMATRSieofwIEi9gunD9+DAkc3WIrXEMvCMaAtasjcx2Z/HHEwuPm+T1otyJZZJWVLQrUs8mcG3xLOoYx6XTtooT1q6kdOCa5d5qVQ2IOWQzCdG2y44t2TNgDkP3Xvo960sXCfeY=";

    /*
     * 支付宝公钥
     */
    public static  String alipayPulicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtm13QopHlAfSUtMkRHy/fUt+sV0dFaLJXyX5y2j/Ic/YxctgrrzkHt5eL5KTrPFKvhue7t/cEV+adblAcw1JWVRjci9xu7LMRgU+qeKqSowr3VYZKLpQJTlJsULEFsDn5b6DQyX7xPB4CpIGtnHizqCthwvKP/P5e0rYDcqsU4Ccmn1/DAxboV/tFpz/UO7kMA6G8tCtUKwn8tNITEQd6r2DWjidg3tNtg4WbvjxtzCU43HnvREqonDnO6R+InSP+9VBMqz+5b0QcQ71ql0GpS8ecZhhGrZRTTJqowxtZ2Grs44dAzP4G514LC2+3Z8+LeWbsB6Mk3an0AhpUbGXcQIDAQAB";


    public static  String alipaySys_service_provider_id="";
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
            DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
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
            dzdxxMapper.updateByPrimaryKey(dzdxx);
            tranObj.setHead("HTLS_U", "S" + sZDBH_U + String.format("%05d", iSCLSXH));
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

//    public static void main(String[] args) {
//        Logger.log(tranObj,"LOG_DEBUG",);
//    }
}
