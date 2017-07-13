package ServerTran;

import Server.Logger;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class ZDLogin extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {

        String ZDJYM_=tranObj.getHead("ZDJYM_");
        String ZDBH_U=tranObj.getValue("ZDBH_U");
        Logger.log("DEBUG","ZDJYM_="+ZDJYM_);
        Logger.log("DEBUG","ZDBH_U="+ZDBH_U);
        tranObj.setValue("re","no");
        if (false) {
            runERR(tranObj, "0001");
            return false;
        }return true;
    }
}
