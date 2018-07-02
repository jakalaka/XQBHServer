package XQBHServer.Utils.ClientConecter;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXExample;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Connector {
    public static void exec(String[] args) {
        Vector<String []> vResoult=new Vector();
         org.slf4j.Logger tmplogger = LoggerFactory.getLogger("XQBHServer");

        Logger.comLog("LOG_IO",Com.getIn);
        String logfile=Logger.getLogPath("Temp");
        MDC.put("logFileName",logfile ); //获取日志文件

        int i = args.length;
        if (i < 2) {
            Logger.comLog("LOG_IO","参数个数过少");
            Logger.comLog("LOG_IO","restart|pause ALL|ZD1 ZD2 ZD3 ...");
            Logger.comLog("LOG_IO",Com.getOut);
            return;
        }
        int j = 0;
        String CMD = "";
        for (String str : args
                ) {
            if (j == 0) {
                if (!"pause".equals(str) && !"restart".equals(str)) {
                    Logger.comLog("LOG_IO","错误的命令" + str);
                    Logger.comLog("LOG_IO","restart|pause ALL|ZD1 ZD2 ZD3 ...");
                    Logger.comLog("LOG_IO",Com.getOut);
                    return;

                } else {
                    CMD = str;
                    j++;
                    continue;
                }

            }


            if ("ALL".equals(str)) {
                DBAccess dbAccess = new DBAccess();
                SqlSession sqlSession = null;
                try {
                    sqlSession = dbAccess.getSqlSession();
                } catch (IOException e) {
                    Logger.comLogException("LOG_ERR",e);
                    Logger.comLog("LOG_IO",Com.getOut);
                    return;
                }
                DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
                DZDXXExample dzdxxExample = new DZDXXExample();
                dzdxxExample.or().andJLZT_UEqualTo("0").andZDLXBHNotEqualTo("SERVER");
                List<DZDXX> lDZDXX;
                try {
                    lDZDXX = dzdxxMapper.selectByExample(dzdxxExample);
                } catch (Exception e) {
                    Logger.comLogException("LOG_ERR",e);
                    Logger.comLog("LOG_IO",Com.getOut);
                    return;
                }
                if (!(lDZDXX.size() > 0)) {
                    Logger.comLog("LOG_DEBUG","没有可执行的终端");

                }
                for (DZDXX dzdxx : lDZDXX
                        ) {
                    try {

                        if (true == sendClient(tmplogger,dzdxx.getIP_UUU(), CMD))
                            vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"SUCCESS"});
                        else
                            vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"FAIL"});
                    } catch (Exception e) {
                        Logger.comLogException("LOG_ERR",e);
                        vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"FAIL"});

                        continue;
                    }
                }


                break;
            } else {
                //将参数的地址传给send
                DBAccess dbAccess = new DBAccess();
                SqlSession sqlSession = null;
                try {
                    sqlSession = dbAccess.getSqlSession();
                } catch (IOException e) {
                    Logger.comLogException("LOG_ERR",e);
                    Logger.comLog("LOG_IO",Com.getOut);
                    return;
                }
                DZDXXKey dzdxxKey=new DZDXXKey();
                dzdxxKey.setFRDM_U("9999");
                dzdxxKey.setZDBH_U(str);
                DZDXXMapper dzdxxMapper=sqlSession.getMapper(DZDXXMapper.class);
                DZDXX dzdxx;
                try {
                    dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
                }catch (Exception e)
                {
                    Logger.comLogException("LOG_ERR",e);

                    continue;
                }
                try {
                    if (true == sendClient(tmplogger,dzdxx.getIP_UUU(), CMD))
                        vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"SUCCESS"});
                    else
                        vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"FAIL"});

                } catch (Exception e) {
                    Logger.comLogException("LOG_ERR",e);
                    vResoult.add(new String[]{dzdxx.getZDBH_U(),dzdxx.getIP_UUU(),"FAIL"});

                    continue;
                }

            }
        }
        Logger.comLog("LOG_IO","=======================================================");
        for (int tmp=0;tmp<vResoult.size();tmp++)
        {
            Logger.comLog("LOG_IO","Send CMD ["+CMD+"] to ZD["+vResoult.get(tmp)[0]+"] IP["+vResoult.get(tmp)[1]+"] "+ vResoult.get(tmp)[2]);
        }
        Logger.comLog("LOG_IO","=======================================================");
        Com.logFile.remove(logfile);


        Logger.comLog("LOG_IO",Com.getOut);

    }

    public static boolean sendClient(org.slf4j.Logger tmplogger,String IP, String CMD) throws IOException {
        if (null==IP||"".equals(IP))
            return false;
//客户端
//1、创建客户端Socket，指定服务器地址和端口
        String sIP=IP.split(":")[0];
        int port=Integer.parseInt(IP.split(":")[1]);


        Logger.comLog("LOG_IO","sIP=["+sIP+"] port=["+port+"]");
        Socket socket = new Socket(sIP, port);
//2、获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
        Map xmlMapIn = new HashMap();
        xmlMapIn.put("FUNCTION", CMD);
        String sXmlIn = XmlUtils.map2XML(xmlMapIn);
        pw.write(sXmlIn);
        pw.flush();
        socket.shutdownOutput();
//3、获取输入流，并读取服务器端的响应信息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        info = br.readLine();
        //4、关闭资源
        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();

        Map xmlMapOut = XmlUtils.XML2map(info);
        if (!"AAAAAA".equals(xmlMapOut.get("CWDM_U"))) {
            Logger.comLog("LOG_IO","IP = [" + IP + "] ,Send CMD=[" + CMD + "] CWDM_U=[" + xmlMapOut.get("CWDM_U") + "]");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        exec(args);
    }
}
