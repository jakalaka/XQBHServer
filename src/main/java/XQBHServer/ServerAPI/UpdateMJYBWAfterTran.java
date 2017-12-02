package XQBHServer.ServerAPI;

import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWKey;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMJYBWAfterTran {
    public static boolean exec(TranObj tranObj)  {
        String sQTRQ_U=tranObj.getHead("QTRQ_U");
        String sQTLS_U=tranObj.getHead("QTLS_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(sQTRQ_U);
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper=tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBWKey mjybwKey=new MJYBWKey();
        mjybwKey.setFRDM_U("9999");
        mjybwKey.setQTLS_U(sQTLS_U);
        mjybwKey.setQTRQ_U(date);

        MJYBWWithBLOBs mjybwWithBLOBs=null;
        try {
            mjybwWithBLOBs = mjybwMapper.selectByPrimaryKey(mjybwKey);
        }catch (Exception e)
        {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (tranObj.unknownFlg)
            mjybwWithBLOBs.setJYZT_U("2");
        else
            mjybwWithBLOBs.setJYZT_U("1");
        mjybwWithBLOBs.setCWDM_U(tranObj.getHead("CWDM_U"));
        mjybwWithBLOBs.setCWXX_U(tranObj.getHead("CWXX_U"));
        mjybwWithBLOBs.setHTLS_U(tranObj.getHead("HTLS_U"));
        try {
            mjybwWithBLOBs.setHTRQ_U(formatter.parse(tranObj.getHead("HTRQ_U")));
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
        try {
            mjybwWithBLOBs.setCCBW_U(tranObj.bwOut.getBytes("GBK"));//非要GBK才能看到中文，艹
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
        try {
            mjybwMapper.updateByPrimaryKeySelective(mjybwWithBLOBs);
        }catch (Exception e)
        {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLUPD");
            return false;
        }
        tranObj.sqlSession_BW.commit();
        tranObj.sqlSession_BW.close();

        return true;
    }
}
