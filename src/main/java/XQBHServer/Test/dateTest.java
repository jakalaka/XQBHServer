package XQBHServer.Test;

import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTest {
    public static void main(String[] args) throws IOException, ParseException {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = dbAccess.getSqlSession();
        MDZSJKey mdzsjKey = new MDZSJKey();
        mdzsjKey.setFRDM_U("9999");
        mdzsjKey.setHTLS_U("SZD0000010000014");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dQTRQ_U = null;
        dQTRQ_U = formatter.parse("20171210");
        mdzsjKey.setHTRQ_U(dQTRQ_U);
        MDZSJMapper mdzsjMapper = sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = mdzsjMapper.selectByPrimaryKey(mdzsjKey);
        System.out.println(mdzsj.getHTRQ_U());
        System.out.println(mdzsj.getHTRQ_U().getTime());
        System.out.println(mdzsj.getJYSJ_U());
        System.out.println(mdzsj.getJYSJ_U().getTime());
        Date date = new Date();
        Date []d= getRQSJ(date);
        System.out.println(d[0].getTime());
        System.out.println(d[1].getTime());





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

}
