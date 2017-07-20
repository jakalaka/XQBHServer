package Server;


import ServerTran.CommonTran;

import javax.xml.ws.Endpoint;

/**
 * ...
 * Created by Administrator on 2017/6/29 0029.
 */
public class ServerMain {
    public static void main(String[] argv) {
        //aaa
        ServerInit.Init();
        Object implementor = new CommonTran();
        String address = "http://192.168.31.62:9000/CommonTran";
        Endpoint.publish(address, implementor);
    }
}
