package XQBHServer.Server;



import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.log.Logger;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
public class ServerMain {
    public static void main(String[] argv) {
        //aaa
         if (false== ServerInit.Init())
         {
             Logger.sysLog("ServerInit Fail!!!");
             return;
         }
//         String ip="172.18.38.19";
        String ip="192.168.31.62";
//        try {
//            ip= InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            Logger.sysLogException(e);
//            return;
//        }
        Object implementor = new CommonTran();
        String address = "http://"+ip+":9000/CommonTran";
        Logger.sysLog("address:"+address);
        Endpoint endpoint=null;
        try {
            endpoint=Endpoint.publish(address, implementor);

        }catch (Exception e)
        {
            Logger.sysLog("平台启动异常");
            Logger.sysLogException(e);
            System.exit(0);
        }
        Map<String ,Object> source=new HashMap();
        source.put("timeout",100);
        endpoint.setProperties(source);
        Map<String,Object>  map=endpoint.getProperties();
        for (Map.Entry<String,Object> entry : map.entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }


        Logger.sysLog("Server start successful!!!");


    }
}
