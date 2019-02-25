package XQBHServer.ServerTran;
/**
 * 本交易不上第三方
 */



import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Utils.Data.DataUtils;
import XQBHServer.Utils.log.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static XQBHServer.Utils.Data.DataUtils.YearString2Date;

/**
 * Created by Administrator on 2017/7/4 0004.
 * <p>
 * CXFS_U：z-按支付宝流水 w-按微信流水 h-按后台日期流水
 */


public class ControllerStatementQuery extends Tran {


    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        /*
        从报文头中找信息
         */
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sQSRQ_U = tranObj.getString("QSRQ_U");
        String sZZRQ_U = tranObj.getString("ZZRQ_U");
        String sJYZT_U = tranObj.getString("JYZT_U");
        String sSHBH_U = tranObj.getString("SHBH_U");
        String sZDBH_U = tranObj.getString("ZDBH_U");
        String sZFZHLX = tranObj.getString("ZFZHLX");


        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);

        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sQSRQ_U=" + sQSRQ_U);
        Logger.log(tranObj, "LOG_IO", "sZZRQ_U=" + sZZRQ_U);
        Logger.log(tranObj, "LOG_IO", "sJYZT_U=" + sJYZT_U);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U=" + sSHBH_U);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U=" + sZDBH_U);
        Logger.log(tranObj, "LOG_IO", "sZFZHLX=" + sZFZHLX);


        /*==================================codeBegin=====================================*/
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJExample mdzsjExample = new MDZSJExample();
        MDZSJExample.Criteria criteria_mdzsj = mdzsjExample.createCriteria();
        criteria_mdzsj.andFRDM_UEqualTo("9999").andJLZT_UEqualTo("0");

        /*合法性检查*/
        if (sQSRQ_U.equals("")) {
            Tran.runERR(tranObj, "CXCW03");
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dQSRQ_U = null;
        try {
            dQSRQ_U = formatter.parse(sQSRQ_U);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }

        if (sZZRQ_U.equals("")) {
            Tran.runERR(tranObj, "CXCW04");
            return false;
        }
        Date dZZRQ_U = YearString2Date(tranObj,sZZRQ_U);



        criteria_mdzsj.andHTRQ_UBetween(dQSRQ_U, dZZRQ_U);

        //检查查询的商户或终端的权限合法性，前端有控制，但是本处也要控制
        //比较报文头中sSHBH_U_head、sKHBH_U_head和需要查询的SHBH_U、ZDBH_U的所属关系
        if ("kh".equals(sQTDX_U_head) && sKHBH_U_head.length() > 0)//为客户查询，先查询客户有多少商户
        {
            criteria_mdzsj.andKHBH_UEqualTo(sKHBH_U_head);
        } else if ("sh".equals(sQTDX_U_head) && sSHBH_U_head.length() > 0) {
            criteria_mdzsj.andSHBH_UEqualTo(sSHBH_U_head);
        } else {
            Tran.runERR(tranObj, "CXCW05");
            return false;
        }

        if (sSHBH_U.length() > 0)
            criteria_mdzsj.andSHBH_UEqualTo(sSHBH_U);
        if (sZDBH_U.length() > 0)
            criteria_mdzsj.andZDBH_UEqualTo(sZDBH_U);

        //以","拆分交易状态
        if (sJYZT_U.length() > 0) {
            List<String> listJYZT_U = new ArrayList<>();
            String[] sJYZT_Us = sJYZT_U.split(",");
            for (int i = 0; i < sJYZT_Us.length; i++) {
                listJYZT_U.add(sJYZT_Us[i]);
            }
            criteria_mdzsj.andJYZT_UIn(listJYZT_U);
        } else {
            Tran.runERR(tranObj, "COMMIS", "交易状态");
            return false;
        }


        if (sZFZHLX.length() > 0)
            criteria_mdzsj.andZFZHLXEqualTo(sZFZHLX);


        mdzsjExample.setOrderByClause(" HTRQ_U  desc,JYSJ_U  desc");
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
        }
        List<Map> listResoult = new ArrayList();

        for (MDZSJ mdzsj :
                mdzsjList) {
            Map detail = new HashMap<String, String>();
            detail.put("HTRQ_U", DataUtils.Date2StringofYear(mdzsj.getHTRQ_U()));
            detail.put("HTSJ_U", DataUtils.Date2StringofTime(mdzsj.getJYSJ_U()));
            detail.put("HTLS_U", mdzsj.getHTLS_U());
            detail.put("JYJE_U", mdzsj.getJYJE_U());
            detail.put("ZFZHLX", mdzsj.getZFZHLX());
            detail.put("SFDH_U", mdzsj.getSFDH_U());
            detail.put("KHBH_U", sKHBH_U_head);
            detail.put("SHBH_U", mdzsj.getSHBH_U());
            detail.put("ZDBH_U", mdzsj.getZDBH_U());
            detail.put("SPXX_U", mdzsj.getSPXX_U());
            detail.put("JYZT_U", mdzsj.getJYZT_U());
            detail.put("YTHJE_", mdzsj.getYTHJE_());
            detail.put("YHTRQ_",DataUtils.Date2StringofYear( mdzsj.getYHTRQ_()));
            detail.put("YHTLS_", mdzsj.getYHTLS_());

            listResoult.add(detail);
        }


        tranObj.BodyMap.put("RELIST", listResoult);


         /*==================================codeEnd=====================================*/

        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }
}
