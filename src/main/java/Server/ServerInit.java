package Server;

import java.io.File;

import Server.Table.Mapper.DZDXXMapper;
import Server.Table.Model.DZDXX;
import Server.Table.Model.DZDXXKey;
import Server.Table.basic.DBAccess;
import Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import static Utils.PropertiesHandler.PropertiesReader.*;

/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {

        SqlSession sqlSession ;
        /*
        尝试连接数据库
         */
        DBAccess dbAccess = new DBAccess();
        try {
            sqlSession= dbAccess.getSqlSession();

            DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey = new DZDXXKey();
            dzdxxKey.setZDBH_U("SVR0000001");
            dzdxxKey.setFRDM_U("9999");
            DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        /*
        从userInfo中获取相关配置信息
         */
//        String LOGLV = readKeyFromXML(new File("src/main/resources/sysInfo.properties"), "LOGLV");
//        if (!"".equals(LOGLV) && LOGLV != null)
//            Com.LOGLV = LOGLV;
//        if (!"DEBUG".equals(LOGLV)&&!"ERR".equals(LOGLV)&&!"SYS".equals(LOGLV)&&!"IO".equals(LOGLV))
//        {
//            Logger.log("LOG_SYS","日志等级错误:"+LOGLV);
//            return false;
//        }

        Com.ERRMap = readAll(new File("src/main/resources/errmsg.properties"));


        return true;


    }
}