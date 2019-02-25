package XQBHServer.Utils.PropertiesHandler;

import XQBHServer.Server.Com;
import XQBHServer.Utils.log.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class PropertiesReader {
    private static final String encode = "GBK";//文件的编码格式
    private static Properties pps = new Properties();

    public static String readFromProperties(File file, String Key) {
        Properties propertie;
        FileInputStream inputFile;
        if (!file.exists()) {
            Logger.comLog("LOG_ERR",file.getAbsolutePath());
            Logger.comLog("LOG_ERR", "file not found");
            return null;
        }

        propertie = new Properties();
        try {
            inputFile = new FileInputStream(file);
            propertie.load(inputFile);
//            propertie.loadFromXML(inputFile);//读取XML文件
            inputFile.close();
        } catch (FileNotFoundException ex) {
            Logger.comLog("LOG_ERR","文件无法找到");
            Logger.comLogException("LOG_ERR",ex);


        } catch (IOException ex) {
            Logger.comLog("LOG_ERR","读取文件失败");
            Logger.comLogException("LOG_ERR",ex);
        }

        return propertie.getProperty(Key);
    }

    public static Map<String, String> readAll(InputStream inputStream) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            pps.load(inputStream);
        } catch (IOException e) {
            Logger.comLogException("LOG_ERR",e);
        }
        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
        while (enum1.hasMoreElements()) {

            String strKey = (String) enum1.nextElement();
            String strValue = pps.getProperty(strKey);
//                Logger.log(tranObj,"LOG_DEBUG",strKey + "=" +strValue);
            map.put(strKey, strValue);
        }

        return map;
    }

//    public static void main(String[] args) {
//        Logger.log(tranObj,"LOG_DEBUG",readAll(new File("resources/errmsg.properties")));
//    }
}
