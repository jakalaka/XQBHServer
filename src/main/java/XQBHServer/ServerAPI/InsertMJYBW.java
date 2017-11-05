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
        MJYBW mjybw = new MJYBW();
        MJYBWWithBLOBs mjybwWithBLOBs = new MJYBWWithBLOBs();
        mjybwWithBLOBs.setFRDM_U("9999");
        mjybwWithBLOBs.setQTLS_U(HeadMap.get("QTLS_U"));
        mjybwWithBLOBs.setQTRQ_U(date);
        mjybwWithBLOBs.setQTJYM_(HeadMap.get("QTJYM_"));
        mjybwWithBLOBs.setHTJYM_(HeadMap.get("HTJYM_"));


        mjybwWithBLOBs.setJYZT_U("0");//0-初始   1-成功  2-失败  r-冲正
        mjybwWithBLOBs.setZDBH_U(HeadMap.get("ZDBH_U"));
        mjybwWithBLOBs.setIP_UUU(HeadMap.get("IP_UUU"));
        mjybwWithBLOBs.setJLZT_U("0");
        mjybwMapper.insert(mjybwWithBLOBs);


        return true;
    }
}
