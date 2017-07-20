package ServerTran;

import Server.Logger;
import Server.Table.Mapper.DZDXXMapper;
import Server.Table.Model.DZDXX;
import Server.Table.Model.DZDXXKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class ZDLogin extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        /*
        签到从报文头中找信息
         */
        String ZDJYM_ = tranObj.getHead("ZDJYM_");
        String ZDBH_U = tranObj.getHead("ZDBH_U");
        String IP_UUU = tranObj.getHead("IP_UUU");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd$HHmmss");//设置日期格式

        Logger.log("DEBUG", "ZDJYM_=" + ZDJYM_);
        Logger.log("DEBUG", "ZDBH_U=" + ZDBH_U);

        DZDXXMapper dzdxxMapper = sqlSession.getMapper(DZDXXMapper.class);
        DZDXXKey dzdxxKey = new DZDXXKey();
        dzdxxKey.setZDBH_U(ZDBH_U);
        dzdxxKey.setFRDM_U("9999");
        DZDXX dzdxx = dzdxxMapper.selectByPrimaryKey(dzdxxKey);
//        if()
//        {
//            runERR(tranObj,"LOG001");
//            return false;
//        }else
        if ("0".equals(dzdxx.getZDDLZT()) || "1".equals(dzdxx.getZDDLZT())) {
            dzdxx.setZDDLZT("1");
            dzdxx.setIP_UUU(IP_UUU);
            Date date=new Date();
            dzdxx.setSCDLRQ(date);
            dzdxx.setSCDLSJ(date);

            dzdxxMapper.updateByPrimaryKey(dzdxx);
        } else {
            runERR(tranObj, "LOG002");
            return false;
        }


        return true;
    }

//    public static void main(String[] args) {
//        Map<String, String> test = new HashMap();
//        String a = test.get("aa").toString();
//    }
}
