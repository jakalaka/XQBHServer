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
        if ("1".equals(tranObj.getHead("CSBZ_U")))//测试 数据写死
        {
            alipayClient = new DefaultAlipayClient(Com.alipayGateway_cs, Com.alipayAppid_cs, Com.alipayPrivateKey_cs, "json", "GBK", Com.alipayPulicKey_cs, "RSA2");
            seller_id = "2088102170074235";
            Logger.log(tranObj, "LOG_DEBUG", "本交易为测试交易TTTTTT");
        } else {
            alipayClient = new DefaultAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");
        }
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + ZFBLS_ + "\"," +//我的流水
                "\"scene\":\"bar_code\"," +
                "\"auth_code\":\"" + sQRCODE + "\"," + //付款码
                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
                "\"subject\":\"" + dshxx.getSHMC_U() + "\"," +
                "\"buyer_id\":\"" + sQRCODE + "\"," + //付款码
                "\"seller_id\":\"" + seller_id + "\"," +
                "\"total_amount\":" + bJYJE_U + "," +
                "\"discountable_amount\":0," +
                "\"body\":\"" + "订单描述" + "\"," +
                "\"goods_detail\":[{" +
                "\"goods_id\":\"" + sSPMC_U + "\"," +
                "\"goods_name\":\"" + sSPMC_U + "\"," +
                "\"quantity\":" + 1 + "," +
                "\"price\":" + bJYJE_U + "," +
                "\"goods_category\":\"" + goods_category + "\"," +
                "\"body\":\"" + "商品描述" + "\"," +
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
            //标记为未知交易
            tranObj.unknownFlg = true;
            Logger.logException(tranObj, "LOG_ERR", e);
            runERR(tranObj, "ZF0004");
            return false;
        }
        if (true != UpdateMJYBWAfterDSF.exec(tranObj, response))//完成交易更新报文表
        {
            //标记为未知交易
            tranObj.unknownFlg = true;
            runERR(tranObj, "ZF0006");
            return false;
        }


        if (response.isSuccess()) {
            Logger.log(tranObj, "LOG_DEBUG", "调用记账成功");
        } else {
            if (response.getSubCode().contains("INVALID_PARAMETER")) {//参数错误，立即停止所有交易等待确认

                tranObj.sqlSession.rollback();
                cxtcs.setVALUE_("0");
                cxtcsMapper.updateByPrimaryKey(cxtcs);
                tranObj.sqlSession.commit();
            } else if ("20000".equals(response.getCode()) || "isp.unknow-error".equals(response.getSubCode()) || "ACQ.SYSTEM_ERROR".equals(response.getSubCode())) {
                //查询一次，失败则直接返回
                AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                queryRequest.setBizContent("{" +
                        "\"out_trade_no\":\"" + ZFBLS_ + "\"," +//我的流水
                        "\"trade_no\":\"\"" +
                        "}");
                AlipayTradeQueryResponse queryResponse = null;
                try {
                    queryResponse = alipayClient.execute(queryRequest);
                } catch (Exception e) {
                    //标记为未知交易
                    tranObj.unknownFlg = true;
                    Logger.logException(tranObj, "LOG_ERR", e);
                    runERR(tranObj, "ZF0004");
                    return false;
                }
                if (queryResponse.isSuccess()) {
                    Logger.log(tranObj, "LOG_DEBUG", "调用查询成功");
                } else {
                    Logger.log(tranObj, "LOG_ERR", request.getBizContent());
                    Logger.log(tranObj, "LOG_ERR", response.getBody());
                    if (queryResponse.getSubCode().contains("TRADE_NOT_EXIST")) {
                        Logger.log(tranObj, "LOG_ERR", "调用查询无记账信息");
                        runERR(tranObj, "ZF0002");
                        return false;
                    } else {
                        //标记为未知交易
                        tranObj.unknownFlg = true;
                        Logger.log(tranObj, "LOG_ERR", "调用查询失败");
                        runERR(tranObj, "ZF0002");
                        return false;
                    }
                }
                if ("TRADE_SUCCESS".equals(queryResponse.getTradeStatus())) {
                    Logger.log(tranObj, "LOG_IO", "记账状态未知，通过查询得知该笔交易成功");
                    Logger.log(tranObj, "LOG_IO", Com.getOut);
                    return true;
                } else {
                    runERR(tranObj, "ZF0002");
                    return false;
                }
            } else {
                Logger.log(tranObj, "LOG_ERR", "调用记账失败");
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
