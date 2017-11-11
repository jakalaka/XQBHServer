package XQBHServer.ServerTran;


import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.Utils.log.Logger;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class ZDLogin extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String ZDJYM_ = tranObj.getHead("ZDJYM_");
        String ZDBH_U = tranObj.getHead("ZDBH_U");
        String IP_UUU = tranObj.getHead("IP_UUU");
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        Logger.log(tranObj,"LOG_IO", "ZDJYM_=" + ZDJYM_);
        Logger.log(tranObj,"LOG_IO", "ZDBH_U=" + ZDBH_U);

//        if (Com.getHTLS(tranObj)==false)
//            return false;



        DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setZDBH_U(ZDBH_U);
        dzdxxKey.setFRDM_U("9999");

        DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);

        if ("0".equals(dzdxx.getZDDLZT()) || "1".equals(dzdxx.getZDDLZT())) {
            dzdxx.setZDDLZT("1");
            dzdxx.setIP_UUU(IP_UUU);
            dzdxx.setSCDLRQ(tranObj.date);
            dzdxx.setSCDLSJ(tranObj.date);

            dzdxxMapper.updateByPrimaryKey(dzdxx);
        } else {
            runERR(tranObj, "LOG002");
            return false;
        }
        tranObj.TranMap.put("re","哈哈哈");
        Com.tmpCount++;
        Logger.log(tranObj,"LOG_DEBUG",""+Com.tmpCount);
        Logger.log(tranObj,"LOG_IO", Com.getOut);
        return true;
    }

}
