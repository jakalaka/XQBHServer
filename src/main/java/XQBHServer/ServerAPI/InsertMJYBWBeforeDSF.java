package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.request.AlipayTradePayRequest;
import org.apache.ibatis.session.SqlSession;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
ZFZHLX
z-支付宝
w-微信
 */
public class InsertMJYBWBeforeDSF {
    public static boolean exec(TranObj tranObj, String sSFQQBW, String sZFZHLX) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);

        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQTLS_U_head = tranObj.getHead("QTLS_U");
        String sQTJYM__head = tranObj.getHead("QTJYM_");
        String sHTJYM__head = tranObj.getHead("HTJYM_");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sIP_UUU_head = tranObj.getHead("IP_UUU");
        Logger.log(tranObj, "LOG_DEBUG", "sQTRQ_U_head=" + sQTRQ_U_head);
        Logger.log(tranObj, "LOG_DEBUG", "sQTLS_U_head=" + sQTLS_U_head);
        Logger.log(tranObj, "LOG_DEBUG", "sQTJYM__head=" + sQTJYM__head);
        Logger.log(tranObj, "LOG_DEBUG", "sHTJYM__head=" + sHTJYM__head);
        Logger.log(tranObj, "LOG_DEBUG", "sZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_DEBUG", "sIP_UUU_head=" + sIP_UUU_head);

        Logger.log(tranObj, "LOG_IO", "sSFQQBW=" + sSFQQBW);

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
        if (sZFZHLX.equals("z")) {
            mjybw.setIP_UUU(Com.alipayGateway);
            mjybw.setBWLX_U("ZO");
        } else if (sZFZHLX.equals("w")) {
            mjybw.setBWLX_U("WO");
            mjybw.setIP_UUU(Com.wechatpayGateway);
        } else {
            Tran.runERR(tranObj, "PAYTYP", sZFZHLX);
            return false;
        }
        try {
            mjybw.setBW_UUU(sSFQQBW.getBytes("GBK"));//非要GBK才能看到中文，艹
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }


        mjybw.setZDBH_U(sZDBH_U_head);
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
