package XQBHServer.ServerAPI;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.TranObj;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class InsertMJYBW {
    public static boolean exec(TranObj tranObj) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String rqTmp = tranObj.getHead("QTRQ_U") + tranObj.getHead("QTSJ_U") ;
        Date date = formatter.parse(rqTmp);
        MJYBWMapper mjybwMapper = tranObj.sqlSession.getMapper(MJYBWMapper.class);
        MJYBW mjybw = new MJYBW();
        MJYBWWithBLOBs mjybwWithBLOBs = new MJYBWWithBLOBs();
        mjybwWithBLOBs.setFRDM_U("9999");
        mjybwWithBLOBs.setQTLS_U(tranObj.getHead("QTLS_U"));
        mjybwWithBLOBs.setQTRQ_U(date);
        mjybwWithBLOBs.setQTJYM_(tranObj.getHead("QTJYM_"));
        mjybwWithBLOBs.setHTJYM_(tranObj.getHead("HTJYM_"));


        mjybwWithBLOBs.setJYZT_U("0");//0-初始   1-成功  2-失败  r-冲正
        mjybwWithBLOBs.setZDBH_U(tranObj.getHead("ZDBH_U"));
        mjybwWithBLOBs.setIP_UUU(tranObj.getHead("IP_UUU"));
        mjybwWithBLOBs.setJLZT_U("0");
        mjybwMapper.insert(mjybwWithBLOBs);


        return true;
    }
}
