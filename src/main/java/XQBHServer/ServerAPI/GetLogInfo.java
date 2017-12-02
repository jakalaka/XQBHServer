package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4 0004.
 * 只做合法性查询
 */
public class GetLogInfo {
    /**
     * @param tranObj
     * @return
     * @throws IOException
     * 主要处理LOG的初始化
     */
    public static boolean exec(TranObj tranObj)  {

        CXTCSMapper cxtcsMapper = tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("flLogLV");
        CXTCS cxtcs=null;

        try{
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        }catch (Exception e)
        {
            Logger.logException(tranObj,"LOG_ERR",e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == cxtcs) {
            tranObj.flLogLV="DEBUG";
        }
        if("SYS".equals(cxtcs.getVALUE_()))
            tranObj.flLogLV="SYS";
        else if("ERR".equals(cxtcs.getVALUE_()))
            tranObj.flLogLV="ERR";
        else if("IO".equals(cxtcs.getVALUE_()))
            tranObj.flLogLV="IO";
        else if("DEBUG".equals(cxtcs.getVALUE_()))
            tranObj.flLogLV="DEBUG";
        else {
            //查出有问题，如何处理？
            tranObj.flLogLV="DEBUG";
        }




        return true;
    }

//    public static void main(String[] args) {
//        String ZDBH_U="SVR0000001";
//        String ZDJYM_="1234567890";
//        JCZDHFX jczdhfx=new JCZDHFX();
//        DZDXXMapper dzdxxMapper=jczdhfx.sqlSession.getMapper(DZDXXMapper.class);
//        DZDXXKey dzdxxKey=new DZDXXKey();
//        dzdxxKey.setFRDM_U("9999");
//        dzdxxKey.setZDBH_U(ZDBH_U);
//        DZDXX dzdxx=dzdxxMapper.selectByPrimaryKey(dzdxxKey);
//        if (null==dzdxx) {
//            Logger.log(tranObj,"LOG_DEBUG","err1");
//            return;
//        }
//        if (!ZDJYM_.equals(dzdxx.getZDJYM_())) {
//            Logger.log(tranObj,"LOG_DEBUG","err2");
//            return;
//        }
//        Logger.log(tranObj,"LOG_DEBUG","ZDBH_U="+ZDBH_U);
//        Logger.log(tranObj,"LOG_DEBUG","ZDJYM_="+ZDJYM_);
//
//    }

}
