package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.ServerInit;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.Server.Table.basic.DBAccess;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.XML.XmlUtils;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 扫描并取消交易状态，由其他轮循调用
 */
public class AlipayCancelLoop extends Tran {

    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);

        /*==================================codeBegin=====================================*/


        CXTCSMapper cxtcsMapper = tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("AlipayTOut");
        CXTCS cxtcs = null;
        try {

            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Logger.log(tranObj, "LOG_ERR", "获取超时时间失败 ERR");
            return false;
        }

        long sTimeOut = 0;
        try {
            sTimeOut = Long.parseLong(cxtcs.getVALUE_());
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Logger.log(tranObj, "LOG_ERR", "转换string2long出错 ERR");
            return false;
        }

        Date dateLimit = new Date();
        dateLimit.setTime(dateLimit.getTime() - sTimeOut);//CXTCS中定义的支付宝超时时间
        Date[] dArrary;
        try {
            dArrary = Com.getRQSJ(dateLimit);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Logger.log(tranObj, "LOG_ERR", "获取当前日期、时间出错 ERR");
            return false;
        }

        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJExample mdzsjExample = new MDZSJExample();
        mdzsjExample.or().andFRDM_UEqualTo("9999");
        mdzsjExample.or().andJYZT_UEqualTo("w");
        mdzsjExample.clear();
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_UEqualTo(dArrary[0]).andJYSJ_ULessThan(dArrary[1]);
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_ULessThan(dArrary[0]);
        int iCount = 0;
        try {
            iCount = mdzsjMapper.countByExample(mdzsjExample);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Logger.log(tranObj, "LOG_ERR", "查询总笔数异常 ERR");
            return false;

        }


        Logger.log(tranObj, "LOG_IO", " dArrary[0]=" + dArrary[0]);
        Logger.log(tranObj, "LOG_IO", " dArrary[1]=" + dArrary[1]);

        if (iCount > 0) {
            Logger.log(tranObj, "LOG_DEBUG", "开始调用 iCount=" + iCount);
            String sZDBH_U = Com.getSYSZDBH_U();
            if (sZDBH_U == null || "".equals(sZDBH_U)) {
                Logger.log(tranObj, "LOG_ERR", "获取临时系统终端信息失败!!!");
                return false;

            } else {
                Logger.log(tranObj, "LOG_ERR", "获取临时系统终端编号=" + sZDBH_U);
            }
            try {
                Map XMLMapIn = new HashMap();
                Map head = new HashMap();
                head.put("ZDBH_U", sZDBH_U);
                head.put("ZDJYM_", "SERVER");
                head.put("HTJYM_", "AlipayCancel");
                head.put("QTRQ_U", Com.getDate());
                head.put("QTLS_U", Com.getSYSQTLS(sZDBH_U));
                XMLMapIn.put("head", head);
                String XMLIn = XmlUtils.map2XML(XMLMapIn);
                SystemTran systemTran = new SystemTran();
                String XMLOut = systemTran.SystemTran(XMLIn);
                Map XMLMapOut = XmlUtils.XML2map(XMLOut);
                Logger.log(tranObj, "LOG_IO", "CALL " + head.get("HTJYM_"));
                if (!"AAAAAA".equals(((Map) XMLMapOut.get("head")).get("CWDM_U")))
                    Logger.log(tranObj, "LOG_ERR", " FAIL!");
                else
                    Logger.log(tranObj, "LOG_IO", " SUCCESS!");
            } finally {
                if (!Com.releaseSYSZHBH_U(sZDBH_U)) {
                    Logger.log(tranObj, "LOG_ERR", "释放柜员时出错!!!");
                }
            }
        } else
            Logger.log(tranObj, "LOG_DEBUG", "iCount=" + iCount);



        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }


}
