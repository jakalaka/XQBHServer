package XQBHServer.Utils.PropertiesHandler;

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
    private static final String encode = "GBK";//�ļ��ı����ʽ
    private static Properties pps = new Properties();

    public static String readKeyFromXML(File file, String Key) {
        Properties propertie;
        FileInputStream inputFile;
        if (!file.exists()) {
            Logger.sysLog( "file not found");
            return null;
        }

        propertie = new Properties();
        try {
            inputFile = new FileInputStream(file);
            propertie.load(inputFile);
//            propertie.loadFromXML(inputFile);//��ȡXML�ļ�
            inputFile.close();
        } catch (FileNotFoundException ex) {
            Logger.sysLog("�ļ��޷��ҵ�");
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.sysLog("��ȡ�ļ�ʧ��");
            ex.printStackTrace();
        }

        return propertie.getProperty(Key);
    }

    public static Map<String, String> readAll(InputStream inputStream) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            pps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration enum1 = pps.propertyNames();//�õ������ļ�������
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
