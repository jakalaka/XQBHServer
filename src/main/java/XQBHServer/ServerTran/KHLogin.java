package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class KHLogin extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String sKHDLZH = tranObj.getHead("KHDLZH");
        String sIP_UUU = tranObj.getHead("IP_UUU");
        String sKHMM_U = tranObj.getString("KHMM_U");
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        Logger.log(tranObj, "LOG_IO", "sKHDLZH=" + sKHDLZH);
        Logger.log(tranObj, "LOG_IO", "sIP_UUU=" + sIP_UUU);
        Logger.log(tranObj, "LOG_IO", "sKHMM_U=" + sKHMM_U);

        /*==================================codeBegin=====================================*/
        DKHXXMapper dkhxxMapper = tranObj.sqlSession.getMapper(DKHXXMapper.class);

        DKHXXExample dkhxxExample=new DKHXXExample();
        dkhxxExample.or().andFRDM_UEqualTo("9999").andKHDLZHEqualTo(sKHDLZH);
        List<DKHXX> dkhxxList=null;
        DKHXX dkhxx = null;
        try {
            dkhxxList = dkhxxMapper.selectByExample(dkhxxExample);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if(dkhxxList.size() == 0)
        {
            Tran.runERR(tranObj, "SQLNFD");
            return false;
        }
        dkhxx=dkhxxList.get(0);
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


        //返回所有商户的列表
        DSHXXExample dshxxExample=new DSHXXExample();
        dshxxExample.or().andFRDM_UEqualTo("9999").andJLZT_UEqualTo("0").andKHBH_UEqualTo(dkhxx.getKHBH_U());
        DSHXXMapper dshxxMapper=tranObj.sqlSession.getMapper(DSHXXMapper.class);
        List<DSHXX> listDSHXX;
        try {
            listDSHXX = dshxxMapper.selectByExample(dshxxExample);
        }catch (Exception e)
        {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }

        String sSHXX_U="";
        List<String> listSHBH_U=new ArrayList();
        for (DSHXX dshxx :
                listDSHXX) {
            sSHXX_U=sSHXX_U+dshxx.getSHBH_U()+"|";
            listSHBH_U.add(dshxx.getSHBH_U());

        }

        //返回所有终端的列表
        DZDXXExample dzdxxExample=new DZDXXExample();
        dzdxxExample.or().andFRDM_UEqualTo("9999").andJLZT_UEqualTo("0").andSHBH_UIn(listSHBH_U);
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
        tranObj.TranMap.put("SHXX_U", sSHXX_U);
        tranObj.TranMap.put("re", "Jakalaka Technology Co. Ltd");
        Com.tmpCount++;
        Logger.log(tranObj, "LOG_DEBUG", "" + Com.tmpCount);
         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
