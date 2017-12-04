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
暂时写死为支付宝，到时候在加微信
 */
public class InsertMJYBWBeforeDSF {
    public static boolean exec(TranObj tranObj, AlipayTradePayRequest request) {
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        String sSFQQBW=request.getBizContent();
        Logger.log(tranObj, "LOG_IO", "sSFQQBW=" + sSFQQBW);
        String sQTRQ_U = tranObj.getHead("QTRQ_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(sQTRQ_U);
        } catch (ParseException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBW mjybw=new MJYBW();
        mjybw.setFRDM_U("9999");
        mjybw.setQTRQ_U(date);
        mjybw.setQTLS_U(tranObj.getHead("QTLS_U"));
        mjybw.setXH_UUU(tranObj.iBWXH);
        mjybw.setQTJYM_(tranObj.getHead("QTJYM_"));
        mjybw.setDYJYM_(tranObj.getHead("HTJYM_"));
        mjybw.setBWLX_U("ZO");

        try {
            mjybw.setBW_UUU(sSFQQBW.getBytes("GBK"));//非要GBK才能看到中文，艹
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }

        if("1".equals(tranObj.getHead("CSBZ_U")))
        {
            mjybw.setIP_UUU(Com.alipayGateway_cs);
        }else {
            mjybw.setIP_UUU(Com.alipayGateway);
        }
        mjybw.setZDBH_U(tranObj.getHead("ZDBH_U"));
        mjybw.setJLZT_U("0");

        try {
            mjybwMapper.insert(mjybw);
        }catch (Exception e)
        {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
//        tranObj.sqlSession_BW.commit();
        tranObj.iBWXH++;
        Logger.log(tranObj,"LOG_IO", Com.getOut);
        return true;
    }
}
