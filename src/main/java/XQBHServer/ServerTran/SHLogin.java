package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Utils.log.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class SHLogin extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String sSHBH_U = tranObj.getHead("SHBH_U");
        String sIP_UUU = tranObj.getHead("IP_UUU");
        String sSHMM_U = tranObj.getString("SHMM_U");
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U=" + sSHBH_U);
        Logger.log(tranObj, "LOG_IO", "sIP_UUU=" + sIP_UUU);
        Logger.log(tranObj, "LOG_IO", "sSHMM_U=" + sSHMM_U);

        /*==================================codeBegin=====================================*/
        DSHXXMapper dshxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U);
        dshxxKey.setFRDM_U("9999");

        DSHXX dshxx = null;
        try {
            dshxx = dshxxMapper.selectByPrimaryKey(dshxxKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if(dshxx == null)
        {
            Tran.runERR(tranObj, "SQLNFD");
            return false;
        }

        if (!sSHMM_U.equals(dshxx.getSHMM_U()))
        {
            Tran.runERR(tranObj, "LOG004");
            return false;

        }


        dshxx.setIP_UUU(sIP_UUU);
        dshxx.setSCDLRQ(tranObj.date);
        dshxx.setSCDLSJ(tranObj.date);

        try {
            dshxxMapper.updateByPrimaryKey(dshxx);
        }catch (Exception e)
        {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLUPD");
            return false;
        }

        //返回所有终端的列表
        DZDXXExample dzdxxExample=new DZDXXExample();
        dzdxxExample.or().andFRDM_UEqualTo("9999").andJLZT_UEqualTo("0").andSHBH_UEqualTo(dshxx.getSHBH_U());
        DZDXXMapper dzdxxMapper=tranObj.sqlSession.getMapper(DZDXXMapper.class);
        List<DZDXX> listDZDXX;
        try {
            listDZDXX = dzdxxMapper.selectByExample(dzdxxExample);
        }catch (Exception e)
        {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }

        String sZDXX_U="";
        for (DZDXX dzdxx :
                listDZDXX) {
            sZDXX_U=sZDXX_U+dzdxx.getZDBH_U()+"|";
        }

        tranObj.TranMap.put("ZDXX_U", sZDXX_U);

        tranObj.TranMap.put("re", "Jakalaka Technology Co. Ltd");
        Com.tmpCount++;
        Logger.log(tranObj, "LOG_DEBUG", "" + Com.tmpCount);
         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
