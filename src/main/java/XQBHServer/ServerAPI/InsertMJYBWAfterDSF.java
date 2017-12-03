package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBWKey;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.response.AlipayTradePayResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
暂时写死为支付宝，到时候在加微信
 */
public class InsertMJYBWAfterDSF {
    public static boolean exec(TranObj tranObj, AlipayTradePayResponse response) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sSFFHBW = response.getBody();
        Logger.log(tranObj, "LOG_IO", "sSFFHBW=" + sSFFHBW);

        String sQTRQ_U = tranObj.getHead("QTRQ_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(sQTRQ_U);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBWWithBLOBs mjybwWithBLOBs = new MJYBWWithBLOBs();
        mjybwWithBLOBs.setFRDM_U("9999");
        mjybwWithBLOBs.setQTRQ_U(date);
        mjybwWithBLOBs.setQTLS_U(tranObj.getHead("QTLS_U"));
        mjybwWithBLOBs.setXH_UUU(tranObj.iBWXH);
        mjybwWithBLOBs.setQTJYM_(tranObj.getHead("QTJYM_"));
        mjybwWithBLOBs.setDYJYM_(tranObj.getHead("HTJYM_"));
        mjybwWithBLOBs.setBWLX_U("ZO");

        try {
            mjybwWithBLOBs.setBW_UUU(sSFFHBW.getBytes("GBK"));//非要GBK才能看到中文，艹
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
        mjybwWithBLOBs.setCWDM_U(response.getCode());
        mjybwWithBLOBs.setZCWDM_(response.getSubCode());
        mjybwWithBLOBs.setCWXX_U(response.getSubMsg());
        try {
            mjybwMapper.insert(mjybwWithBLOBs);
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
