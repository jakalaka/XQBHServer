package XQBHServer.ServerAPI;


import XQBHServer.ServerTran.Tran;
import XQBHServer.ServerTran.TranObj;
import XQBHServer.Utils.log.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ComInit {
    public static boolean exec(TranObj tranObj)  {
        //报文头格式化
        tranObj.setHead("HTLS_U","");
        tranObj.setHead("HTRQ_U","");
        tranObj.setHead("CWDM_U","");
        tranObj.setHead("CWXX_U","");

        tranObj.setHead("HTRQ_U",new SimpleDateFormat("yyyyMMdd").format(tranObj.date));

        /*
        插入交易报文
         */
            if(true != InsertMJYBWBeforeTran.exec(tranObj)) {
                Tran.runERR(tranObj,"ERR006");
                return false;
            }


        /*
        以下添加拆报合法性检查如终端信息，终端校验码等
         */

        try {
            if (true!= JCZDHFX.exec(tranObj)) {
                Tran.runERR(tranObj,"ERR002");
                return false;
            }
        } catch (IOException e) {
            Logger.log(tranObj,"LOG_ERR",e.toString());
            Tran.runERR(tranObj,"ERR002");
            return false;
        }

        /*
        获取日志等级
         */



        return true;

    }
}
