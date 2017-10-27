package Utils.log;

import Server.Table.Mapper.CXTCSMapper;
import Server.Table.Model.CXTCS;
import Server.Table.Model.CXTCSKey;
import Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * Created by Administrator on 2017/7/2 0002.
 */
public class Logger {
    public static SqlSession sqlSession;

    public static boolean log(String logLevel, String Msg) {
        DBAccess dbAccess = new DBAccess();
        try {
            sqlSession = dbAccess.getSqlSession();

            CXTCSMapper CXTCSMapper = sqlSession.getMapper(CXTCSMapper.class);
            CXTCSKey CXTCSKey = new CXTCSKey();
            CXTCSKey.setKEY_UU("LogLV");
            CXTCSKey.setFRDM_U("9999");
            CXTCS cxtcs = CXTCSMapper.selectByPrimaryKey(CXTCSKey);

            if ("DEBUG".equals(cxtcs.getVALUE_())) {
                LogUtil.logger = LogUtil.setLoggerHanlder(Level.CONFIG);
            } else if ("IO".equals(cxtcs.getVALUE_())) {
                LogUtil.logger = LogUtil.setLoggerHanlder(Level.INFO);
            } else if ("ERR".equals(cxtcs.getVALUE_())) {
                LogUtil.logger = LogUtil.setLoggerHanlder(Level.WARNING);
            } else if ("SYS".equals(cxtcs.getVALUE_())) {
                LogUtil.logger = LogUtil.setLoggerHanlder(Level.SEVERE);
            }

            if ("LOG_DEBUG".equals(logLevel)) {
                LogUtil.logger.config(Msg);
            } else if ("LOG_IO".equals(logLevel)) {
                LogUtil.logger.info(Msg);
            } else if ("LOG_ERR".equals(logLevel)) {
                LogUtil.logger.warning(Msg);
            } else if ("LOG_SYS".equals(logLevel)) {
                LogUtil.logger.severe(Msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Handler h : LogUtil.logger.getHandlers()) {
            h.close();   //must call h.close or a .LCK file will remain.
        }
        return true;
    }
}
