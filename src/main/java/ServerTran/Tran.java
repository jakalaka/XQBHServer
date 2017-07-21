package ServerTran;

import Server.Com;
import Server.Logger;
import Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public abstract class Tran {

    public abstract boolean exec(TranObj tranObj);
    public SqlSession sqlSession = null;
    public boolean execDo(TranObj tranObj) {

        try {
            DBAccess dbAccess = new DBAccess();
            sqlSession = dbAccess.getSqlSession();
            if (false == exec(tranObj)) {
                if (null == tranObj.CWDM_U || "".equals(tranObj.CWDM_U)) {
                    tranObj.CWDM_U = "COMERR";
                    tranObj.CWXX_U = "调用" + tranObj.JYM_UU + "交易时错误";

                } else {
                    //执行后返回的tranobj中已存在错误信息
                }
                return false;
            } else {
                tranObj.CWDM_U = "AAAAAA";
                sqlSession.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tranObj.CWDM_U = "COMERR";
            tranObj.CWXX_U = "调用" + tranObj.JYM_UU + "交易时错误";
            return false;
        } finally {
            //tmp tranObj.sqlSession.close();
        }
    }

    public static void runERR(TranObj tranObj, String CWDM_U) {
        tranObj.CWDM_U = CWDM_U;
        tranObj.CWXX_U = Com.ERRMap.get(CWDM_U);
        Logger.log("DEBUG", "tranObj.CWDM_U=" + tranObj.CWDM_U + "  tranObj.CWXX_U" + tranObj.CWXX_U);
    }
}
