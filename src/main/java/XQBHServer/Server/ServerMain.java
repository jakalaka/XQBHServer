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
             Logger.log("LOG_SYS","ServerInit Fail!!!");
             return;
         }
         String ip="";
        try {
            ip= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Object implementor = new CommonTran();
        String address = "http://"+ip+":9000/CommonTran";
        Logger.log("LOG_SYS","address:"+address);
        Endpoint.publish(address, implementor);

        Logger.log("LOG_SYS","Server start successful!!!");
    
    }
}
