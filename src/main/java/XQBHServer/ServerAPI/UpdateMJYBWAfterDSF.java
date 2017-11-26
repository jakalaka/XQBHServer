package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MJYBWMapper;
import XQBHServer.Server.Table.Model.MJYBWKey;
import XQBHServer.Server.Table.Model.MJYBWWithBLOBs;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.response.AlipayTradePayResponse;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMJYBWAfterDSF {
    public static boolean exec(TranObj tranObj, AlipayTradePayResponse response) throws Exception {
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        String sSFFHBW=response.getBody();
        Logger.log(tranObj, "LOG_IO", "sSFFHBW=" + sSFFHBW);

        String sQTRQ_U = tranObj.getHead("QTRQ_U");
        String sQTLS_U = tranObj.getHead("QTLS_U");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = formatter.parse(sQTRQ_U);
        MJYBWMapper mjybwMapper = tranObj.sqlSession_BW.getMapper(MJYBWMapper.class);
        MJYBWKey mjybwKey = new MJYBWKey();
        mjybwKey.setFRDM_U("9999");
        mjybwKey.setQTLS_U(sQTLS_U);
        mjybwKey.setQTRQ_U(date);
        MJYBWWithBLOBs mjybwWithBLOBs = mjybwMapper.selectByPrimaryKey(mjybwKey);
        mjybwWithBLOBs.setSFFHBW(sSFFHBW.getBytes("GBK"));//非要GBK才能看到中文，艹
        mjybwWithBLOBs.setSFXYM_(response.getCode());
        mjybwWithBLOBs.setSFYCM_(response.getSubCode());
        mjybwMapper.updateByPrimaryKeySelective(mjybwWithBLOBs);
        tranObj.sqlSession_BW.commit();
        Logger.log(tranObj,"LOG_IO", Com.getOut);

        return true;
    }
}
