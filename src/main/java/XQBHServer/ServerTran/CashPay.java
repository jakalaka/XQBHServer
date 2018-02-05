package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
支付功能，正常扣款返回AAAAAA，10003返回ZFWAIT，其他则为支付失败
20000时自动调用1次查询，如还是未知，则交易状态为u
登记对账数据表
交易状态JYZT_U
1-完成
w-等待用户支付
c-关闭
x-撤销
t-退货
u-未知
客户端调用，如为ZFWAIT，则需要调用ZFBQuery查询并更新对账数据状态
 */
public class CashPay extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQTLS_U_head = tranObj.getHead("QTLS_U");

        String sSPMC_U = tranObj.getString("SPMC_U");
        BigDecimal bJYJE_U = tranObj.getBigDecimal("JYJE_U");


        Logger.log(tranObj, "LOG_IO", "sQTDX_U_head=" + sQTDX_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sQTRQ_U_head=" + sQTRQ_U_head);
        Logger.log(tranObj, "LOG_IO", "sQTLS_U_head=" + sQTLS_U_head);

        Logger.log(tranObj, "LOG_IO", "sSPMC_U=" + sSPMC_U);
        Logger.log(tranObj, "LOG_IO", "bJYJE_U=" + bJYJE_U);
        /*==================================codeBegin=====================================*/
        //终端号为空则取商户号，说明是在商户控制端做的交易
        if ("kh".equals(sQTDX_U_head))
        {
            sZDBH_U_head=sKHBH_U_head;
        }else if ("sh".equals(sQTDX_U_head))
        {
            Tran.runERR(tranObj, "PAY001");
            return false;
        }else if ("zd".equals(sQTDX_U_head))
        {

        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dQTRQ_U = null;
        try {
            dQTRQ_U = formatter.parse(sQTRQ_U_head);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }


        //=================================生成流水============================
        if (Com.getHTLS(tranObj) == false)
            return false;
        String sZFBLS_ = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");


        //=============================插入对账数据表===========================
        MDZSJ mdzsj = new MDZSJ();
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        mdzsj.setFRDM_U("9999");
        mdzsj.setHTRQ_U(tranObj.date);
        mdzsj.setHTLS_U(tranObj.getHead("HTLS_U"));
        mdzsj.setJYSJ_U(tranObj.date);
        mdzsj.setHTJYM_(tranObj.getHead("HTJYM_"));
        mdzsj.setJYLX_U("0");
        mdzsj.setJYJE_U(bJYJE_U);
        mdzsj.setKHBH_U(sKHBH_U_head);
        mdzsj.setSHBH_U(sSHBH_U_head);
        mdzsj.setZDBH_U(sZDBH_U_head);
        mdzsj.setZFZHLX("c");
        mdzsj.setSFDH_U(sZFBLS_);
        mdzsj.setQTRQ_U(dQTRQ_U);
        mdzsj.setQTLS_U(sQTLS_U_head);
        mdzsj.setQTJYM_(tranObj.getHead("QTJYM_"));
        mdzsj.setJYZT_U("1");
        mdzsj.setSPXX_U(sSPMC_U);
        mdzsj.setJLZT_U("0");

        mdzsj.setJYZT_U("1");


        try {
            mdzsjMapper.insert(mdzsj);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

}
