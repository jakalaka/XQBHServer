package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DKHXX;
import XQBHServer.Server.Table.Model.DKHXXKey;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Utils.log.Logger;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class KHLogin extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String sKHBH_U = tranObj.getHead("KHBH_U");
        String sIP_UUU = tranObj.getHead("IP_UUU");
        String sKHMM_U = tranObj.getString("KHMM_U");
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U=" + sKHBH_U);
        Logger.log(tranObj, "LOG_IO", "sIP_UUU=" + sIP_UUU);
        Logger.log(tranObj, "LOG_IO", "sKHMM_U=" + sKHMM_U);

        /*==================================codeBegin=====================================*/
        DKHXXMapper dkhxxMapper = tranObj.sqlSession.getMapper(DKHXXMapper.class);
        DKHXXKey dkhxxKey = new DKHXXKey();
        dkhxxKey.setKHBH_U(sKHBH_U);
        dkhxxKey.setFRDM_U("9999");

        DKHXX dkhxx = null;
        try {
            dkhxx = dkhxxMapper.selectByPrimaryKey(dkhxxKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if(dkhxx == null)
        {
            Tran.runERR(tranObj, "SQLNFD");
            return false;
        }
        if (!sKHMM_U.equals(dkhxx.getKHMM_U()))
        {
            Tran.runERR(tranObj, "LOG004");
            return false;

        }


        dkhxx.setIP_UUU(sIP_UUU);
        dkhxx.setSCDLRQ(tranObj.date);
        dkhxx.setSCDLSJ(tranObj.date);
        try {
            dkhxxMapper.updateByPrimaryKey(dkhxx);
        }catch (Exception e)
        {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLUPD");
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
