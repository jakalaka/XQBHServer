package XQBHServer.Server.Table.basic;


import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.xml.transform.Source;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class DBAccess {

    public SqlSession getSqlSession() throws IOException {
        Reader reader = Resources.getUrlAsReader(Class.class.getResource("/resources/table/Configuration.xml").toString());

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
