package XQBHServer.ServerAPI;


import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Model.CXTCS;
import XQBHServer.Server.Table.Model.CXTCSKey;
import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ComInit {
    public static boolean exec(TranObj tranObj) {
        //报文头格式化
        tranObj.setHead("HTLS_U", "");
        tranObj.setHead("HTRQ_U", "");
        tranObj.setHead("CWDM_U", "");
        tranObj.setHead("CWXX_U", "");

        tranObj.setHead("HTRQ_U", new SimpleDateFormat("yyyyMMdd").format(tranObj.date));

        /*
        插入交易报文
         */
        if (true != InsertMJYBWBeforeTran.exec(tranObj)) {
            Tran.runERR(tranObj, "ERR006");
            return false;
        }


        /*
        以下添加拆报合法性检查如终端信息，终端校验码等
         */

        try {
            if (true != JCZDHFX.exec(tranObj)) {
                return false;
            }
        } catch (IOException e) {
            Logger.log(tranObj, "LOG_ERR", e.toString());
            Tran.runERR(tranObj, "ERR002");
            return false;
        }


        /*
        如果服务器已关闭，返回失败，避免强制关闭导致的事物不一致
         */
        CXTCSKey cxtcsKey=new CXTCSKey();
        cxtcsKey.setFRDM_U("9999");
        cxtcsKey.setKEY_UU("SHUTDOWN");
        CXTCSMapper cxtcsMapper=tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCS cxtcs=cxtcsMapper.selectByPrimaryKey(cxtcsKey);

        if ("1".equals(cxtcs.getVALUE_()))
        {
            Logger.log(tranObj,"LOG_ERR", "系统即将关闭");
            Tran.runERR(tranObj, "SYSSHT");
            return false;
        }

        return true;

    }
}
