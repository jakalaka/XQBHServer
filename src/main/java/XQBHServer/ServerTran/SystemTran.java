package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.ServerAPI.ComInit;
import XQBHServer.ServerAPI.InsertMJYBWAfterTran;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.CallUtils.CallThread;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/2 0002.
 */
public class SystemTran {
    public static boolean call(Map<String, Map> map_In, CallResult callResult) throws Exception {
        return call(null, map_In, callResult);
    }

    public static boolean call(TranObj tranObj, Map<String, Map> map_In, CallResult callResult) throws Exception {

        if (!map_In.get("head").containsKey("HTJYM_"))
            throw new Exception("δ�����̨������");


//        if (!map_In.get("head").containsKey("ZDBH_U")) {//����ն˱��û�д���Ĭ��Ϊϵͳ����Ľ��ף�ͬʱ���У���롢ǰ̨��ˮ��ǰ̨����
//            String sZDBH_U = Com.getSYSZDBH_U();;
//            map_In.get("head").put("ZDBH_U", sZDBH_U);
//            map_In.get("head").put("ZDJYM_", "SERVER");
//            map_In.get("head").put("QTLS_U", Com.getSYSQTLS(sZDBH_U));
//            map_In.get("head").put("QTRQ_U", Com.getDate());
//        }
        CallThread call_thread=null;
        if (tranObj != null)
            call_thread = new CallThread(map_In, tranObj.iBWXH);
        else
            call_thread = new CallThread(map_In, 0);

        call_thread.start();
        call_thread.join();
        if (tranObj != null)
            tranObj.iBWXH = call_thread.deta_iBWXH[0];
        callResult.setHead(call_thread.map_Out.get("head"));
        callResult.setBody(call_thread.map_Out.get("body"));
        return call_thread.isSuccess();
    }

    public static String call_from_xml(String XMLIn, int[] iBWXH) {

        TranObj tranObj = new TranObj(XMLIn);
        tranObj.iBWXH = iBWXH[0];//�����ͬ
        if (false == tranObj.buildSUCCESS) {
            Tran.runERR(tranObj, "ERR003");
            return getOut(tranObj, iBWXH);
        }

        Logger.log(tranObj, "LOG_IO", "XMLIn=" + XMLIn);

        /*
        �������
         */

        if (true != ComInit.exec(tranObj)) {
            Logger.log(tranObj, "LOG_SYS", "��ʼ��ʧ��");
            if ("".equals(tranObj.getHead("CWDM_U")))
                Tran.runERR(tranObj, "ERR001");
            return getOut(tranObj, iBWXH);
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
        if (true != callRe && !tranObj.commitFlg) {//��֪״̬�Ľ��ף����˹�����
            tranObj.sqlSession.rollback();
            Logger.log(tranObj, "LOG_ERR", "Call ERR");
        }

        return getOut(tranObj, iBWXH);
    }

    public static String getOut(TranObj tranObj, int[] iBWXH) {
        String XMLOut = "";
        XMLOut = XmlUtils.tranObj2XML(tranObj);
        tranObj.bwOut = XMLOut;

        InsertMJYBWAfterTran.exec(tranObj);//ֱ�Ӳ���

        iBWXH[0] = tranObj.iBWXH;//�������
        Logger.log(tranObj, "LOG_IO", "��ջ�������=" + iBWXH[0]);
        if (null != tranObj.sqlSession) {
            tranObj.sqlSession.commit();
            tranObj.sqlSession.close();
        }
        Logger.log(tranObj, "LOG_IO", "XMLOut" + XMLOut);
        Logger.log(tranObj, "LOG_IO", Com.TRAN_OUT);

        Com.logFile.remove(tranObj.loggerFile);


        return XMLOut;
    }


}
