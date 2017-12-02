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
        //����ͷ��ʽ��
        tranObj.setHead("HTLS_U","");
        tranObj.setHead("HTRQ_U","");
        tranObj.setHead("CWDM_U","");
        tranObj.setHead("CWXX_U","");

        tranObj.setHead("HTRQ_U",new SimpleDateFormat("yyyyMMdd").format(tranObj.date));

        /*
        ���뽻�ױ���
         */
            if(true != InsertMJYBWBeforeTran.exec(tranObj)) {
                Tran.runERR(tranObj,"ERR006");
                return false;
            }


        /*
        ������Ӳ𱨺Ϸ��Լ�����ն���Ϣ���ն�У�����
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
        ��ȡ��־�ȼ�
         */



        return true;

    }
}
