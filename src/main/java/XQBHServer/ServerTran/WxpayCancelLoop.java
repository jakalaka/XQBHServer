package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJExample;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.log.Logger;

import java.text.ParseException;
import java.util.*;

import static XQBHServer.Utils.Data.DataUtils.Date2StringofYear;
import static XQBHServer.Utils.Data.DataUtils.splitDate;

/**
 * 扫描并取消交易状态，由其他轮循调用
 */
public class WxpayCancelLoop extends Tran {

    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);

        /*==================================codeBegin=====================================*/


        Date dateLimit = new Date();
        dateLimit.setTime(dateLimit.getTime() - Com.wxpayCancelTimeThreshold);//超时时间


        Date[]    dArrary = splitDate(tranObj,dateLimit);

        Logger.log(tranObj, "LOG_IO", " dArrary[0]=" + dArrary[0]);
        Logger.log(tranObj, "LOG_IO", " dArrary[1]=" + dArrary[1]);

        MDZSJExample mdzsjExample = new MDZSJExample();
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        mdzsjExample.clear();
        mdzsjExample.or().andZFZHLXEqualTo("w").andJYZT_UIn(Arrays.asList("w", "u")).andFRDM_UEqualTo("9999").andHTRQ_UEqualTo(dArrary[0]).andJYSJ_ULessThan(dArrary[1]);
        mdzsjExample.or().andZFZHLXEqualTo("w").andJYZT_UIn(Arrays.asList("w", "u")).andFRDM_UEqualTo("9999").andHTRQ_ULessThan(dArrary[0]);
        List<MDZSJ> mdzsjList = null;

        try {
            mdzsjList = mdzsjMapper.selectByExample(mdzsjExample);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }


        Logger.log(tranObj, "LOG_DEBUG", "总笔数 mdzsjList.size()=" + mdzsjList.size());
        for (int i = 0; i < mdzsjList.size(); i++) {

            Logger.log(tranObj, "LOG_DEBUG", "开始调用取消 index=" + i);
            try {

                Map XMLMapIn = new HashMap();
                CallResult callResult=new CallResult();
                Map head = new HashMap();
                Map body = new HashMap();
                head=tranObj.getHeadMap();
                head.put("HTJYM_", "WxpayCancel");
                body.put("YHTLS_",mdzsjList.get(i).getHTLS_U());
                body.put("YHTRQ_",Date2StringofYear(mdzsjList.get(i).getHTRQ_U()));
                XMLMapIn.put("head",head);
                XMLMapIn.put("body",body);
                if(SystemTran.call(tranObj,XMLMapIn,callResult)!=true)
                {
                    Logger.log(tranObj, "LOG_ERR", "call failed!");
                }

            } catch (Exception e) {
                Logger.logException(tranObj,"LOG_ERR",e);
            }
        }



        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);
        return true;
    }


}
