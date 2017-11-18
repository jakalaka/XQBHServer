package XQBHServer.Utils.XML; /**
 * Created by Administrator on 2017/7/2 0002.
 */

import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    /**
     * 根据Map组装xml消息体，值对象仅支持基本数据类型、String、BigInteger、BigDecimal，以及包含元素为上述支持数据类型的Map
     *
     * @return
     */
    public static String map2XML(Map<String, Object> In) {
        String jsonString = JSON.toJSONString(In);
        return jsonString;
    }
    public static String tranObj2XML(TranObj tranObj) {
        Map XMLMap=new HashMap();
        XMLMap.put("head",tranObj.HeadMap);
        XMLMap.put("body",tranObj.TranMap);
        return map2XML(XMLMap);
    }

    /**
     * 根据xml消息体转化为Map
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map XML2map(String xml)  {
        JSONObject jsonObject = JSON.parseObject(xml);
        return  jsonObject.getInnerMap();
    }


}