package ServerTran;

import Server.Logger;
import Server.XmlUtils;
import ServerAPI.ComInit;

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
        Logger.log("DEBUG", "XMLIn=" + XMLIn);
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
                Logger.log("DEBUG", "初始化失败");
                Tran.runERR(tranObj,"ERR001");
                return getOut(tranObj);
            }
        } catch (Exception e) {
            Logger.log("DEBUG", e.toString());
            Logger.log("DEBUG", "初始化失败");
            Tran.runERR(tranObj,"ERR001");
            return getOut(tranObj);
        }
        Class c = null;
        boolean callRe = false;
        Logger.log("DEBUG", "JYM_UU=" + tranObj.JYM_UU);
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
            Logger.log("ERR","Call ERR");
        }

        return getOut(tranObj);
    }
    public String getOut(TranObj tranObj){
        String reXml=XmlUtils.tranObj2XML(tranObj,"root");
        Logger.log("DEBUG",reXml);
        return reXml;
    }


}
