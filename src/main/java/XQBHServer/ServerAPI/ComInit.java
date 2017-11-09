package XQBHServer.ServerAPI;


import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerTran.TranObj;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ComInit {
    public static boolean exec(TranObj tranObj) throws Exception {

        /*
        插入交易报文
         */
        if(false == InsertMJYBW.exec(tranObj))
            return false;
        tranObj.sqlSession.commit();

        /*
        以下添加拆报合法性检查如终端信息，终端校验码等
         */

        if (false== JCZDHFX.exec(tranObj))
            return false;


        /*
        获取日志等级
         */



        return true;

    }
}
