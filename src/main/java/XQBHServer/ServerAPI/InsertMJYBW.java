package XQBHServer.ServerAPI;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class InsertMJYBW {
    public static boolean exec(SqlSession sqlSession, Map<String, String> HeadMap) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String rqTmp = HeadMap.get("QTRQ_U") + HeadMap.get("QTSJ_U");
        Date date = formatter.parse(rqTmp);
        MJYBWMapper mjybwMapper = sqlSession.getMapper(MJYBWMapper.class);
        DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
        MJYBW mjybw = new MJYBW();
        MJYBWWithBLOBs mjybwWithBLOBs = new MJYBWWithBLOBs();
        mjybwWithBLOBs.setFRDM_U("9999");
        mjybwWithBLOBs.setQTLS_U(HeadMap.get("QTLS_U"));
        mjybwWithBLOBs.setQTRQ_U(date);


        return true;
    }
}
