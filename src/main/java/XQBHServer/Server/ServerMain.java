package XQBHServer.Server;



import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.log.Logger;

import javax.xml.ws.Endpoint;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        String ip="192.168.31.42";
//        try {
//            ip= InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            Logger.sysLogException(e);
//            return;
//        }
        Object implementor = new CommonTran();
        String address = "http://"+ip+":9000/CommonTran";
        Logger.sysLog("address:"+address);
        Endpoint.publish(address, implementor);

        Logger.sysLog("Server start successful!!!");
    
    }
}
