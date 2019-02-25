package XQBHServer.ServerTran;
/*本交易不上第三方*/

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Utils.log.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static XQBHServer.Utils.Data.DataUtils.Date2StringofTime;
import static XQBHServer.Utils.Data.DataUtils.Date2StringofYear;

/**
 * Created by Administrator on 2017/7/4 0004.
 * <p>
 * CXFS_U：z-按支付宝流水 w-按微信流水 h-按后台日期流水
 */


public class ControllerSingleQuery extends Tran {


    @Override
    public boolean exec(TranObj tranObj) {
        /*
        从报文头中找信息
         */
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sCXFS_U = tranObj.getString("CXFS_U");
        String sZFBDDH = tranObj.getString("ZFBDDH");
        String sWXDH_U = tranObj.getString("WXDH_U");
        String sHTRQ_U = tranObj.getString("HTRQ_U");
        String sHTLS_U = tranObj.getString("HTLS_U");

        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);

        Logger.log(tranObj, "LOG_IO", "sQTDX_U_head=" + sQTDX_U_head);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sCXFS_U=" + sCXFS_U);
        Logger.log(tranObj, "LOG_IO", "sZFBLS_=" + sZFBDDH);
        Logger.log(tranObj, "LOG_IO", "sWXLS_U=" + sWXDH_U);
        Logger.log(tranObj, "LOG_IO", "sHTRQ_U=" + sHTRQ_U);
        Logger.log(tranObj, "LOG_IO", "sHTLS_U=" + sHTLS_U);
        /*==================================codeBegin=====================================*/

        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJExample mdzsjExample = new MDZSJExample();
        MDZSJExample.Criteria criteria_mdzsj = mdzsjExample.createCriteria();
        criteria_mdzsj.andFRDM_UEqualTo("9999");


        switch (sCXFS_U) {//z-支付宝 w-微信 h-后台流水日期
            case "z":
                criteria_mdzsj.andZFZHLXEqualTo("z");
                criteria_mdzsj.andSFDH_UEqualTo(sZFBDDH);
                break;
            case "w":
                criteria_mdzsj.andZFZHLXEqualTo("w");
                criteria_mdzsj.andSFDH_UEqualTo(sWXDH_U);
                break;
            case "h":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    date = formatter.parse(sHTRQ_U);
                } catch (ParseException e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    Tran.runERR(tranObj, "TIMEER");
                    return false;
                }
                criteria_mdzsj.andHTRQ_UEqualTo(date).andHTLS_UEqualTo(sHTLS_U);
                break;
            default:
                Tran.runERR(tranObj, "CXCW02");
                return false;
        }

        if ("sh".equals(sQTDX_U_head) && sSHBH_U_head.length() > 0) {
            criteria_mdzsj.andSHBH_UEqualTo(sSHBH_U_head);
        } else if ("kh".equals(sQTDX_U_head) &&sKHBH_U_head.length() > 0) {//客户需查询出所有商户信息
            criteria_mdzsj.andKHBH_UEqualTo(sKHBH_U_head);
        } else {
            Tran.runERR(tranObj, "CXCW01");
            return false;
        }


        List<MDZSJ> mdzsjList = null;

        try {
            mdzsjList = mdzsjMapper.selectByExample(mdzsjExample);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (mdzsjList.size() == 0) {
            Tran.runERR(tranObj, "SQLNFD");
            return false;
        } else if (mdzsjList.size() > 1) {
            Tran.runERR(tranObj, "SQLMRE");
            return false;
        }
        MDZSJ mdzsj = mdzsjList.get(0);


        tranObj.BodyMap.put("YHTRQ_", Date2StringofYear(mdzsj.getHTRQ_U()));
        tranObj.BodyMap.put("YHTSJ_", Date2StringofTime(mdzsj.getJYSJ_U()));
        tranObj.BodyMap.put("YHTLS_", mdzsj.getHTLS_U());
        tranObj.BodyMap.put("YHTJYM", mdzsj.getHTJYM_());
        tranObj.BodyMap.put("ZFZHLX", mdzsj.getZFZHLX());
        tranObj.BodyMap.put("JYJE_U", mdzsj.getJYJE_U());
        tranObj.BodyMap.put("SFDH_U", mdzsj.getSFDH_U());
        tranObj.BodyMap.put("SHBH_U", mdzsj.getSHBH_U());
        tranObj.BodyMap.put("ZDBH_U", mdzsj.getZDBH_U());
        tranObj.BodyMap.put("SPXX_U", mdzsj.getSPXX_U());
        tranObj.BodyMap.put("FKM_UU", mdzsj.getFKM_UU());
        tranObj.BodyMap.put("JYZT_U", mdzsj.getJYZT_U());

         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }
}
