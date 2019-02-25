package XQBHServer.Utils.CallUtils;

import XQBHServer.ServerTran.SystemTran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.XML.XmlUtils;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public class CallThread extends Thread{
    private boolean isSuccess;
    public Map map_In;
    public Map <String,Map>map_Out;
    public int []deta_iBWXH={0};
    public CallThread(Map map_In_,int deta_iBWXH_) {
        isSuccess = false;
        map_In = map_In_;
        map_Out = new HashMap();
        deta_iBWXH[0]=deta_iBWXH_;
    }
    public CallThread(Map map_In_) {
        new CallThread( map_In_,0);
    }

    public void run()
    {
        String XMLIn = XmlUtils.map2XML(map_In);
        String XMLOut = SystemTran.call_from_xml(XMLIn,deta_iBWXH);
        map_Out = XmlUtils.XML2map(XMLOut);
        if ("AAAAAA".equals(((Map) map_Out.get("head")).get("CWDM_U")))
            isSuccess=true;

    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
