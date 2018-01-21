package XQBHServer.Utils.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String Date2StringofYear(Date date){
        String resoult="";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }
    public static String Date2StringofTime(Date date){
        String resoult="";
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }
}
