package XQBHServer.Server;


import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.RSA.HashUtil;
import XQBHServer.Utils.RSA.RSAUtil;
import XQBHServer.Utils.log.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                    Logger.sysLogException(e);
                    Logger.sysLog("创建ServerSocket对象失败!!!");
                    System.exit(0);
                }
                while (true) {
                    try {
                        Socket finalSocket = serverSocket.accept();
                        new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    //3、获取输入流，并读取客户端信息
                                    InputStream is = null;
                                    is = finalSocket.getInputStream();

//                                    InputStreamReader isr = new InputStreamReader(is);
//                                    BufferedReader br = new BufferedReader(isr);
//                                    String info = null;
                                    StringBuilder stringBuilder = new StringBuilder();
//                                    while((info=br.readLine())!=null){
//                                        stringBuilder.append(info);
//                                        System.out.println(info.length());
//                                    }
                                    byte[] buff = new byte[1024];
                                    byte[] buffIn = new byte[0];
                                    int t = 0;
                                    while ((t = is.read(buff)) != -1) {
                                        buffIn = addBytes(buffIn, buff, t);
                                    }

                                    finalSocket.shutdownInput();//关闭输入流

                                    Logger.sysLog("======================================================================================" );
                                    Logger.sysLog("finalSocket shutdown");
                                    Logger.sysLog("receive buff=[" + new String(buffIn) + "]");
                                    Logger.sysLog("begin to find split");

                                    //获取签名分割位置
                                    int iPos = 0;
                                    boolean foundSplit = false;
                                    for (int i = 0; i < buffIn.length - 7; i++) {

                                        for (int j = 0; j < 7; j++) {
                                            if (buffIn[i + j] == '7') {
                                                foundSplit = true;
                                                continue;
                                            } else {
                                                foundSplit = false;
                                                break;
                                            }
                                        }
                                        if (foundSplit) {
                                            iPos = i;
                                            break;
                                        }
                                    }
                                    if (!foundSplit)//未找到分割符
                                    {
                                        Logger.sysLog("split no found");

                                    } else {
                                        Logger.sysLog("match the split");
                                    }
                                    Logger.sysLog("begin to decrypt");

                                    byte[] encrybyte = new byte[iPos];
                                    System.arraycopy(buffIn, 0, encrybyte, 0, encrybyte.length);

                                    //解密
                                    byte[] datebyte = RSAUtil.decryptByPublicKey(HashUtil.decryptBASE64(encrybyte), Com.clientEncryptPublicKey);
                                    String XMLIn = new String(datebyte);
                                    Logger.sysLog("decrypt done");

                                    Logger.sysLog("begin to checksign");

                                    //验签
                                    byte[] signbyte = new byte[buffIn.length - iPos - 7];
                                    System.arraycopy(buffIn, iPos + 7, signbyte, 0, signbyte.length);
                                    boolean verPass = RSAUtil.verify(XMLIn.getBytes(Com.charset), Com.clientSignPublicKey, new String(signbyte));

                                    Logger.sysLog("checksign done");

                                    String XMLOut;
                                    if (verPass) {
                                        Logger.sysLog("checksign pass");

                                        CommonTran commonTran = new CommonTran();
                                        XMLOut = commonTran.Comtran(XMLIn);
                                    } else {
                                        XMLOut = "验签失败,本服务已备案,请自重!!!";
                                        Logger.sysLog("checksign fail");
                                        Logger.sysLog("收到报文且验签失败!!!");
                                    }
                                    byte[] outbyte = XMLOut.getBytes();
                                    if (verPass) {
                                        Logger.sysLog("begin to encrypt");

                                        //加密
                                        outbyte = HashUtil
                                                .encryptBASE64byte(RSAUtil.encryptByPublicKey(outbyte, Com.clientEncryptPublicKey));

                                        Logger.sysLog("encrypt done");
                                        Logger.sysLog("begin to sign");
                                        //加签
                                        String signstr = RSAUtil.sign(outbyte, Com.serverSignPrivatebKey);
                                        byte[] splitbyte = {'7', '7', '7', '7', '7', '7', '7'};
                                        outbyte = addBytes(outbyte, splitbyte, splitbyte.length);
                                        byte[] signbyteout = signstr.getBytes();
                                        outbyte = addBytes(outbyte, signbyteout, signbyteout.length);
                                        Logger.sysLog("sign done");

                                    }
                                    Logger.sysLog("response buff=[" + new String(outbyte) + "]");
                                    Logger.sysLog("======================================================================================" + "\n\n\n");

                                    //4、获取输出流，响应客户端的请求
                                    OutputStream os = null;
                                    os = finalSocket.getOutputStream();
                                    os.write(outbyte);
                                    os.flush();


                                    //5、关闭资源
                                    os.close();
//                                    br.close();
//                                    isr.close();
                                    is.close();
                                    finalSocket.close();
                                } catch (Exception e) {
                                    Logger.sysLogException(e);
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

    public static byte[] addBytes(byte[] data1, byte[] data2, int size) {
        byte[] data3 = new byte[data1.length + size];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, size);
        return data3;

    }
}
