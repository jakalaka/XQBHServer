package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.UpdateMJYBWAfterDSF;
import XQBHServer.ServerAPI.UpdateMJYBWBeforeDSF;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;

public class PayBill extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sZDJYM_ = tranObj.getHead("ZDJYM_");
        String sSHBH_U = tranObj.getHead("SHBH_U");
        String sZDBH_U = tranObj.getHead("ZDBH_U");
        String sZFZHLX = tranObj.getString("ZFZHLX");
        String sQRCODE = tranObj.getString("QRCODE");
        String sSPMC_U = tranObj.getString("SPMC_U");
        BigDecimal bJYJE_U = tranObj.getBigDecimal("JYJE_U");

        Logger.log(tranObj, "LOG_IO", "sZDJYM_=" + sZDJYM_);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U=" + sSHBH_U);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U=" + sZDBH_U);
        Logger.log(tranObj, "LOG_IO", "sZFZHLX=" + sZFZHLX);
        Logger.log(tranObj, "LOG_IO", "sQRCODE=" + sQRCODE);
        Logger.log(tranObj, "LOG_IO", "sSPMC_U=" + sSPMC_U);
        Logger.log(tranObj, "LOG_IO", "bJYJE_U=" + bJYJE_U);
        /*==================================codeBegin=====================================*/
        CXTCSMapper cxtcsMapper = tranObj.sqlSession.getMapper(CXTCSMapper.class);
        CXTCSKey cxtcsKey = new CXTCSKey();
        cxtcsKey.setKEY_UU("ALLOWPAY");
        cxtcsKey.setFRDM_U("9999");
        CXTCS cxtcs = null;
        try {
            cxtcs = cxtcsMapper.selectByPrimaryKey(cxtcsKey);

        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == cxtcs || !"1".equals(cxtcs.getVALUE_())) {
            runERR(tranObj, "SYSPAY");
            return false;
        }


        if (Com.getHTLS(tranObj) == false)
            return false;

        DSHXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U);
        dshxxKey.setFRDM_U("9999");
        DSHXX dshxx = null;
        try {
            dshxx = dzdxxMapper.selectByPrimaryKey(dshxxKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshxx) {
            runERR(tranObj, "SH0001");
            return false;
        }
        if (dshxx.getDQYE_U().compareTo(BigDecimal.ZERO) < 0) {
            runERR(tranObj, "SH0002");
            return false;
        }
        if (!"0".equals(dshxx.getJLZT_U())) {
            runERR(tranObj, "SH0003");
            return false;
        }


        DSHZHMapper dshzhMapper = tranObj.sqlSession.getMapper(DSHZHMapper.class);
        DSHZHKey dshzhKey = new DSHZHKey();
        dshzhKey.setFRDM_U("9999");
        dshzhKey.setSHBH_U(dshxx.getSHBH_U());
        dshzhKey.setZFZHLX(sZFZHLX);
        DSHZH dshzh = null;
        try {
            dshzh = dshzhMapper.selectByPrimaryKey(dshzhKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshzh) {
            runERR(tranObj, "ZF0001", sZFZHLX);
            return false;
        }

        String goods_category = "";
        String show_url = "";
        String timeout_express = "";
        String ZFBLS_ = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");
        AlipayClient alipayClient;
        String seller_id = dshzh.getZFZH_U();
        if ("1".equals(tranObj.getHead("CSBZ_U")))//���� ����д��
        {
            alipayClient = new DefaultAlipayClient(Com.alipayGateway_cs, Com.alipayAppid_cs, Com.alipayPrivateKey_cs, "json", "GBK", Com.alipayPulicKey_cs, "RSA2");
            seller_id = "2088102170074235";
            Logger.log(tranObj, "LOG_DEBUG", "������Ϊ���Խ���TTTTTT");
        } else {
            alipayClient = new DefaultAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");
        }
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + ZFBLS_ + "\"," +//�ҵ���ˮ
                "\"scene\":\"bar_code\"," +
                "\"auth_code\":\"" + sQRCODE + "\"," + //������
                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
                "\"subject\":\"" + dshxx.getSHMC_U() + "\"," +
                "\"buyer_id\":\"" + sQRCODE + "\"," + //������
                "\"seller_id\":\"" + seller_id + "\"," +
                "\"total_amount\":" + bJYJE_U + "," +
                "\"discountable_amount\":0," +
                "\"body\":\"" + "��������" + "\"," +
                "\"goods_detail\":[{" +
                "\"goods_id\":\"" + sSPMC_U + "\"," +
                "\"goods_name\":\"" + sSPMC_U + "\"," +
                "\"quantity\":" + 1 + "," +
                "\"price\":" + bJYJE_U + "," +
                "\"goods_category\":\"" + goods_category + "\"," +
                "\"body\":\"" + "��Ʒ����" + "\"," +
                "\"show_url\":\"" + show_url + "\"" +
                "}]," +
                "\"operator_id\":\"" + sZDBH_U + "\"," +
                "\"store_id\":\"" + sSHBH_U + "\"," +
                "\"terminal_id\":\"" + sZDBH_U + "\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"" + Com.alipaySys_service_provider_id + "\"" +
                "}," +
                "\"timeout_express\":\"" + timeout_express + "\"" +
                "}");

        AlipayTradePayResponse response = null;
        if (true != UpdateMJYBWBeforeDSF.exec(tranObj, request)) {
            runERR(tranObj, "ZF0005");
            return false;
        }
        try {
            response = alipayClient.execute(request);
        } catch (Exception e) {
            //���Ϊδ֪����
            tranObj.unknownFlg = true;
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
        if (true != UpdateMJYBWAfterDSF.exec(tranObj, response))//��ɽ��׸��±��ı�
        {
            //���Ϊδ֪����
            tranObj.unknownFlg = true;
            runERR(tranObj, "ZF0006");
            return false;
        }


        if (response.isSuccess()) {
            Logger.log(tranObj, "LOG_DEBUG", "���ü��˳ɹ�");
        } else {
            if (response.getSubCode().contains("INVALID_PARAMETER")) {//������������ֹͣ���н��׵ȴ�ȷ��

                tranObj.sqlSession.rollback();
                cxtcs.setVALUE_("0");
                cxtcsMapper.updateByPrimaryKey(cxtcs);
                tranObj.sqlSession.commit();
            } else if ("20000".equals(response.getCode()) || "isp.unknow-error".equals(response.getSubCode()) || "ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                //��ѯһ�Σ�ʧ����ֱ�ӷ���
                AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                queryRequest.setBizContent("{" +
                        "\"out_trade_no\":\"" + ZFBLS_ + "\"," +//�ҵ���ˮ
                        "\"trade_no\":\"\"" +
                        "}");
                AlipayTradeQueryResponse queryResponse = null;
                try {
                    queryResponse = alipayClient.execute(queryRequest);
                } catch (Exception e) {
                    //���Ϊδ֪����
                    tranObj.unknownFlg = true;
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "ZF0004");
                    return false;
                }
                if (queryResponse.isSuccess()) {
                    Logger.log(tranObj, "LOG_DEBUG", "���ò�ѯ�ɹ�");
                } else {
                    Logger.log(tranObj, "LOG_ERR", request.getBizContent());
                    Logger.log(tranObj, "LOG_ERR", response.getBody());
                    if (queryResponse.getSubCode().contains("TRADE_NOT_EXIST")) {
                        Logger.log(tranObj, "LOG_ERR", "���ò�ѯ�޼�����Ϣ");
                        runERR(tranObj, "ZF0002");
                        return false;
                    } else {
                        //���Ϊδ֪����
                        tranObj.unknownFlg = true;
                        Logger.log(tranObj, "LOG_ERR", "���ò�ѯʧ��");
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                }
                if ("TRADE_SUCCESS".equals(queryResponse.getTradeStatus())) {
                    Logger.log(tranObj, "LOG_IO", "����״̬δ֪��ͨ����ѯ��֪�ñʽ��׳ɹ�");
                    Logger.log(tranObj, "LOG_IO", Com.getOut);
                    return true;
                } else {
                    runERR(tranObj, "ZF0002");
                    return false;
                }
            } else {
                Logger.log(tranObj, "LOG_ERR", "���ü���ʧ��");
                runERR(tranObj, "ZF0002");
                return false;
            }

        }
        Logger.log(tranObj, "LOG_DEBUG", "response.getCode()=" + response.getCode());
        if (!"10000".equals(response.getCode())) {
            runERR(tranObj, "ZF0003");
            return false;
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

}
