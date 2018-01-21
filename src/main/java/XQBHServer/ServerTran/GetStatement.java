package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Utils.log.Logger;

public class GetStatement extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {

        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sSFDH_U = tranObj.getString("SFDH_U");


        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sSFDH_U" + sSFDH_U);
        /*==================================codeBegin=====================================*/





       /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);

        return true;
    }
}
