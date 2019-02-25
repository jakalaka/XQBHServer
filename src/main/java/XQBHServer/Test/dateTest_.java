package XQBHServer.Test;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.Server.Table.Model.MDZSJExample;
import XQBHServer.Server.Table.basic.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static XQBHServer.Utils.Data.DataUtils.splitDate;

public class dateTest_ {
    public static void main(String[] args)  {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CXTCSMapper cxtcsMapper = sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("AlipayTOut");
        CXTCS cxtcs = null;
        cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);

        long sTimeOut = 0;

        sTimeOut = Long.parseLong(cxtcs.getVALUE_());

        Date dateLimit = new Date();
        dateLimit.setTime(dateLimit.getTime() - sTimeOut);//CXTCS中定义的支付宝超时时间
        Date[] dArrary=null;
        try {
            dArrary = splitDate(null,dateLimit);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        MDZSJMapper mdzsjMapper = sqlSession.getMapper(MDZSJMapper.class);
        MDZSJExample mdzsjExample = new MDZSJExample();
        mdzsjExample.or().andFRDM_UEqualTo("9999");
        mdzsjExample.or().andJYZT_UEqualTo("w");
        mdzsjExample.clear();
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_UEqualTo(dArrary[0]).andJYSJ_ULessThan(dArrary[1]);
        mdzsjExample.or().andZFZHLXEqualTo("z").andJYZT_UEqualTo("w").andFRDM_UEqualTo("9999").andHTRQ_ULessThan(dArrary[0]);
        long iCount = 0;

        iCount = mdzsjMapper.countByExample(mdzsjExample);
        System.out.println(iCount);

    }
}
