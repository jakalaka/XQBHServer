package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.CXTCSMapper;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Mapper.MDZSJMapper;
import XQBHServer.Server.Table.Model.*;
import XQBHServer.ServerAPI.InsertMJYBWAfterDSF;
import XQBHServer.ServerAPI.InsertMJYBWBeforeDSF;
import XQBHServer.Test.MyAlipayClient;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
֧�����ܣ������ۿ��AAAAAA��10003����ZFWAIT��������Ϊ֧��ʧ��
20000ʱ�Զ�����1�β�ѯ���绹��δ֪������״̬Ϊu
�ǼǶ������ݱ�
����״̬JYZT_U
1-���
w-�ȴ��û�֧��
c-�ر�
x-����
t-�˻�
u-δ֪
�ͻ��˵��ã���ΪZFWAIT������Ҫ����ZFBQuery��ѯ�����¶�������״̬
 */
public class AlipayPay extends Tran {
    @Override
    public boolean exec(TranObj tranObj) {
        Logger.log(tranObj, "LOG_IO", Com.getIn);
        String sQTDX_U_head = tranObj.getHead("QTDX_U");
        String sKHBH_U_head = tranObj.getHead("KHBH_U");
        String sSHBH_U_head = tranObj.getHead("SHBH_U");
        String sZDBH_U_head = tranObj.getHead("ZDBH_U");
        String sQTRQ_U_head = tranObj.getHead("QTRQ_U");
        String sQRCODE = tranObj.getString("QRCODE");
        String sSPMC_U = tranObj.getString("SPMC_U");
        BigDecimal bJYJE_U = tranObj.getBigDecimal("JYJE_U");

        Logger.log(tranObj, "LOG_IO", "sQTDX_U_head=" + sQTDX_U_head);
        Logger.log(tranObj, "LOG_IO", "sKHBH_U_head=" + sKHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sSHBH_U_head=" + sSHBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sZDBH_U_head=" + sZDBH_U_head);
        Logger.log(tranObj, "LOG_IO", "sQTRQ_U_head=" + sQTRQ_U_head);

        Logger.log(tranObj, "LOG_IO", "sQRCODE=" + sQRCODE);
        Logger.log(tranObj, "LOG_IO", "sSPMC_U=" + sSPMC_U);
        Logger.log(tranObj, "LOG_IO", "bJYJE_U=" + bJYJE_U);
        /*==================================codeBegin=====================================*/
        //�ն˺�Ϊ����ȡ�̻��ţ�˵�������̻����ƶ����Ľ���
        if ("kh".equals(sQTDX_U_head))
        {
            sZDBH_U_head=sKHBH_U_head;
        }else if ("sh".equals(sQTDX_U_head))
        {
            Tran.runERR(tranObj, "PAY001");
            return false;
        }else if ("zd".equals(sQTDX_U_head))
        {

        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date dQTRQ_U = null;
        try {
            dQTRQ_U = formatter.parse(sQTRQ_U_head);
        } catch (ParseException e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "TIMEER");
            return false;
        }
        //============================���ϵͳ֧���Ϸ���============================
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
        if (null == cxtcs || !"1".equals(cxtcs.getVALUE_())) {//ϵͳ���δ������˹���ϵͳ��Ϊ����
            runERR(tranObj, "SYSPAY");
            return false;
        }

        //=================================������ˮ============================
        if (Com.getHTLS(tranObj) == false)
            return false;
        String sZFBLS_ = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");

        //==============================��ѯ�̻���Ϣ�Ϸ���=========================
        DSHXXMapper dshxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U_head);
        dshxxKey.setFRDM_U("9999");
        DSHXX dshxx = null;
        try {
            dshxx = dshxxMapper.selectByPrimaryKey(dshxxKey);
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

        //==============================��ѯ�̻��տ��˺�=======================
        DSHZHMapper dshzhMapper = tranObj.sqlSession.getMapper(DSHZHMapper.class);
        DSHZHKey dshzhKey = new DSHZHKey();
        dshzhKey.setFRDM_U("9999");
        dshzhKey.setSHBH_U(dshxx.getSHBH_U());
        dshzhKey.setZFZHLX("z");
        DSHZH dshzh = null;
        try {
            dshzh = dshzhMapper.selectByPrimaryKey(dshzhKey);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLSEL");
            return false;
        }
        if (null == dshzh) {
            runERR(tranObj, "ZF0001", "z");
            return false;
        }

        //=============================����������ݱ�===========================
        MDZSJ mdzsj = new MDZSJ();
        MDZSJMapper mdzsjMapper = tranObj.sqlSession.getMapper(MDZSJMapper.class);
        mdzsj.setFRDM_U("9999");
        mdzsj.setHTRQ_U(tranObj.date);
        mdzsj.setHTLS_U(tranObj.getHead("HTLS_U"));
        mdzsj.setJYSJ_U(tranObj.date);
        mdzsj.setHTJYM_(tranObj.getHead("HTJYM_"));
        mdzsj.setJYLX_U("0");
        mdzsj.setJYJE_U(bJYJE_U);
        mdzsj.setKHBH_U(sKHBH_U_head);
        mdzsj.setSHBH_U(sSHBH_U_head);
        mdzsj.setZDBH_U(sZDBH_U_head);

        mdzsj.setZFZHLX("z");
        mdzsj.setSFDH_U(sZFBLS_);
        mdzsj.setSFLS_U(null);//���׳ɹ������
        mdzsj.setSFRQ_U(null);//���׳ɹ������
        mdzsj.setFKRID_(null);//���׳ɹ������
        mdzsj.setFKRZH_(null);//���׳ɹ������
        mdzsj.setQTRQ_U(dQTRQ_U);
        mdzsj.setQTLS_U(tranObj.getHead("QTLS_U"));
        mdzsj.setQTJYM_(tranObj.getHead("QTJYM_"));
        mdzsj.setJYZT_U("u");
        mdzsj.setSPXX_U(sSPMC_U);
        mdzsj.setFKM_UU(sQRCODE);
        mdzsj.setJLZT_U("0");
        try {
            mdzsjMapper.insert(mdzsj);
        } catch (Exception e) {
            Logger.logException(tranObj, "LOG_ERR", e);
            Tran.runERR(tranObj, "SQLINS");
            return false;
        }


        //===============================��֯�տ�================================
        String goods_category = "";
        String show_url = "";
        String timeout_express = "";
        AlipayClient alipayClient;
        String seller_id = dshzh.getZFZH_U();

        alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.appPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + sZFBLS_ + "\"," +//�ҵ���ˮ
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
                "\"operator_id\":\"" + sZDBH_U_head + "\"," +
                "\"store_id\":\"" + sSHBH_U_head + "\"," +
                "\"terminal_id\":\"" + sZDBH_U_head + "\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"" + Com.alipaySys_service_provider_id + "\"" +
                "}," +
                "\"timeout_express\":\"" + timeout_express + "\"" +
                "}");

