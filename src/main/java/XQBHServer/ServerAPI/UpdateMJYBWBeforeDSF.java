package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBWKey;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.request.AlipayTradePayRequest;
import org.apache.ibatis.session.SqlSession;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMJYBWBeforeDSF {
    public static boolean exec(TranObj tranObj, AlipayTradePayRequest request) {
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        String sSFQQBW=request.getBizContent();
        Logger.log(tranObj, "LOG_IO", "sSFQQBW=" + sSFQQBW);

        String sQTRQ_U = tranObj.getHead("QTRQ_U");
        String sQTLS_U = tranObj.getHead("QTLS_U");
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
        MJYBWKey mjybwKey = new MJYBWKey();
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
        try {
            mjybwWithBLOBs.setSFQQBW(sSFQQBW.getBytes("GBK"));//非要GBK才能看到中文，艹
        } catch (UnsupportedEncodingException e) {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }
        mjybwWithBLOBs.setSFLS_U(request.getTextParams().get("out_trade_no"));
        mjybwWithBLOBs.setJYZT_U("2");//先置为2
        try {
            mjybwMapper.updateByPrimaryKeySelective(mjybwWithBLOBs);
        }catch (Exception e)
        {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLUPD");
            return false;
        }
        tranObj.sqlSession_BW.commit();
        Logger.log(tranObj,"LOG_IO", Com.getOut);
        return true;
    }
}
