package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
/*
暂时写死为支付宝，到时候在加微信
 */
public class InsertMJYBWBeforeTran {
    public static boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);

        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQTLS_U_head = tranObj.getHead("QTLS_U");
        String sQTJYM__head = tranObj.getHead("QTJYM_");
        String sHTJYM__head = tranObj.getHead("HTJYM_");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sIP_UUU_head = tranObj.getHead("IP_UUU");
        Logger.log(tranObj,"LOG_DEBUG","sQTRQ_U_head="+sQTRQ_U_head);
        Logger.log(tranObj,"LOG_DEBUG","sQTLS_U_head="+sQTLS_U_head);
        Logger.log(tranObj,"LOG_DEBUG","sQTJYM__head="+sQTJYM__head);
        Logger.log(tranObj,"LOG_DEBUG","sHTJYM__head="+sHTJYM__head);
        Logger.log(tranObj,"LOG_DEBUG","sZDBH_U_head="+sZDBH_U_head);
        Logger.log(tranObj,"LOG_DEBUG","sIP_UUU_head="+sIP_UUU_head);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(sQTRQ_U_head);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBW mjybw = new MJYBW();
        mjybw.setFRDM_U("9999");
        mjybw.setQTRQ_U(date);
        mjybw.setQTLS_U(sQTLS_U_head);
        mjybw.setXH_UUU(tranObj.iBWXH);
        mjybw.setQTJYM_(sQTJYM__head);
        mjybw.setDYJYM_(sHTJYM__head);

        mjybw.setBWLX_U("CI");
        try {
            mjybw.setBW_UUU(tranObj.bwIn.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }


        mjybw.setZDBH_U(sZDBH_U_head);
        mjybw.setIP_UUU(sIP_UUU_head);
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
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }
}
