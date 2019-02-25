package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJExample;
import XQBHServer.Server.Table.Model.MDZSJKey;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Utils.AlipayHelper.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static XQBHServer.Utils.Data.DataUtils.Date2StringofYear;

/**
 * ��ѯ֧��������״̬
 * <p>
 * 1.�����̨��ˮ�����ڣ���Ϊ�����Ѿ��Ǽ�MDZSJ
 * �����磺ǰ��ͨѶ����ѵ��ѯ�������ʱ����ݽ������MDZSJ״̬
 * <p>
 * 2.������������,��Ϊ����δִ�еǼ����ݿ⣬��ʱ�����±������ݣ�ֻ�Ƿ�������״̬��
 * �����磺�������ֹ���ѯ��֧�����ײ���ȷʱ��������ѯ
 */
public class AlipayQuery extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.METHOD_IN);
        String sYHTLS_ = tranObj.getString("YHTLS_");
        String sYHTRQ_ = tranObj.getString("YHTRQ_");
        String sSFDH_U = tranObj.getString("SFDH_U");

        Logger.log(tranObj, "LOG_IO", "sYHTLS_=" + sYHTLS_);
        Logger.log(tranObj, "LOG_IO", "sYHTRQ_=" + sYHTRQ_);
        Logger.log(tranObj, "LOG_IO", "sSFDH_U" + sSFDH_U);
        /*==================================codeBegin=====================================*/
        AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppID, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        String sSFDH_UFINAL = "";

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


        } else {
            Logger.log(tranObj, "LOG_ERR", "�����������!");
            runERR(tranObj, "ERRPIN");
            return false;
        }


        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sSFDH_UFINAL + "\"," +
                "\"trade_no\":\"\"" +
                "}");

        AlipayTradeQueryResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent(), "z")) {
            runERR(tranObj, "ZF0005");
            return false;
        }


        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
        if (true != InsertMJYBWAfterDSF.exec(tranObj, response.getBody(), response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg()))//��ɽ��׸��±��ı�ֱ�Ӳ��룬��������
        {
            //���Ϊδ֪����
            Logger.log(tranObj, "LOG_ERR", "������������뱨��ʧ��");
        }
        if (!response.isSuccess()) {
            if ("ACQ.TRADE_NOT_EXIST".equals(response.getSubCode())) {
                runERR(tranObj, "ZF0007");
                return false;
            } else if ("ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                runERR(tranObj, "ZF0004");
                return false;
            }
            runERR(tranObj, "ZF0002");
            return false;
        }
        Logger.log(tranObj, "LOG_DEBUG", "���óɹ�");


        Logger.log(tranObj, "LOG_DEBUG", "response.getTradeStatus()=" + response.getTradeStatus());
        if (null != sYHTLS_ && !"".equals(sYHTLS_) && null != sYHTRQ_ && !"".equals(sYHTRQ_)) {//����ǰ̨���ø���mdzsj
            if ("TRADE_CLOSED".equals(response.getTradeStatus())) {
                if (!"c".equals(mdzsj.getJYZT_U())) {
                    mdzsj.setJYZT_U("c");
                    mdzsj.setSFLS_U(response.getTradeNo());
                    mdzsj.setSFRQ_U(response.getSendPayDate());
                    mdzsj.setFKRID_(response.getBuyerUserId());
                    mdzsj.setFKRZH_(response.getBuyerLogonId());
                    try {
                        mdzsjMapper.updateByPrimaryKey(mdzsj);
                    } catch (Exception e) {
                        Logger.logException(tranObj, "LOG_ERR", e);
                        runERR(tranObj, "SQLUPD");
                        return false;
                    }
                }
            } else if ("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())) {
                if ("w".equals(mdzsj.getJYZT_U())) {
                    mdzsj.setJYZT_U("1");
                    mdzsj.setSFLS_U(response.getTradeNo());
                    mdzsj.setSFRQ_U(response.getSendPayDate());
                    mdzsj.setFKRID_(response.getBuyerUserId());
                    mdzsj.setFKRZH_(response.getBuyerLogonId());
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


        tranObj.BodyMap.put("CODE_U", response.getCode());
        tranObj.BodyMap.put("SUBCOD", response.getSubCode());
        tranObj.BodyMap.put("TSTAT_", response.getTradeStatus());
        tranObj.BodyMap.put("SFLS_U", response.getTradeNo());
        tranObj.BodyMap.put("SFRQ_U", Date2StringofYear(response.getSendPayDate()));
        tranObj.BodyMap.put("FKRID_", response.getBuyerUserId());
        tranObj.BodyMap.put("FKRZH_", response.getBuyerLogonId());









        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.METHOD_OUT);

        return true;
    }

}
