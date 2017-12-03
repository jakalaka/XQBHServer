package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.ServerAPI.ComInit;
import XQBHServer.ServerAPI.UpdateMJYBWAfterTran;
import XQBHServer.Utils.RSA.RSASignature;
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
        //��ǩ��
        String []str=XMLIn.split("sign=");
        try {
            if (true!=RSASignature.doCheck(str[0], str[1], Com.upPublicKey))
            {
                Logger.sysLog(XMLIn);
                Logger.sysLog("������ǩʧ��!!!");
                return "�Ƿ�����";
            }else {
                XMLIn=str[0];
            }
        }catch (Exception e)
        {
            Logger.sysLog(XMLIn);
            Logger.sysLogException(e);
            return "�Ƿ�����";
        }

        TranObj tranObj = new TranObj(XMLIn);
        if (false == tranObj.buildSUCCESS) {
            Tran.runERR(tranObj, "ERR003");
            return getOut(tranObj);
        }

        Logger.log(tranObj, "LOG_IO", "flLogLV = [" + tranObj.flLogLV + "] ");
        Logger.log(tranObj, "LOG_IO", "XMLIn=" + XMLIn);


        /*
        �������
         */

        if (true != ComInit.exec(tranObj)) {
            Logger.log(tranObj, "LOG_SYS", "��ʼ��ʧ��");
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
        String XMLOut = "";
        XMLOut = XmlUtils.tranObj2XML(tranObj);
        tranObj.bwOut = XMLOut;
        boolean updateMJYBW = !"ERR006".equals(tranObj.getHead("CWDM_U"));
        if (updateMJYBW) {

            if (true != UpdateMJYBWAfterTran.exec(tranObj)) {
                Logger.log(tranObj, "LOG_SYS", "���½��ױ��ı����");
                Tran.runERR(tranObj, "ERR005");
            }
        }

        if (null != tranObj.sqlSession) {
            tranObj.sqlSession.commit();
            tranObj.sqlSession.close();
        }
        Logger.log(tranObj, "LOG_IO", "XMLOut" + XMLOut + "\n\n\n");
        Logger.writte(tranObj);

        /*
        ��ǩ��
         */
        String signstr= RSASignature.sign(XMLOut,Com.rePrivatebKey);
        XMLOut=XMLOut+"sign="+signstr;
        return XMLOut;
    }


}
