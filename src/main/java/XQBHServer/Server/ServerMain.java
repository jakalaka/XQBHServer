package XQBHServer.Server;



import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.log.Logger;

import javax.xml.ws.Endpoint;

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

        Object implementor = new CommonTran();
        String address = "http://127.0.0.1:9000/CommonTran";
        Endpoint.publish(address, implementor);

        Logger.log("LOG_SYS","Server start successful!!!");
    
    }
}
