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
                if (null == tranObj.getHead("CWDM_U") || "".equals(tranObj.getHead("CWDM_U"))) {
                    tranObj.setHead("CWDM_U", "COMERR");
                    tranObj.setHead("CWXX_U", "调用" + tranObj.getHead("HTJYM_") + "交易时错误");
//                    tranObj.sqlSession.rollback();

                } else {
                    //执行后返回的tranobj中已存在错误信息
                }
                return false;
            } else {
                tranObj.setHead("CWDM_U", "AAAAAA");
                tranObj.setHead("CWXX_U", "");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tranObj.setHead("CWDM_U", "COMERR");
            tranObj.setHead("CWXX_U", "调用" + tranObj.getHead("HTJYM_") + "交易时错误");
//            tranObj.sqlSession.rollback();
            return false;
        } finally {
            //tmp tranObj.sqlSession.close();
        }
    }

    public static void runERRFinal(TranObj tranObj, String CWDM_U, String CWXX_U) {

        tranObj.setHead("CWDM_U", CWDM_U);
        tranObj.setHead("CWXX_U", CWXX_U);
//        if (null!=tranObj.sqlSession)
//            tranObj.sqlSession.rollback();
        Logger.log(tranObj, "LOG_ERR", "rollback  tranObj.CWDM_U=" + tranObj.getHead("CWDM_U") + "  tranObj.CWXX_U" + tranObj.getHead("CWXX_U"));
    }

    public static void runERR(TranObj tranObj, String CWDM_U) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        runERRFinal(tranObj, CWDM_U, sCWXX_U);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());
        runERRFinal(tranObj, CWDM_U, sCWXX_U);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1, Object parms2) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms2.toString());
        runERRFinal(tranObj, CWDM_U, sCWXX_U);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1, Object parms2, Object parms3) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms2.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms3.toString());
        runERRFinal(tranObj, CWDM_U, sCWXX_U);
    }


}
