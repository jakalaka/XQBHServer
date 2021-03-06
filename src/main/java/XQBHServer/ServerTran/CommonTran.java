package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.ServerAPI.ComInit;
import XQBHServer.ServerAPI.InsertMJYBWAfterTran;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;

import java.lang.reflect.Method;


/**
 * Created by Administrator on 2017/7/2 0002.
 */
public class CommonTran {
    public String Comtran(String XMLIn) {


        TranObj tranObj = new TranObj(XMLIn);
        if (false == tranObj.buildSUCCESS) {
            Tran.runERR(tranObj, "ERR003");
            return getOut(tranObj);
        }
        Logger.log(tranObj, "LOG_SYS", "XMLIn=" + XMLIn);








        /*
        公共检查
         */

        if (true != ComInit.exec(tranObj)) {
            Logger.log(tranObj, "LOG_SYS", "初始化失败");
            if ("".equals(tranObj.getHead("CWDM_U")))
                Tran.runERR(tranObj, "ERR001");
            return getOut(tranObj);
        }

        Class c = null;
        boolean callRe = false;
        Logger.log(tranObj, "LOG_IO", "JYM_UU=" + tranObj.getHead("HTJYM_"));
        try {
            c = Class.forName("XQBHServer.ServerTran." + tranObj.getHead("HTJYM_"));
            Object obj = c.newInstance();
            Method m = obj.getClass().getMethod("execDo", TranObj.class);
            callRe = (Boolean) m.invoke(obj, tranObj);
        } catch (ClassNotFoundException e) {
            Tran.runERR(tranObj, "ERR007");
        } catch (NoClassDefFoundError e) {
            Tran.runERR(tranObj, "ERR007");
        } catch (Exception e) {
            Tran.runERR(tranObj, "SYSERR");//属于实例化抽象类错误
        }
        if (true != callRe && !tranObj.commitFlg) {//不知状态的交易，需人工对账
            Logger.log(tranObj, "LOG_IO", "rollback");

            tranObj.sqlSession.rollback();
            Logger.log(tranObj, "LOG_IO", "rollback");

            Logger.log(tranObj, "LOG_ERR", "Call ERR");
        }


        return getOut(tranObj);
    }

    public static String getOut(TranObj tranObj) {

        String XMLOut = "";
        XMLOut = XmlUtils.tranObj2XML(tranObj);
        tranObj.bwOut = XMLOut;

        InsertMJYBWAfterTran.exec(tranObj);//直接插入

        if (null != tranObj.sqlSession) {
            tranObj.sqlSession.commit();
            tranObj.sqlSession.close();
        }
        Logger.log(tranObj, "LOG_SYS", "XMLOut" + XMLOut);
        Logger.log(tranObj, "LOG_IO", Com.TRAN_OUT);

        Com.logFile.remove(tranObj.loggerFile);
        return XMLOut;



    }


}
