package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.ServerAPI.GetLogInfo;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class TranObj {
    public Map<String, String> HeadMap = null;
    public Map<String, String> TranMap = null;

    public boolean buildSUCCESS = false;
    public Date date;
    public SqlSession sqlSession = null;

    public StringBuilder filePrinter = null;
    public String flLogLV=null;

    public TranObj(String XMLIn) {
        filePrinter = new StringBuilder();
        Map XMLMapIn = (Map) XmlUtils.XML2map(XMLIn, "root");
        HeadMap = (Map) XMLMapIn.get("head");
        TranMap = (Map) XMLMapIn.get("body");
        date = new Date();
        try {
            sqlSession = Com.dbAccess.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        buildSUCCESS = true;

        try {//获取日志等级 必须先获取所以写到这里
            GetLogInfo.exec(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHead(String Key, String Value) {
        HeadMap.put(Key, Value);
    }

    public String getHead(String Key) {
        if (null==HeadMap.get(Key))
            return "";
        String result = HeadMap.get(Key).toString();
        return result;
    }

    public void setValue(String Key, String Value) {
        TranMap.put(Key, Value);
    }

    public String getValue(String Key) {
        if (null==TranMap.get(Key))
            return "";
        String result = TranMap.get(Key);;
        return result;
    }


}
