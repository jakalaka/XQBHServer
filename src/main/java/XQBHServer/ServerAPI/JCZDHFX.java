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
 * ֻ���Ϸ��Բ�ѯ
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

        if (sZDBH_U != null && !"".equals(sZDBH_U)) { //���ն���Ϣʱ��֤�ն���Ϣ
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
                Logger.log(tranObj, "LOG_ERR", "���޶�Ӧ�ն�");
                Tran.runERR(tranObj, "SQLNFD");

                return false;
            }
            if (!dzdxx.getJLZT_U().equals("0")) {
                Logger.log(tranObj, "LOG_ERR", "��¼״̬�쳣[" + dzdxx.getJLZT_U() + "]");
                Tran.runERR(tranObj, "LOG002");
                return false;
            }
            if (!sZDJYM_.equals(dzdxx.getZDJYM_())) {
                Logger.log(tranObj, "LOG_ERR", "У�������");
                Tran.runERR(tranObj, "LOG007");
                return false;
            }
            if ("2".equals(dzdxx.getZDDLZT())) {
                Logger.log(tranObj, "LOG_ERR", "�ն˵�¼״̬�쳣[" + dzdxx.getZDDLZT() + "]");
                Tran.runERR(tranObj, "LOG002");
                return false;
            }
            if (checkIPFlg)
                if (!sIP_UUU.equals(dzdxx.getIP_UUU())) {
                    Tran.runERR(tranObj, "LOG003", dzdxx.getIP_UUU(), sIP_UUU);
                    return false;
                }

            tranObj.setHead("SHBH_U", dzdxx.getSHBH_U()); //�������̻����д�뱨��ͷ��������β�ѯ
            tranObj.setHead("KHBH_U",dzdxx.getKHBH_U());
            tranObj.setHead("QTDX_U", "zd");

        } else {
            if (sSHBH_U != null && !"".equals(sSHBH_U)) //�̻���Ų�Ϊ������֤�̻���Ϣ
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
                    Logger.log(tranObj, "LOG_ERR", "���޶�Ӧ�̻�");
                    Tran.runERR(tranObj, "SQLNFD");
                    return false;
                }
                if (!dshxx.getJLZT_U().equals("0")) {
                    Logger.log(tranObj, "LOG_ERR", "��¼״̬�쳣[" + dshxx.getJLZT_U() + "]");
                    Tran.runERR(tranObj, "LOG005");
                    return false;
                }
                if (checkIPFlg)
                    if (!sIP_UUU.equals(dshxx.getIP_UUU())) {
                        Tran.runERR(tranObj, "LOG003", dshxx.getIP_UUU(), sIP_UUU);
                        return false;
                    }
                tranObj.setHead("QTDX_U", "sh");
                tranObj.setHead("KHBH_U",dshxx.getKHBH_U());  //�������ͻ����д�뱨��ͷ��������β�ѯ

            } else {
                //�ͻ���¼��Ҫͨ���ͻ���¼�˺�����¼
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
                    Logger.log(tranObj, "LOG_ERR", "���޶�Ӧ�ͻ�");
                    Tran.runERR(tranObj, "SQLNFD");
                    return false;
                }
                dkhxx = dkhxxList.get(0);
                if (!dkhxx.getJLZT_U().equals("0")) {
                    Logger.log(tranObj, "LOG_ERR", "��¼״̬�쳣[" + dkhxx.getJLZT_U() + "]");
                    Tran.runERR(tranObj, "LOG006");
                    return false;
                }
                if (checkIPFlg)
                    if (!sIP_UUU.equals(dkhxx.getIP_UUU())) {
                        Tran.runERR(tranObj, "LOG003", dkhxx.getIP_UUU(), sIP_UUU);
                        return false;
                    }

                tranObj.setHead("KHBH_U", dkhxx.getKHBH_U()); //�������ͻ����д�뱨��ͷ��������β�ѯ
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
