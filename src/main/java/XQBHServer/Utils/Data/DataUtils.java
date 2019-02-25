package XQBHServer.Utils.Data;

import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DataUtils {
    /**
     * @param date 年月日DATE
     * @return 字符串
     */
    public static String Date2StringofYear(Date date){
        String resoult="";
        if (date==null)
            return resoult;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }

    /**
     * @param date 时分秒DATE
     * @return 字符串
     */
    public static String Date2StringofTime(Date date){
        String resoult="";
        if (date==null)
            return resoult;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }

    /**
     * @param tranObj
     * @param RIQI_U 日期字符串
     * @return 年月日的DATE
     * @throws ParseException
     */
    public static Date YearString2Date(TranObj tranObj,String RIQI_U) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date result=null;
        try {
            result=formatter.parse(RIQI_U);
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "TIMEER");
            throw e;
        }
        return result;

    }

    /**
     * @param tranObj
     * @param RIQI_U 时间字符串
     * @return 时分秒DATE
     * @throws ParseException
     */
    public static Date TimeString2Date(TranObj tranObj,String RIQI_U) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
        Date result=null;
        try {
            result=formatter.parse(RIQI_U);
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "TIMEER");
            throw e;
        }
        return result;

    }

    /**
     * @param tranObj
     * @param date 完整的DATE
     * @return 日期、时间的DATE
     * @throws ParseException
     */
    public static Date[] splitDate(TranObj tranObj, Date date) throws ParseException {
        Date[] reDate = null;
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd,HHmmss");
            String[] str = myFmt.format(date).split(",");


            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date d1 = sdf1.parse(str[0]);

            SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
            Date d2 = sdf2.parse(str[1]);
            reDate = new Date[]{d1, d2};

        }catch (Exception e)
        {
            if(tranObj!=null) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "TIMEER");
            }
            throw e;
        }

        return reDate;
    }

    /**
     * @param map
     * @param sKey
     * @return 非null的字符串
     */
    public static String getValue(Map map, String sKey) {
        String resoult="";
        if (map.get(sKey)==null)
            return resoult;
        resoult=map.get(sKey).toString();
        return resoult;
    }
}
