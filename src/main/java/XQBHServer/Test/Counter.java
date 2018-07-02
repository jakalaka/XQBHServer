package XQBHServer.Test;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * Created by yangjinfeng02 on 2016/6/4.
 */
public class Counter implements Runnable {


    private String counterName;

    public Counter(String counterName) {
        this.counterName = counterName;
    }

    public void run() {
        MDC.put("logFileName","logs/"+ counterName);
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CXTCSMapper cxtcsMapper = sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("AlipayTOut");
        CXTCS cxtcs = null;
        logback.logger.info("before{}", counterName);

        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logback.logger.info("after{}", counterName);

        MDC.remove("logFileName");
    }
}