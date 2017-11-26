package XQBHServer.Server;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static XQBHServer.Utils.PropertiesHandler.PropertiesReader.readAll;


/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init() {

        SqlSession sqlSession;
        /*
        尝试连接数据库
         */

        try {
            sqlSession = Com.dbAccess.getSqlSession();

            DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey = new DZDXXKey();
            dzdxxKey.setZDBH_U("SVR0000001");
            dzdxxKey.setFRDM_U("9999");
            DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        } catch (Exception e) {
            Logger.sysLog("aaa");
            Logger.sysLog(e.toString());
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
//            Logger.log(tranObj,"LOG_SYS","日志等级错误:"+LOGLV);
//            return false;
//        }
        InputStream inputStream = Class.class.getResourceAsStream("/resources/errmsg.properties");
        Com.ERRMap = readAll(inputStream);


        return true;


    }
}