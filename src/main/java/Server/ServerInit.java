package Server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import Server.PropertiesReader.*;
import static Server.PropertiesReader.*;

/**
 * Created by Administrator on 2017/7/1 0001.userInfo.properties
 */
public class ServerInit {
    public static boolean Init(){
        /*
        从userInfo中获取相关配置信息
         */
        String LOGLV=readKeyFromXML(new File("src/main/resources/sysInfo.properties") ,"LOGLV");
        if (!"".equals(LOGLV)&&LOGLV!=null)
            Com.LOGLV=LOGLV;
        Com.ERRMap=readAll(new File("src/main/resources/errmsg.properties") );


        return true;
    }
}
