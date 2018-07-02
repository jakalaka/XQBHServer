package XQBHServer.Server;


import XQBHServer.ServerTran.CommonTran;
import XQBHServer.Utils.RSA.HashUtil;
import XQBHServer.Utils.RSA.RSAUtil;
import XQBHServer.Utils.log.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
public class ServerMain {
    public static void main(String[] argv) {
        String sysLogfile=Logger.getLogPath("System");
        MDC.put("logFileName",sysLogfile ); //获取日志文件

        //aaa
        if (false == ServerInit.Init()) {
            Logger.comLog("LOG_ERR", "ServerInit Fail!!!");
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
//        Logger.comLog(Logger.logger,"address:"+address);
//        Endpoint endpoint=null;
//        try {
//            endpoint=Endpoint.publish(address, implementor);
//
//        }catch (Exception e)
//        {
//            Logger.comLog(Logger.logger,"平台启动异常");
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
        new Thread(new Runnable() { //主服务进程
            @Override
            public void run() {
                MDC.put("logFileName",sysLogfile ); //获取日志文件

                // 1.创建ServerSocket对象
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    Logger.comLogException("LOG_ERR",e);
                    Logger.comLog("LOG_ERR", "创建ServerSocket对象失败!!!");
                    System.exit(0);
                }
                ExecutorService executorService = Executors.newFixedThreadPool(5);

                while (true) {
                    try {

                        Socket finalSocket = serverSocket.accept();
                        Thread subThread=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String platFormFile=Logger.getLogPath("Platform");
                                MDC.put("logFileName",platFormFile ); //获取日志文件
                                MDC.put("threadID","1" ); //进程标识

                                try {
                                    //3、获取输入流，并读取客户端信息
                                    InputStream is = null;
                                    is = finalSocket.getInputStream();

                                    StringBuilder stringBuilder = new StringBuilder();

                                    byte[] buff = new byte[1024];
                                    byte[] buffIn = new byte[0];
                                    int t = 0;
                                    while ((t = is.read(buff)) != -1) {
                                        buffIn = addBytes(buffIn, buff, t);
                                    }

                                    finalSocket.shutdownInput();//关闭输入流

                                    Logger.comLog("LOG_SYS", "======================================================================================");
                                    Logger.comLog("LOG_SYS", "finalSocket shutdown");
                                    Logger.comLog("LOG_SYS", "receive buff=[" + new String(buffIn) + "]");
                                    Logger.comLog("LOG_SYS", "begin to find split");

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
                                        Logger.comLog("LOG_SYS", "split no found");

                                    } else {
                                        Logger.comLog("LOG_SYS", "match the split");
                                    }
                                    Logger.comLog("LOG_SYS", "begin to decrypt");

                                    byte[] encrybyte = new byte[iPos];
                                    System.arraycopy(buffIn, 0, encrybyte, 0, encrybyte.length);

                                    //解密
                                    byte[] datebyte = RSAUtil.decryptByPublicKey(HashUtil.decryptBASE64(encrybyte), Com.clientEncryptPublicKey);
                                    String XMLIn = new String(datebyte);

                                    Logger.comLog("LOG_SYS", "XMLIn=[" + XMLIn + "]");

                                    Logger.comLog("LOG_SYS", "decrypt done");

                                    Logger.comLog("LOG_SYS", "begin to checksign");

                                    //验签
                                    byte[] signbyte = new byte[buffIn.length - iPos - 7];
                                    System.arraycopy(buffIn, iPos + 7, signbyte, 0, signbyte.length);
                                    boolean verPass = RSAUtil.verify(XMLIn.getBytes(Com.charset), Com.clientSignPublicKey, new String(signbyte));

                                    Logger.comLog("LOG_SYS", "checksign done");

                                    String XMLOut;
                                    if (verPass) {
                                        Logger.comLog("LOG_SYS", "checksign pass");

                                        CommonTran commonTran = new CommonTran();
                                        XMLOut = commonTran.Comtran(XMLIn);
                                        MDC.put("logFileName",platFormFile ); //获取日志文件

                                    } else {
                                        XMLOut = "验签失败,本服务已备案,请自重!!!";
                                        Logger.comLog("LOG_SYS", "checksign fail");
                                        Logger.comLog("LOG_SYS", "收到报文且验签失败!!!");
                                    }
                                    Logger.comLog("LOG_SYS", "XMLOut=[" + XMLOut + "]");
                                    byte[] outbyte = XMLOut.getBytes();
                                    if (verPass) {
                                        Logger.comLog("LOG_SYS", "begin to encrypt");

                                        //加密
                                        outbyte = HashUtil
                                                .encryptBASE64byte(RSAUtil.encryptByPublicKey(outbyte, Com.clientEncryptPublicKey));

                                        Logger.comLog("LOG_SYS", "encrypt done");
                                        Logger.comLog("LOG_SYS", "begin to sign");
                                        //加签
                                        String signstr = RSAUtil.sign(outbyte, Com.serverSignPrivatebKey);
                                        byte[] splitbyte = {'7', '7', '7', '7', '7', '7', '7'};
                                        outbyte = addBytes(outbyte, splitbyte, splitbyte.length);
                                        byte[] signbyteout = signstr.getBytes();
                                        outbyte = addBytes(outbyte, signbyteout, signbyteout.length);
                                        Logger.comLog("LOG_SYS", "sign done");

                                    }
                                    Logger.comLog("LOG_SYS", "response buff=[" + new String(outbyte) + "]");
                                    Logger.comLog("LOG_SYS", "======================================================================================" + "\n\n\n");

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
                                    MDC.put("logFileName",platFormFile ); //获取日志文件
                                    Logger.comLogException("LOG_ERR",e);
                                }finally {
                                    Com.logFile.remove(platFormFile);
                                }


                            }
                        });
                        executorService.execute(subThread);


                    } catch (IOException e) {
                        Logger.comLogException("LOG_ERR",e);
                    }

                }
            }
        }).start();

        Logger.comLog("LOG_SYS", "Server start successful!!!");


    }

    public static byte[] addBytes(byte[] data1, byte[] data2, int size) {
        byte[] data3 = new byte[data1.length + size];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, size);
        return data3;

    }
}
