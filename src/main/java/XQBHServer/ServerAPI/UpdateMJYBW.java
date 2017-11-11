package XQBHServer.ServerAPI;

import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWKey;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.TranObj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMJYBW {
    public static boolean exec(TranObj tranObj) throws Exception {
        String sQTRQ_U=tranObj.getHead("QTRQ_U");
        String sQTLS_U=tranObj.getHead("QTLS_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = formatter.parse(sQTRQ_U);
        MJYBWMapper mjybwMapper=tranObj.sqlSession.getMapper(MJYBWMapper.class);
        MJYBWKey mjybwKey=new MJYBWKey();
        mjybwKey.setFRDM_U("9999");
        mjybwKey.setQTLS_U(sQTLS_U);
        mjybwKey.setQTRQ_U(date);

        MJYBWWithBLOBs mjybwWithBLOBs=mjybwMapper.selectByPrimaryKey(mjybwKey);
        mjybwWithBLOBs.setJYZT_U("1");
        mjybwWithBLOBs.setCWDM_U(tranObj.getHead("CWDM_U"));
        mjybwWithBLOBs.setCWXX_U(tranObj.getHead("CWXX_U"));
        mjybwWithBLOBs.setHTLS_U(tranObj.getHead("HTLS_U"));
        mjybwWithBLOBs.setHTRQ_U(formatter.parse(tranObj.getHead("HTRQ_U")));

        mjybwMapper.updateByPrimaryKeySelective(mjybwWithBLOBs);
        return true;
    }
}
