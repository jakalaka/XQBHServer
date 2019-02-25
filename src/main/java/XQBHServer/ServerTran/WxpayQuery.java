package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.CallUtils.CallResult;
import XQBHServer.Utils.WxpayHelper.MyWxConfig;
import XQBHServer.Utils.log.Logger;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XQBHServer.Utils.Data.DataUtils.YearString2Date;

/**
 * 查询微信交易状态
 *
 * 1.传入后台流水和日期，认为交易已经登记MDZSJ
 * 场景如：前端通讯后轮训查询结果，此时需根据结果更新MDZSJ状态
 *
 * 2.传入三方单号,认为交易未执行登记数据库，此时不更新本地数据，只是返回三方状态
 * 与支付宝区别的是，通过三方订单号查询的必须提供卖家mch_id或者mdzsj中有相关登记以便查询到mch_id
 * 场景如：服务器手工查询，支付交易不明确时的立即查询
 */
public class WxpayQuery extends Tran {
    @Override
    public boolean exec(TranObj tranObj) throws ParseException {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sSFDH_U = tranObj.getString("SFDH_U");
        String sSHZH_U = tranObj.getString("SHZH_U");

        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head" + sSHBH_U_head);

        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sSFDH_U=" + sSFDH_U);
        Logger.log(tranObj, "LOG_IO", "sSHZH_U=" + sSHZH_U);
        /*==================================codeBegin=====================================*/
        String pay_mode = "w";
        String sSFDH_UFINAL = "";

        //======================检查参数合法性
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            Logger.log(tranObj, "LOG_DEBUG", "通过后台日期流水查询");

            MDZSJKey mdzsjKey = new MDZSJKey();
            mdzsjKey.setFRDM_U("9999");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = null;
            try {
                date = formatter.parse(sYHTRQ_);
            } catch (ParseException e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "TIMEER");
                return false;
            }

            mdzsjKey.setHTRQ_U(date);
            mdzsjKey.setHTLS_U(sYHTLS_);
            try {
                mdzsj = mdzsjMapper.selectByPrimaryKey(mdzsjKey);
            } catch (Exception e) {
                Logger.logException(tranObj, "LOG_ERR", e);
                Tran.runERR(tranObj, "SQLSEL");
                return false;
            }
            sSFDH_UFINAL = mdzsj.getSFDH_U();
        } else if (null != sSFDH_U && !"".equals(sSFDH_U)) {//通过三方订单号直接查询
            Logger.log(tranObj, "LOG_DEBUG", "通过三方订单查询");
            sSFDH_UFINAL = sSFDH_U;
            if("".equals(sSHZH_U)) {
                MDZSJExample example = new MDZSJExample();
                example.or().andSFDH_UEqualTo(sSFDH_U);
                List<MDZSJ> lists = mdzsjMapper.selectByExample(example);

                if (lists.size() == 0) {
                    Logger.log(tranObj, "LOG_ERR", "查询本地数据库错误!");
                    runERR(tranObj, "SQLNFD");
                    return false;
                }
                if (lists.size() > 1) {
                    Logger.log(tranObj, "LOG_ERR", "查询本地数据库错误!");
                    runERR(tranObj, "SQLMRE");
                    return false;
                }
                mdzsj = lists.get(0);
                sSHZH_U=mdzsj.getZFZH_U();
            }
        } else {
            Logger.log(tranObj, "LOG_ERR", "传入参数错误!");
            runERR(tranObj, "ERRPIN");
            return false;
        }





        /*
        组织查询
         */

        MyWxConfig config = null;
        WXPay wxpay = null;
        Map<String, String> response = new HashMap<String, String>();
        Map<String, String> require = new HashMap<String, String>();
        try {
            config = new MyWxConfig(sSHZH_U);
            wxpay = new WXPay(config, true, Com.wxpayUseSandbox);

            require.put("out_trade_no", sSFDH_UFINAL);


        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "ZF0004");
            return false;

        }



        if (true != InsertMJYBWBeforeDSF.exec(tranObj, JSON.toJSONString(require), pay_mode)) {
            runERR(tranObj, "ZF0005");
            return false;
        }


        try {
            response = wxpay.orderQuery(require);

        } catch (Exception e) {

            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0002");
            return false;

        }


        //完成交易更新报文表，直接插入，报错不返回
        if (true != InsertMJYBWAfterDSF.exec(tranObj, JSON.toJSONString(response), response.get("return_code"), response.get("return_msg"), response.get("err_code"), response.get("err_code_des"))) {
            //标记为未知交易
            Logger.log(tranObj, "LOG_ERR", "上完第三方插入报文失败");
        }
        if (!"SUCCESS".equals(response.get("return_code")))//网关调用失败直接认定为失败
        {
            runERR(tranObj, "ZF0002");
            return false;
        }
        Logger.log(tranObj, "LOG_DEBUG", response.get("return_code"));

        if (!"SUCCESS".equals(response.get("result_code"))) {
            if("ORDERNOTEXIST".equals(response.get("err_code")))
            {
                runERR(tranObj, "ZF0007");
                return false;
            }

            runERR(tranObj, "ZF0002");
            return false;
        }
        Logger.log(tranObj, "LOG_DEBUG", "调用成功");


        Logger.log(tranObj, "LOG_DEBUG", "response.get(\"trade_state\")=" + response.get("trade_state"));
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            if ("CLOSED".equals(response.get("trade_state"))) {
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//正常前台调用更新mdzsj

                    if (!"c".equals(mdzsj.getJYZT_U())) {
                        mdzsj.setJYZT_U("c");
                        mdzsj.setSFLS_U(response.get("transaction_id"));
                        mdzsj.setSFRQ_U(YearString2Date(tranObj, response.get("time_end").substring(0, 8)));
                        mdzsj.setFKRID_(response.get("openid"));

                        try {
                            mdzsjMapper.updateByPrimaryKey(mdzsj);
                        } catch (Exception e) {
                            Logger.logException(tranObj, "LOG_ERR", e);
                            runERR(tranObj, "SQLUPD");
                            return false;
                        }
                    }

                }
            } else if ("SUCCESS".equals(response.get("trade_state"))) {
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//正常前台调用更新mdzsj

                    if ("w".equals(mdzsj.getJYZT_U())) {
                        mdzsj.setJYZT_U("1");
                        mdzsj.setSFLS_U(response.get("transaction_id"));
                        mdzsj.setSFRQ_U(YearString2Date(tranObj, response.get("time_end").substring(0, 8)));
                        mdzsj.setFKRID_(response.get("openid"));
                        mdzsj.setFKRZH_("");
                        try {
                            mdzsjMapper.updateByPrimaryKey(mdzsj);
                        } catch (Exception e) {
                            Logger.logException(tranObj, "LOG_ERR", e);
                            runERR(tranObj, "SQLUPD");
                            return false;
                        }

                    }
                }

            }
        }


        tranObj.BodyMap.put("CODE_U",response.get("return_code"));
        tranObj.BodyMap.put("SUBCOD",response.get("err_code"));
        tranObj.BodyMap.put("TSTAT_",response.get("trade_state"));
        tranObj.BodyMap.put("SFLS_U",response.get("transaction_id"));
        tranObj.BodyMap.put("SFRQ_U",YearString2Date(tranObj, response.get("time_end").substring(0, 8)));
        tranObj.BodyMap.put("FKRID_",response.get("openid"));
        tranObj.BodyMap.put("FKRZH_","");









        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);

        return true;
    }

}
