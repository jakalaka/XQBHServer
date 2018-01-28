package XQBHServer.Utils.XML; /**
 * Created by Administrator on 2017/7/2 0002.
 */

import XQBHServer.ServerTran.TranObj;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentException;

import java.util.HashMap;
import java.util.Map;

public class XmlUtils {

    /**
     * ����Map��װxml��Ϣ�壬ֵ�����֧�ֻ����������͡�String��BigInteger��BigDecimal���Լ�����Ԫ��Ϊ����֧���������͵�Map
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
     * ����xml��Ϣ��ת��ΪMap
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