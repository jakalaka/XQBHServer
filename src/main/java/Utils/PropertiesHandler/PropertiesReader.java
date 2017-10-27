package Utils.PropertiesHandler;

import Utils.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class PropertiesReader {
    private static final String encode = "UTF-8";//文件的编码格式
    private static Properties pps = new Properties();

    public static String readKeyFromXML(File file, String Key) {
        Properties propertie;
        FileInputStream inputFile;
        if (!file.exists()) {
            Logger.log("ERR", "file not found");
            return null;
        }

        propertie = new Properties();
        try {
            inputFile = new FileInputStream(file);
            propertie.load(inputFile);
//            propertie.loadFromXML(inputFile);//读取XML文件
            inputFile.close();
        } catch (FileNotFoundException ex) {
            Logger.log("ERR", "文件无法找到");
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.log("ERR", "读取文件失败");
            ex.printStackTrace();
        }

        return propertie.getProperty(Key);
    }

    public static Map<String, String> readAll(File file) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            pps.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
        while (enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = pps.getProperty(strKey);
//                System.out.println(strKey + "=" +strValue);
            map.put(strKey, strValue);
        }

        return map;
    }

//    public static void main(String[] args) {
//        System.out.println(readAll(new File("resources/errmsg.properties")));
//    }
}
