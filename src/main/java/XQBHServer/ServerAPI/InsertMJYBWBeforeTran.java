package XQBHServer.ServerAPI;

import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class InsertMJYBWBeforeTran {
    public static boolean exec(TranObj tranObj) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String rqTmp = tranObj.getHead("QTRQ_U") ;
        Date date = null;
        try {
            date = formatter.parse(rqTmp);
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
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
        try {
            mjybwWithBLOBs.setCRBW_U(tranObj.bwIn.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
        mjybwMapper.insert(mjybwWithBLOBs);
        tranObj.sqlSession_BW.commit();

        return true;
    }
}
