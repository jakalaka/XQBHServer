package XQBHServer.Test;

import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.*;

public class mylogtest {
    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++)
//        {
//            try {
//                FileWriter fw=new FileWriter(new File("logs/mylog.log"),true);
//                fw.write("debug");
//                fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("spand " + (endTime - startTime) + "ms");
        File f=new File("logs/out.txt");
        try {
            f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);

            System.out.println("默认输出到控制台的这一句，输出到了文件 out.txt");
            DBAccess dbAccess=new DBAccess();
            SqlSession sqlSession=dbAccess.getSqlSession();
            CXTCSMapper cxtcsMapper = sqlSession.getMapper(CXTCSMapper.class);
            CXTCSKey cxtcsKey = new CXTCSKey();
            cxtcsKey.setFRDM_U("9999");
            cxtcsKey.setKEY_UU("AlipayTOut");
            CXTCS cxtcs = null;
            try {
                cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
