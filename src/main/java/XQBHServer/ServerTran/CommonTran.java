package XQBHServer.ServerTran;

import XQBHServer.ServerAPI.ComInit;
import XQBHServer.ServerAPI.UpdateMJYBW;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by Administrator on 2017/7/2 0002.
 */
@WebService()
public class CommonTran {
    @WebMethod
    public String Comtran(String XMLIn) {

        TranObj tranObj = new TranObj(XMLIn);
        if (false == tranObj.buildSUCCESS) {
            Tran.runERR(tranObj, "ERR003");
            return getOut(tranObj);
        }

        Logger.log(tranObj, "LOG_IO", "flLogLV = [" + tranObj.flLogLV + "] ");
        Logger.log(tranObj, "LOG_IO", "XMLIn=" + XMLIn);


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
        } catch (InstantiationException e) {
            Tran.runERR(tranObj, "SYSERR");
        } catch (InvocationTargetException e) {
            Tran.runERR(tranObj, "SYSERR");
        } catch (NoSuchMethodException e) {
            Tran.runERR(tranObj, "SYSERR");
        } catch (IllegalAccessException e) {
            Tran.runERR(tranObj, "SYSERR");
        } catch (ClassNotFoundException e) {
            Tran.runERR(tranObj, "ERR007");
        }
        if (true != callRe) {
            tranObj.sqlSession.rollback();
            Logger.log(tranObj, "LOG_ERR", "Call ERR");
        }
        return getOut(tranObj);
    }

    public static String getOut(TranObj tranObj) {
        boolean updateMJYBW = !"ERR006".equals(tranObj.getHead("CWDM_U"));
        if (updateMJYBW) {
            try {
                UpdateMJYBW.exec(tranObj);
            } catch (Exception e) {
                Logger.log(tranObj, "LOG_SYS", "更新交易报文表出错");
                Tran.runERR(tranObj, "ERR005");
            }
            tranObj.sqlSession.commit();
        }
        String XMLOut = "";
        XMLOut = XmlUtils.tranObj2XML(tranObj);
        if (null != tranObj.sqlSession)
            tranObj.sqlSession.close();

        Logger.log(tranObj, "LOG_IO", "XMLOut" + XMLOut + "\n\n\n");
        Logger.writte(tranObj);
        return XMLOut;
    }


}
