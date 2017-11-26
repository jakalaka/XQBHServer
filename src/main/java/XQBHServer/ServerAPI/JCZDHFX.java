package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXKey;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4 0004.
 * 只做合法性查询
 */
public class JCZDHFX {
    public static boolean exec(TranObj tranObj) throws IOException {
        Logger.log(tranObj,"LOG_IO", Com.getIn);
        String sZDBH_U=tranObj.getHead("ZDBH_U");
        String sZDJYM_=tranObj.getHead("ZDJYM_");
        Logger.log(tranObj,"LOG_IO","sZDBH_U="+sZDBH_U);
        Logger.log(tranObj,"LOG_IO","sZDJYM_="+sZDJYM_);
        DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setFRDM_U("9999");
        dzdxxKey.setZDBH_U(sZDBH_U);
        DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        if (null == dzdxx)
            return false;
        if (!sZDJYM_.equals(dzdxx.getZDJYM_()))
            return false;
        if ("2".equals(dzdxx.getZDDLZT()))
            return false;
        tranObj.setHead("SHBH_U",dzdxx.getSHBH_U());
        Logger.log(tranObj,"LOG_IO", Com.getOut);
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
