package ServerAPI;

import Server.Table.Mapper.CCPSXMapper;
import Server.Table.Mapper.DZDXXMapper;
import Server.Table.Model.CCPSX;
import Server.Table.Model.CCPSXKey;
import Server.Table.Model.DZDXX;
import Server.Table.Model.DZDXXKey;
import Server.Table.basic.DBAccess;
import ServerTran.TranObj;
import Utils.log.Logger;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class JCZDHFX {
    public static boolean exec(SqlSession sqlSession, String ZDBH_U, String ZDJYM_) throws IOException {
        Logger.log("LOG_IO",">>>>>>>>>>>>>>>>");
        DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setFRDM_U("9999");
        dzdxxKey.setZDBH_U(ZDBH_U);
        DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
        if (null == dzdxx)
            return false;
        if (!ZDJYM_.equals(dzdxx.getZDJYM_()))
            return false;
        if ("2".equals(dzdxx.getZDDLZT()))
            return false;
        Logger.log("LOG_IO","<<<<<<<<<<<<<<<<<");
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
//            System.out.println("err1");
//            return;
//        }
//        if (!ZDJYM_.equals(dzdxx.getZDJYM_())) {
//            System.out.println("err2");
//            return;
//        }
//        System.out.println("ZDBH_U="+ZDBH_U);
//        System.out.println("ZDJYM_="+ZDJYM_);
//
//    }

}
