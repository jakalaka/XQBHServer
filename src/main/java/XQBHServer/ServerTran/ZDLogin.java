package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Utils.log.Logger;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class ZDLogin extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String sZDJYM_ = tranObj.getHead("ZDJYM_");
        String sZDBH_U = tranObj.getHead("ZDBH_U");
        String sIP_UUU = tranObj.getHead("IP_UUU");
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        Logger.log(tranObj, "LOG_IO", "sZDJYM_=" + sZDJYM_);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U=" + sZDBH_U);
        Logger.log(tranObj, "LOG_IO", "sIP_UUU=" + sIP_UUU);

        /*==================================codeBegin=====================================*/
        DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setZDBH_U(sZDBH_U);
        dzdxxKey.setFRDM_U("9999");

        DZDXX dzdxx = null;
        try {
            dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }

        if ("0".equals(dzdxx.getZDDLZT()) || "1".equals(dzdxx.getZDDLZT())) {
            dzdxx.setZDDLZT("1");
            dzdxx.setIP_UUU(sIP_UUU);
            dzdxx.setSCDLRQ(tranObj.date);
            dzdxx.setSCDLSJ(tranObj.date);
            try {
                dzdxxMapper.updateByPrimaryKey(dzdxx);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "SQLUPD");
                return false;
            }


        } else {
            runERR(tranObj, "LOG002");
            return false;
        }
        tranObj.TranMap.put("re", "Jakalaka Technology Co. Ltd");
        Com.tmpCount++;
        Logger.log(tranObj, "LOG_DEBUG", "" + Com.tmpCount);
         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
