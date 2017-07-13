package Server;

import ServerAPI.JCZDHFX;

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
    public static boolean minusInfo(Map XMLMapIn,Map TranMapIn,String[] JYM_UU)
    {
        Map HeadMapIn=(Map) XMLMapIn.get("head");
        TranMapIn=(Map)XMLMapIn.get("body");
        JYM_UU[0]=((Map)XMLMapIn.get("head")).get("JYM_UU").toString();
        /*
        以下需增加对来报解析
         */
        String ZDBH_U=HeadMapIn.get("ZDBH_U").toString();
        if (JCZDHFX.exec(ZDBH_U)==false)
            return false;

        return true;
    }
}
