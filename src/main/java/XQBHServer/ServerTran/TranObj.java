package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.ServerAPI.GetLogInfo;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class TranObj {
    public Map<String, Object> HeadMap = null;
    public Map<String, Object> TranMap = null;

    public boolean buildSUCCESS = false;
    public Date date;
    public SqlSession sqlSession = null;
    public SqlSession sqlSession_BW = null;

    public StringBuilder filePrinter = null;
    public String flLogLV = null;
    public String bwIn;
    public String bwOut;
    public boolean commitFlg;
    public int iBWXH = 0;

    public TranObj(String XMLIn) {
        iBWXH = 0;
        bwIn = XMLIn;
        filePrinter = new StringBuilder();
        Map XMLMapIn = XmlUtils.XML2map(XMLIn);
        HeadMap = (Map) XMLMapIn.get("head");
        TranMap = (Map) XMLMapIn.get("body");
        date = new Date();
        try {
            sqlSession = Com.dbAccess.getSqlSession();
            sqlSession_BW = Com.dbAccess.getSqlSession();
        } catch (IOException e) {
            Logger.sysLogException(e);
            return;
        }
        commitFlg = false;


        if (true != GetLogInfo.exec(this)) {
            Logger.log(this, "LOG_ERR", "获取日志等级失败");
            return;
        }
        buildSUCCESS = true;

    }

    public void setHead(String Key, String Value) {
        HeadMap.put(Key, Value);
    }

    public String getHead(String Key) {
        if (null == HeadMap.get(Key))
            return "";
        String result = HeadMap.get(Key).toString();
        return result;
    }

    public void setValue(String Key, Object value) {
        TranMap.put(Key, value);
    }

    public BigDecimal getBigDecimal(String sKey) {
        Object result = TranMap.get(sKey);
        if (null == result)
            return TypeUtils.castToBigDecimal(0);
        return TypeUtils.castToBigDecimal(TranMap.get(sKey));
    }

    public Long getLong(String sKey) {
        Object result = TranMap.get(sKey);
        if (null == result)
            return TypeUtils.castToLong(0);
        return TypeUtils.castToLong(result);
    }

    public String getString(String sKey) {
        Object result = TranMap.get(sKey);
        if (null == result)
            return TypeUtils.castToString("");
        return TypeUtils.castToString(result);
    }

    public String getGridString(String sGridName, int iIndex, String sKey) {
        Object object = TranMap.get(sGridName);
        if (null == object)
            return null;
        return TypeUtils.castToString(((Map) ((JSONArray) object).get(iIndex)).get(sKey));
    }

    public Long getGridLong(String sGridName, int iIndex, String sKey) {
        Object object = TranMap.get(sGridName);
        if (null == object)
            return TypeUtils.castToLong(0);
        return TypeUtils.castToLong(((Map) ((JSONArray) object).get(iIndex)).get(sKey));
    }

    public BigDecimal getGridBigDecimal(String sGridName, int iIndex, String sKey) {
        Object object = TranMap.get(sGridName);
        if (null == object)
            return TypeUtils.castToBigDecimal(0);
        return TypeUtils.castToBigDecimal(((Map) ((JSONArray) object).get(iIndex)).get(sKey));
    }

}
