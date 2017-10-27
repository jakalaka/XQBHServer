package ServerAPI;

import Server.Table.Mapper.DZDXXMapper;
import Server.Table.Mapper.MJYBWMapper;
import Server.Table.Model.DZDXX;
import Server.Table.Model.MJYBW;
import Server.Table.Model.MJYBWWithBLOBs;
import Server.Table.basic.DBAccess;
import ServerTran.TranObj;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
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