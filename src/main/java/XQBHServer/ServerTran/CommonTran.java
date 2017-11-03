package XQBHServer.ServerTran;

import XQBHServer.ServerAPI.ComInit;
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
        Logger.log("LOG_IO", "XMLIn=" + XMLIn);
        TranObj tranObj= new TranObj(XMLIn);
        if (false==tranObj.buildSUCCESS) {
            Tran.runERR(tranObj, "ERR002");
            return getOut(tranObj);
        }

        /*
        公共检查
         */
        try {
            if(false == ComInit.exec(tranObj))
            {
                Logger.log("LOG_SYS", "初始化失败");
                Tran.runERR(tranObj,"ERR001");
                return getOut(tranObj);
            }
        } catch (Exception e) {
            Logger.log("LOG_SYS", e.toString());
            Logger.log("LOG_SYS", "初始化失败");
            Tran.runERR(tranObj,"ERR001");
            return getOut(tranObj);
        }
        Class c = null;
        boolean callRe = false;
        Logger.log("LOG_IO", "JYM_UU=" + tranObj.JYM_UU);
        try {
            c = Class.forName("ServerTran." + tranObj.JYM_UU);
            Object obj = c.newInstance();
            Method m = obj.getClass().getMethod("execDo", TranObj.class);
            callRe = (Boolean) m.invoke(obj,tranObj);
        } catch (InstantiationException e) {
            Tran.runERR(tranObj,"SYSERR");
        } catch (InvocationTargetException e) {
            Tran.runERR(tranObj,"SYSERR");
        } catch (NoSuchMethodException e) {
            Tran.runERR(tranObj,"SYSERR");
        } catch (IllegalAccessException e) {
            Tran.runERR(tranObj,"SYSERR");
        } catch (ClassNotFoundException e) {
            Tran.runERR(tranObj,"SYSERR");
        }
        if(false==callRe)
        {
            Logger.log("LOG_ERR","Call ERR");
        }

        return getOut(tranObj);
    }
    public String getOut(TranObj tranObj){
        String XMLOut= XmlUtils.tranObj2XML(tranObj,"root");
        Logger.log("LOG_IO","XMLOut"+XMLOut+"\n\n\n");
        return XMLOut;
    }


}
