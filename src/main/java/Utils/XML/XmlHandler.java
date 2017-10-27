package Utils.XML;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
public class XmlHandler {
    /**
     * 回报组包
     * @param HeadMap
     * @param TranMap
     * @param XmlMapOut
     * @return
     */
    public static boolean addInfo(Map HeadMap,Map TranMap,Map XmlMapOut)
    {
        if("".equals(HeadMap.get("CWDM_U"))||null==HeadMap.get("CWDM_U"))
            HeadMap.put("CWDM_U","AAAAAAA");
        else
            TranMap.clear();
        XmlMapOut.put("head",HeadMap);
        XmlMapOut.put("body",TranMap);

        /*
        我也不知道再要加什么
         */
        return true;
    }

    /**
     * 来报拆解并返回交易码
     * @param XMLMapIn
     * @param TranMapIn
     * @param JYM_UU
     * @return
     */

}
