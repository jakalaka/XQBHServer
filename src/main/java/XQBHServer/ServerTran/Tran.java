package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public abstract class Tran {

    public abstract boolean exec(TranObj tranObj);
    public boolean execDo(TranObj tranObj) {
        try {
            tranObj.sqlSession = Com.dbAccess.getSqlSession();

            if (false == exec(tranObj)) {
                if (null == tranObj.CWDM_U || "".equals(tranObj.CWDM_U)) {
                    tranObj.CWDM_U = "COMERR";
                    tranObj.CWXX_U = "调用" + tranObj.getHead("HTJYM_") + "交易时错误";
                    tranObj.sqlSession.rollback();

                } else {
                    //执行后返回的tranobj中已存在错误信息
                }
                return false;
            } else {
                tranObj.CWDM_U = "AAAAAA";
                tranObj.sqlSession.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tranObj.CWDM_U = "COMERR";
            tranObj.CWXX_U = "调用" + tranObj.getHead("HTJYM_") + "交易时错误";
            tranObj.sqlSession.rollback();
            return false;
        } finally {
            //tmp tranObj.sqlSession.close();
        }
    }

    public static void runERR(TranObj tranObj, String CWDM_U) {
        tranObj.CWDM_U = CWDM_U;
        tranObj.CWXX_U = Com.ERRMap.get(CWDM_U);
        if (null!=tranObj.sqlSession)
            tranObj.sqlSession.rollback();
        Logger.log(tranObj,"LOG_ERR", "rollback  tranObj.CWDM_U=" + tranObj.CWDM_U + "  tranObj.CWXX_U" + tranObj.CWXX_U);
    }
}
