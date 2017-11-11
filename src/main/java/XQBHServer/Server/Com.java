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
    public static Map<String ,String > ERRMap;

    /**
     * 日志等级
     * SYS,ERR,IO,DEBUG
     */
    public static  String LOGLV="";

    public static final String getIn =">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    public static final String getOut ="<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
    public static  long tmpCount =0;
    public static DBAccess dbAccess = new DBAccess();
    /**
     * 获取后台流水=？？？
     * @return
     */
    public static boolean getHTLS(TranObj tranObj){
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        int iSCLSXH=0;
        String sZDBH_U=tranObj.getHead("ZDBH_U");
        String sHTLS_U=tranObj.getHead("HTLS_U");
        if (null==sHTLS_U||"".equals(sHTLS_U)) {
            DZDXXMapper dzdxxMapper=tranObj.sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey=new DZDXXKey();
            dzdxxKey.setZDBH_U(sZDBH_U);
            dzdxxKey.setFRDM_U("9999");
            DZDXX dzdxx=dzdxxMapper.selectByPrimaryKey(dzdxxKey);
            Date dSCJYRQ=dzdxx.getSCJYRQ();
            if (DateUtils.isSameDay(dSCJYRQ,tranObj.date)) {
                iSCLSXH=dzdxx.getSCLSXH();
                if (iSCLSXH>0)
                    iSCLSXH++;
                else
                    iSCLSXH=1;
                dzdxx.setSCLSXH(iSCLSXH);
            }
            else {
                iSCLSXH=1;
                dzdxx.setSCJYRQ(tranObj.date);
                dzdxx.setSCLSXH(iSCLSXH);

            }
            dzdxxMapper.updateByPrimaryKey(dzdxx);
            tranObj.setHead("HTLS_U","S"+sZDBH_U+String.format("%05d", iSCLSXH));
            Logger.log(tranObj, "LOG_IO", "后台流水[" + tranObj.getHead("HTLS_U") + "]");
        }
        else
            Logger.log(tranObj,"LOG_IO", "已经生成后台流水["+sHTLS_U+"],不再重复生成");


        Logger.log(tranObj,"LOG_IO", Com.getOut);
        return true;
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
//        Logger.log(tranObj,"LOG_DEBUG",);
//    }
}
