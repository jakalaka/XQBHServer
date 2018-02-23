package XQBHServer.Utils.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DataUtils {
    public static String Date2StringofYear(Date date){
        String resoult="";
        if (date==null)
            return resoult;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }
    public static String Date2StringofTime(Date date){
        String resoult="";
        if (date==null)
            return resoult;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        resoult = df.format(date);
        return resoult;
    }
    public static String getValue(Map map, String sKey) {
        String resoult="";
        if (map.get(sKey)==null)
            return resoult;
        resoult=map.get(sKey).toString();
        return resoult;
    }
}