        AlipayTradePayResponse response = null;
        if (true != InsertMJYBWBeforeDSF.exec(tranObj, request.getBizContent(),"z")) {
            runERR(tranObj, "ZF0005");
            return false;
        }
        try {
            response = alipayClient.execute(request);
        } catch (Exception e) {
            //���Ϊδ֪����
            tranObj.commitFlg = true;
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
//        if (true != InsertMJYBWAfterDSF.exec(tranObj, response))//��ɽ��׸��±��ı�
//        {
//            //���Ϊδ֪����
//            tranObj.commitFlg = true;
//            runERR(tranObj, "ZF0006");
//            return false;
//        }
        if (true != InsertMJYBWAfterDSF.exec(tranObj, response))//��ɽ��׸��±��ı�ֱ�Ӳ��룬��������
        {
            //���Ϊδ֪����
            Logger.log(tranObj, "LOG_ERR", "������������뱨��ʧ��");
        }

        if (response.isSuccess()) {
            Logger.log(tranObj, "LOG_DEBUG", "���ü��˳ɹ�");
        } else {
            if (response.getSubCode().contains("INVALID_PARAMETER")) {//������������ֹͣ���н��׵ȴ�ȷ��

                tranObj.sqlSession.rollback();
                cxtcs.setVALUE_("0");
                try {
                    cxtcsMapper.updateByPrimaryKey(cxtcs);
                } catch (Exception e) {
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "SQLUPD");
                    return false;
                }
                tranObj.sqlSession.commit();
            } else if ("20000".equals(response.getCode()) || "isp.unknow-error".equals(response.getSubCode()) || "ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                //��ѯһ�Σ�ʧ����ֱ�ӷ���
                AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                queryRequest.setBizContent("{" +
                        "\"out_trade_no\":\"" + sZFBLS_ + "\"," +//�ҵ���ˮ
                        "\"trade_no\":\"\"" +
                        "}");
                AlipayTradeQueryResponse queryResponse = null;
                try {
                    queryResponse = alipayClient.execute(queryRequest);
                } catch (Exception e) {
                    //���Ϊδ֪����
                    tranObj.commitFlg = true;
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
                        tranObj.commitFlg = true;
                        Logger.log(tranObj, "LOG_ERR", "���ò�ѯʧ��");
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                }
                if ("TRADE_SUCCESS".equals(queryResponse.getTradeStatus())) {
                    Logger.log(tranObj, "LOG_IO", "����״̬δ֪��ͨ����ѯ��֪�ñʽ��׳ɹ�");
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
        if ("10003".equals(response.getCode())) {
            mdzsj.setJYZT_U("w");
            try {
                mdzsjMapper.updateByPrimaryKey(mdzsj);
            }catch (Exception e)
            {
                Logger.logException(tranObj,"LOG_ERR",e);
            }
            runERR(tranObj, "ZFWAIT");//ǰ����һ����ѭ
            tranObj.commitFlg=true;
            return false;
        } else if (!"10000".equals(response.getCode())) {
            runERR(tranObj, "ZF0003");
            return false;
        }


        mdzsj.setSFLS_U(response.getTradeNo());
        mdzsj.setSFRQ_U(response.getGmtPayment());
        mdzsj.setFKRID_(response.getBuyerUserId());
        mdzsj.setFKRZH_(response.getBuyerLogonId());

        mdzsj.setJYZT_U("1");


        try {
            mdzsjMapper.updateByPrimaryKey(mdzsj);
        } catch (Exception e) {
            //����������Ϊ�ɹ������˹��˶�״̬Ϊu�����
            Logger.logException(tranObj,"LOG_ERR",e);
        }




        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }

}
