package XQBHServer.Test;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXExample;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class DBtest {
    public static void main(String[] args) {

        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR", e);
            Logger.comLog("LOG_ERR", "ªÒ»°sqlsession ß∞‹");
            return;
        }



        DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setFRDM_U("9999");
        dzdxxKey.setZDBH_U("SVR000000010");

        DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);


        dzdxx.setZDDLZT("5");
        System.out.println(dzdxxMapper.updateByPrimaryKey(dzdxx));
        sqlSession.commit();
        sqlSession.close();




    }
}
