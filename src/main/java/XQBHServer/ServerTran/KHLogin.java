package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Utils.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<String> listSHBH_U=new ArrayList();



        List lLSSHXX=new ArrayList();
        for (DSHXX dshxx :
                listDSHXX) {
            Map map=new HashMap();
            map.put("KHBH_U",dshxx.getKHBH_U());
            map.put("SHBH_U",dshxx.getSHBH_U());
            lLSSHXX.add(map);
            listSHBH_U.add(dshxx.getSHBH_U());

        }
        if (listSHBH_U.size()==0)
        {
            Tran.runERR(tranObj, "SHMISS");
            return false;
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

        if (listDZDXX.size()==0)
        {
            Tran.runERR(tranObj, "ZDMISS");
            return false;
        }

        List lLSZDXX=new ArrayList();
        for (DZDXX dzdxx :
                listDZDXX) {
            Map map=new HashMap();
            map.put("IP_UUU",dzdxx.getIP_UUU());
            map.put("ZDBH_U",dzdxx.getZDBH_U());
            map.put("SHBH_U",dzdxx.getSHBH_U());
            lLSZDXX.add(map);

        }



        tranObj.TranMap.put("ZDLIST", lLSZDXX);
        tranObj.TranMap.put("SHLIST", lLSSHXX);
        tranObj.TranMap.put("KHBH_U", dkhxx.getKHBH_U());



        tranObj.TranMap.put("re", "Jakalaka Technology Co. Ltd");
        Com.tmpCount++;
        Logger.log(tranObj, "LOG_DEBUG", "" + Com.tmpCount);
         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
