package XQBHServer.ServerAPI;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DKHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DZDXXMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 * 只做合法性查询
 */
public class JCZDHFX {
    public static boolean exec(TranObj tranObj) throws IOException {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sZDBH_U = tranObj.getHead("ZDBH_U");
        String sZDJYM_ = tranObj.getHead("ZDJYM_");
        String sKHDLZH = tranObj.getHead("KHDLZH");
        String sSHBH_U = tranObj.getHead("SHBH_U");
        String sHTJYM_ = tranObj.getHead("HTJYM_");
        String sIP_UUU = tranObj.getHead("IP_UUU");
        Logger.log(tranObj, "LOG_IO", "sZDBH_U=" + sZDBH_U);
        Logger.log(tranObj, "LOG_IO", "sZDJYM_=" + sZDJYM_);
        Logger.log(tranObj, "LOG_IO", "sKHDLZH=" + sKHDLZH);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U=" + sSHBH_U);
        Logger.log(tranObj, "LOG_IO", "sHTJYM_=" + sHTJYM_);
        Logger.log(tranObj, "LOG_IO", "sIP_UUU=" + sIP_UUU);


        boolean checkIPFlg = true;
        if ("ZDLogin".equals(sHTJYM_) || "KHLogin".equals(sHTJYM_) || "SHLogin".equals(sHTJYM_))
            checkIPFlg = false;

        if (sZDBH_U != null && !"".equals(sZDBH_U)) { //有终端信息时验证终端信息
            DZDXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DZDXXMapper.class);
            DZDXXKey dzdxxKey = new DZDXXKey();
            dzdxxKey.setFRDM_U("9999");
            dzdxxKey.setZDBH_U(sZDBH_U);
            DZDXX dzdxx = null;
            try {
                dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "SQLSEL");
                return false;
            }
            if (null == dzdxx) {
                Logger.log(tranObj, "LOG_ERR", "查无对应终端");
                Tran.runERR(tranObj, "SQLNFD");

                return false;
            }
            if (!dzdxx.getJLZT_U().equals("0")) {
                Logger.log(tranObj, "LOG_ERR", "记录状态异常[" + dzdxx.getJLZT_U() + "]");
                Tran.runERR(tranObj, "LOG002");
                return false;
            }
            if (!sZDJYM_.equals(dzdxx.getZDJYM_())) {
                Logger.log(tranObj, "LOG_ERR", "校验码错误");
                Tran.runERR(tranObj, "LOG007");
                return false;
            }
            if ("2".equals(dzdxx.getZDDLZT())) {
                Logger.log(tranObj, "LOG_ERR", "终端登录状态异常[" + dzdxx.getZDDLZT() + "]");
                Tran.runERR(tranObj, "LOG002");
                return false;
            }
            if (checkIPFlg)
                if (!sIP_UUU.equals(dzdxx.getIP_UUU())) {
                    Tran.runERR(tranObj, "LOG003", dzdxx.getIP_UUU(), sIP_UUU);
                    return false;
                }

            tranObj.setHead("SHBH_U", dzdxx.getSHBH_U()); //将所属商户编号写入报文头，避免二次查询
            tranObj.setHead("KHBH_U",dzdxx.getKHBH_U());
            tranObj.setHead("QTDX_U", "zd");

        } else {
            if (sSHBH_U != null && !"".equals(sSHBH_U)) //商户编号不为空则验证商户信息
            {
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
                if (dshxx == null) {
                    Logger.log(tranObj, "LOG_ERR", "查无对应商户");
                    Tran.runERR(tranObj, "SQLNFD");
                    return false;
                }
                if (!dshxx.getJLZT_U().equals("0")) {
                    Logger.log(tranObj, "LOG_ERR", "记录状态异常[" + dshxx.getJLZT_U() + "]");
                    Tran.runERR(tranObj, "LOG005");
                    return false;
                }
                if (checkIPFlg)
                    if (!sIP_UUU.equals(dshxx.getIP_UUU())) {
                        Tran.runERR(tranObj, "LOG003", dshxx.getIP_UUU(), sIP_UUU);
                        return false;
                    }
                tranObj.setHead("QTDX_U", "sh");
                tranObj.setHead("KHBH_U",dshxx.getKHBH_U());  //将所属客户编号写入报文头，避免二次查询

            } else {
                //客户登录需要通过客户登录账号来登录
                DKHXXMapper dkhxxMapper = tranObj.sqlSession.getMapper(DKHXXMapper.class);
                DKHXXExample dkhxxExample = new DKHXXExample();
                dkhxxExample.or().andFRDM_UEqualTo("9999").andKHDLZHEqualTo(sKHDLZH);
                List<DKHXX> dkhxxList;
                DKHXX dkhxx = null;
                try {
                    dkhxxList = dkhxxMapper.selectByExample(dkhxxExample);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "SQLSEL");
                    return false;
                }
                if (dkhxxList.size() == 0) {
                    Logger.log(tranObj, "LOG_ERR", "查无对应客户");
                    Tran.runERR(tranObj, "SQLNFD");
                    return false;
                }
                dkhxx = dkhxxList.get(0);
                if (!dkhxx.getJLZT_U().equals("0")) {
                    Logger.log(tranObj, "LOG_ERR", "记录状态异常[" + dkhxx.getJLZT_U() + "]");
                    Tran.runERR(tranObj, "LOG006");
                    return false;
                }
                if (checkIPFlg)
                    if (!sIP_UUU.equals(dkhxx.getIP_UUU())) {
                        Tran.runERR(tranObj, "LOG003", dkhxx.getIP_UUU(), sIP_UUU);
                        return false;
                    }

                tranObj.setHead("KHBH_U", dkhxx.getKHBH_U()); //将所属客户编号写入报文头，避免二次查询
                tranObj.setHead("QTDX_U", "kh");

            }

        }


        Logger.log(tranObj, "LOG_IO", Com.getOut);
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
