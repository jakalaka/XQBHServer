package XQBHServer.ServerAPI;


import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.TranObj;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ComInit {
    public static boolean exec(TranObj tranObj) throws Exception {
        DBAccess dbAccess=new DBAccess();
        SqlSession sqlSession=dbAccess.getSqlSession();
        /*
        以下添加拆报合法性检查如终端信息，终端校验码等
         */
        String ZDBH_U = tranObj.HeadMap.get("ZDBH_U").toString();
        String ZDJYM_ = tranObj.HeadMap.get("ZDJYM_").toString();
        if (false== JCZDHFX.exec(sqlSession, ZDBH_U, ZDJYM_))
            return returnFalse(sqlSession);

        /*
        插入交易报文
         */
        if(false == InsertMJYBW.exec(sqlSession,tranObj.HeadMap))
            return false;

        sqlSession.commit();
        sqlSession.close();
        return true;

    }
    public static boolean returnFalse( SqlSession sqlSession){
        sqlSession.rollback();
        sqlSession.close();
        return false;
    }
}
