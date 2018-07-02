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
        MDC.put("logFileName",sysLogfile ); //��ȡ��־�ļ�

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
//            Logger.comLog(Logger.logger,"ƽ̨�����쳣");
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
        new Thread(new Runnable() { //���������
            @Override
            public void run() {
                MDC.put("logFileName",sysLogfile ); //��ȡ��־�ļ�

                // 1.����ServerSocket����
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    Logger.comLogException("LOG_ERR",e);
                    Logger.comLog("LOG_ERR", "����ServerSocket����ʧ��!!!");
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
                                MDC.put("logFileName",platFormFile ); //��ȡ��־�ļ�
                                MDC.put("threadID","1" ); //���̱�ʶ

                                try {
                                    //3����ȡ������������ȡ�ͻ�����Ϣ
                                    InputStream is = null;
                                    is = finalSocket.getInputStream();

                                    StringBuilder stringBuilder = new StringBuilder();

                                    byte[] buff = new byte[1024];
                                    byte[] buffIn = new byte[0];
                                    int t = 0;
                                    while ((t = is.read(buff)) != -1) {
                                        buffIn = addBytes(buffIn, buff, t);
                                    }

                                    finalSocket.shutdownInput();//�ر�������

                                    Logger.comLog("LOG_SYS", "======================================================================================");
                                    Logger.comLog("LOG_SYS", "finalSocket shutdown");
                                    Logger.comLog("LOG_SYS", "receive buff=[" + new String(buffIn) + "]");
                                    Logger.comLog("LOG_SYS", "begin to find split");

                                    //��ȡǩ���ָ�λ��
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
                                    if (!foundSplit)//δ�ҵ��ָ��
                                    {
                                        Logger.comLog("LOG_SYS", "split no found");

                                    } else {
                                        Logger.comLog("LOG_SYS", "match the split");
                                    }
                                    Logger.comLog("LOG_SYS", "begin to decrypt");

                                    byte[] encrybyte = new byte[iPos];
                                    System.arraycopy(buffIn, 0, encrybyte, 0, encrybyte.length);

                                    //����
                                    byte[] datebyte = RSAUtil.decryptByPublicKey(HashUtil.decryptBASE64(encrybyte), Com.clientEncryptPublicKey);
                                    String XMLIn = new String(datebyte);

                                    Logger.comLog("LOG_SYS", "XMLIn=[" + XMLIn + "]");

                                    Logger.comLog("LOG_SYS", "decrypt done");

                                    Logger.comLog("LOG_SYS", "begin to checksign");

                                    //��ǩ
                                    byte[] signbyte = new byte[buffIn.length - iPos - 7];
                                    System.arraycopy(buffIn, iPos + 7, signbyte, 0, signbyte.length);
                                    boolean verPass = RSAUtil.verify(XMLIn.getBytes(Com.charset), Com.clientSignPublicKey, new String(signbyte));

                                    Logger.comLog("LOG_SYS", "checksign done");

                                    String XMLOut;
                                    if (verPass) {
                                        Logger.comLog("LOG_SYS", "checksign pass");

                                        CommonTran commonTran = new CommonTran();
                                        XMLOut = commonTran.Comtran(XMLIn);
                                        MDC.put("logFileName",platFormFile ); //��ȡ��־�ļ�

                                    } else {
                                        XMLOut = "��ǩʧ��,�������ѱ���,������!!!";
                                        Logger.comLog("LOG_SYS", "checksign fail");
                                        Logger.comLog("LOG_SYS", "�յ���������ǩʧ��!!!");
                                    }
                                    Logger.comLog("LOG_SYS", "XMLOut=[" + XMLOut + "]");
                                    byte[] outbyte = XMLOut.getBytes();
                                    if (verPass) {
                                        Logger.comLog("LOG_SYS", "begin to encrypt");

                                        //����
                                        outbyte = HashUtil
                                                .encryptBASE64byte(RSAUtil.encryptByPublicKey(outbyte, Com.clientEncryptPublicKey));

                                        Logger.comLog("LOG_SYS", "encrypt done");
                                        Logger.comLog("LOG_SYS", "begin to sign");
                                        //��ǩ
                                        String signstr = RSAUtil.sign(outbyte, Com.serverSignPrivatebKey);
                                        byte[] splitbyte = {'7', '7', '7', '7', '7', '7', '7'};
                                        outbyte = addBytes(outbyte, splitbyte, splitbyte.length);
                                        byte[] signbyteout = signstr.getBytes();
                                        outbyte = addBytes(outbyte, signbyteout, signbyteout.length);
                                        Logger.comLog("LOG_SYS", "sign done");

                                    }
                                    Logger.comLog("LOG_SYS", "response buff=[" + new String(outbyte) + "]");
                                    Logger.comLog("LOG_SYS", "======================================================================================" + "\n\n\n");

                                    //4����ȡ���������Ӧ�ͻ��˵�����
                                    OutputStream os = null;
                                    os = finalSocket.getOutputStream();
                                    os.write(outbyte);
                                    os.flush();


                                    //5���ر���Դ
                                    os.close();
//                                    br.close();
//                                    isr.close();
                                    is.close();
                                    finalSocket.close();
                                } catch (Exception e) {
                                    MDC.put("logFileName",platFormFile ); //��ȡ��־�ļ�
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
