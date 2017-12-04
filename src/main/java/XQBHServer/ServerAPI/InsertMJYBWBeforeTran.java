package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
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
/*
暂时写死为支付宝，到时候在加微信
 */
public class InsertMJYBWBeforeTran {
    public static boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String rqTmp = tranObj.getHead("QTRQ_U");
        Date date = null;
        try {
            date = formatter.parse(rqTmp);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBW mjybw = new MJYBW();
        mjybw.setFRDM_U("9999");
        mjybw.setQTRQ_U(date);
        mjybw.setQTLS_U(tranObj.getHead("QTLS_U"));
        mjybw.setXH_UUU(tranObj.iBWXH);
        mjybw.setQTJYM_(tranObj.getHead("QTJYM_"));
        mjybw.setDYJYM_(tranObj.getHead("HTJYM_"));

        mjybw.setBWLX_U("CI");
        try {
            mjybw.setBW_UUU(tranObj.bwIn.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }


        mjybw.setZDBH_U(tranObj.getHead("ZDBH_U"));
        mjybw.setIP_UUU(tranObj.getHead("IP_UUU"));
        mjybw.setJLZT_U("0");
        try {
            mjybwMapper.insert(mjybw);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
//        tranObj.sqlSession_BW.commit();
        tranObj.iBWXH++;
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
