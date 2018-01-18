package XQBHServer.Server;


import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;

import javax.xml.ws.Endpoint;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
public class ServerMain {
    public static void main(String[] argv) {
        //aaa
        if (false == ServerInit.Init()) {
            Logger.sysLog("ServerInit Fail!!!");
            return;
        }

        int port = 9000;
//        try {
//            ip= InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            Logger.sysLogException(e);
//            return;
//        }
//        Object implementor = new CommonTran();
//        String address = "http://"+ip+":9000/CommonTran";
//        Logger.sysLog("address:"+address);
//        Endpoint endpoint=null;
//        try {
//            endpoint=Endpoint.publish(address, implementor);
//
//        }catch (Exception e)
//        {
//            Logger.sysLog("平台启动异常");
//            Logger.sysLogException(e);
//            System.exit(0);
//        }
//        Map<String ,Object> source=new HashMap();
//        source.put("timeout",100);
//        endpoint.setProperties(source);
//        Map<String,Object>  map=endpoint.getProperties();
//        for (Map.Entry<String,Object> entry : map.entrySet()) {
//
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }


//
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1.创建ServerSocket对象
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        Socket socket = null;
                        socket = serverSocket.accept();

                        Socket finalSocket = socket;
                        new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    //3、获取输入流，并读取客户端信息
                                    InputStream is = null;
                                    is = finalSocket.getInputStream();

                                    InputStreamReader isr = new InputStreamReader(is);
                                    BufferedReader br = new BufferedReader(isr);
                                    String info = null;
                                    StringBuilder stringBuilder=new StringBuilder();
                                    while((info=br.readLine())!=null){
                                        stringBuilder.append(info);
                                    }
                                    finalSocket.shutdownInput();//关闭输入流

                                    String XMLIn=stringBuilder.toString();
                                    CommonTran commonTran=new CommonTran();
                                    String XMLOut=commonTran.Comtran(XMLIn);



                                    //4、获取输出流，响应客户端的请求
                                    OutputStream os = null;
                                    os = finalSocket.getOutputStream();
                                    PrintWriter pw = new PrintWriter(os);


                                    pw.write(XMLOut);
                                    pw.flush();

                                    //5、关闭资源
                                    pw.close();
                                    os.close();
                                    br.close();
                                    isr.close();
                                    is.close();
                                    finalSocket.close();
                                } catch (Exception e) {

                                }
                            }
                        }).start();


                    } catch (IOException e) {
                        Logger.sysLogException(e);
                    }

                }
            }
        }).start();


        Logger.sysLog("Server start successful!!!");


    }
}
