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
 * ��ѯ΢�Ž���״̬
 *
 * 1.�����̨��ˮ�����ڣ���Ϊ�����Ѿ��Ǽ�MDZSJ
 * �����磺ǰ��ͨѶ����ѵ��ѯ�������ʱ����ݽ������MDZSJ״̬
 *
 * 2.������������,��Ϊ����δִ�еǼ����ݿ⣬��ʱ�����±������ݣ�ֻ�Ƿ�������״̬
 * ��֧����������ǣ�ͨ�����������Ų�ѯ�ı����ṩ����mch_id����mdzsj������صǼ��Ա��ѯ��mch_id
 * �����磺�������ֹ���ѯ��֧�����ײ���ȷʱ��������ѯ
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

        //======================�������Ϸ���
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        MDZSJ mdzsj = null;
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            Logger.log(tranObj, "LOG_DEBUG", "ͨ����̨������ˮ��ѯ");

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
        } else if (null != sSFDH_U && !"".equals(sSFDH_U)) {//ͨ������������ֱ�Ӳ�ѯ
            Logger.log(tranObj, "LOG_DEBUG", "ͨ������������ѯ");
            sSFDH_UFINAL = sSFDH_U;
            if("".equals(sSHZH_U)) {
                MDZSJExample example = new MDZSJExample();
                example.or().andSFDH_UEqualTo(sSFDH_U);
                List<MDZSJ> lists = mdzsjMapper.selectByExample(example);

                if (lists.size() == 0) {
                    Logger.log(tranObj, "LOG_ERR", "��ѯ�������ݿ����!");
                    runERR(tranObj, "SQLNFD");
                    return false;
                }
                if (lists.size() > 1) {
                    Logger.log(tranObj, "LOG_ERR", "��ѯ�������ݿ����!");
                    runERR(tranObj, "SQLMRE");
                    return false;
                }
                mdzsj = lists.get(0);
                sSHZH_U=mdzsj.getZFZH_U();
            }
        } else {
            Logger.log(tranObj, "LOG_ERR", "�����������!");
            runERR(tranObj, "ERRPIN");
            return false;
        }





        /*
        ��֯��ѯ
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


        //��ɽ��׸��±��ı�ֱ�Ӳ��룬��������
        if (true != InsertMJYBWAfterDSF.exec(tranObj, JSON.toJSONString(response), response.get("return_code"), response.get("return_msg"), response.get("err_code"), response.get("err_code_des"))) {
            //���Ϊδ֪����
            Logger.log(tranObj, "LOG_ERR", "������������뱨��ʧ��");
        }
        if (!"SUCCESS".equals(response.get("return_code")))//���ص���ʧ��ֱ���϶�Ϊʧ��
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
        Logger.log(tranObj, "LOG_DEBUG", "���óɹ�");


        Logger.log(tranObj, "LOG_DEBUG", "response.get(\"trade_state\")=" + response.get("trade_state"));
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {
            if ("CLOSED".equals(response.get("trade_state"))) {
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//����ǰ̨���ø���mdzsj

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
                if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//����ǰ̨���ø���mdzsj

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
