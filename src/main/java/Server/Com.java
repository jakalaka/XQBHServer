package Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class Com {
    public static Map<String ,String > ERRMap;

    /**
     * 日志等级
     *
     */
    public static  String LOGLV="";

    /**
     * 获取后台流水=？？？
     * @return
     */
    public static String getHTLS(){
        String HTLS_U="";
        return HTLS_U;
    }
    /**
     * 获取后台机器日期
     * @return
     */
    public static String getDate(){
        String Date="";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Date =df.format(new Date());
        return Date;
    }
    /**
     * 获取后台机器时间
     * @return
     */
    public static String getTime(){
        String Time="";
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");//设置日期格式
        Time =df.format(new Date());
        return Time;
    }

//    public static void main(String[] args) {
//        System.out.println();
//    }
}
