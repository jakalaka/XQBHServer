package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public abstract class Tran {


    public abstract boolean exec(TranObj tranObj) throws ParseException;

    public boolean execDo(TranObj tranObj) {
        try {

            if (false == exec(tranObj)) {
                if (null == tranObj.getHead("CWDM_U") || "".equals(tranObj.getHead("CWDM_U"))) {
                    tranObj.setHead("CWDM_U", "COMERR");
                    tranObj.setHead("CWXX_U", "调用" + tranObj.getHead("HTJYM_") + "交易时错误");

                }
                return false;
            } else {
                tranObj.setHead("CWDM_U", "AAAAAA");
                tranObj.setHead("CWXX_U", "");
                return true;
            }
        } catch (Exception e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            if (null == tranObj.getHead("CWDM_U") || "".equals(tranObj.getHead("CWDM_U"))) {
                tranObj.setHead("CWDM_U", "COMERR");
                tranObj.setHead("CWXX_U", "调用" + tranObj.getHead("HTJYM_") + "交易时异常");
            }
            return false;
        }

    }

    public static void runERRFinal(TranObj tranObj, String CWDM_U, String CWXX_U,String sCallSource) {

        if (null!=tranObj.getHead("CWDM_U")&&!"".equals(tranObj.getHead("CWDM_U")))
        {
            Logger.log(tranObj, "LOG_ERR", "overwrite CWDM_U "+tranObj.getHead("CWDM_U"));

        }
        if (null!=tranObj.getHead("CWXX_U")&&!"".equals(tranObj.getHead("CWXX_U")))
        {
            Logger.log(tranObj, "LOG_ERR", "overwrite CWXX_U "+tranObj.getHead("CWXX_U"));

        }
        tranObj.setHead("CWDM_U", CWDM_U);
        tranObj.setHead("CWXX_U", CWXX_U);


        Logger.log(tranObj, "LOG_ERR", "CALL ERR's place:"+sCallSource);
        Logger.log(tranObj, "LOG_ERR", "rollback  tranObj.CWDM_U=" + tranObj.getHead("CWDM_U") + "  tranObj.CWXX_U" + tranObj.getHead("CWXX_U"));
    }

    public static void runERR(TranObj tranObj, String CWDM_U) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);

        StringBuilder builder = new StringBuilder();
        builder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        builder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");
        String sCallSource=builder.toString();

        runERRFinal(tranObj, CWDM_U, sCWXX_U,sCallSource);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());

        StringBuilder builder = new StringBuilder();
        builder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        builder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");
        String sCallSource=builder.toString();

        runERRFinal(tranObj, CWDM_U, sCWXX_U,sCallSource);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1, Object parms2) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms2.toString());

        StringBuilder builder = new StringBuilder();
        builder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        builder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");
        String sCallSource=builder.toString();

        runERRFinal(tranObj, CWDM_U, sCWXX_U,sCallSource);
    }

    public static void runERR(TranObj tranObj, String CWDM_U, Object parms1, Object parms2, Object parms3) {
        String sCWXX_U = Com.ERRMap.get(CWDM_U);
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms1.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms2.toString());
        sCWXX_U = sCWXX_U.replaceFirst("%s", parms3.toString());



        StringBuilder builder = new StringBuilder();
        builder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        builder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");
        String sCallSource=builder.toString();

        runERRFinal(tranObj, CWDM_U, sCWXX_U,sCallSource);
    }


}
