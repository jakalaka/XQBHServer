package XQBHServer.ServerTran;

import XQBHServer.Utils.XML.XmlUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class TranObj {
    public Map<String, String> HeadMap = null;
    public Map<String, String> TranMap = null;
    String JYM_UU = null;
    public String CWDM_U = null;
    public String CWXX_U = null;
    public boolean buildSUCCESS = false;
    Date date;
    public TranObj(String XMLIn)  {
        Map XMLMapIn = (Map) XmlUtils.XML2map(XMLIn, "root");
        HeadMap = (Map) XMLMapIn.get("head");
        TranMap = (Map) XMLMapIn.get("body");
        JYM_UU = HeadMap.get("HTJYM_").toString();
        date = new Date();
        buildSUCCESS = true;
    }

    public void writeHead(String Key, String Value) {
        HeadMap.put(Key, Value);
    }

    public String getHead(String Key) {
        String result = HeadMap.get(Key).toString();
        if (null == result)
            result = "";
        return result;
    }

    public void setValue(String Key, String Value) {
        TranMap.put(Key, Value);
    }

    public String getValue(String Key) {

        String result = TranMap.get(Key);
        if (null == result)
            result = "";
        return result;
    }


}
