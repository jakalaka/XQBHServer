package XQBHServer.ServerTran;

import XQBHServer.Server.Com;
import XQBHServer.Server.Table.Mapper.DSHXXMapper;
import XQBHServer.Server.Table.Mapper.DSHZHMapper;
import XQBHServer.Server.Table.Model.DSHXX;
import XQBHServer.Server.Table.Model.DSHXXKey;
import XQBHServer.Server.Table.Model.DSHZH;
import XQBHServer.Server.Table.Model.DSHZHKey;
import XQBHServer.Utils.log.Logger;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;

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
        if (Com.getHTLS(tranObj) == false)
            return false;

        DSHXXMapper dzdxxMapper = tranObj.sqlSession.getMapper(DSHXXMapper.class);
        DSHXXKey dshxxKey = new DSHXXKey();
        dshxxKey.setSHBH_U(sSHBH_U);
        dshxxKey.setFRDM_U("9999");
        DSHXX dshxx = dzdxxMapper.selectByPrimaryKey(dshxxKey);
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
        DSHZH dshzh = dshzhMapper.selectByPrimaryKey(dshzhKey);
        if (null == dshzh) {
            runERR(tranObj, "ZF0001", sZFZHLX);
            return false;
        }

        String goods_category = "";
        String show_url = "";
        String timeout_express = "";
        String ZFBLS_ = tranObj.getHead("HTRQ_U") + tranObj.getHead("HTLS_U");

        AlipayClient alipayClient = new DefaultAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.alipayPrivateKey, "json", "utf-8", Com.alipayPulicKey, "RSA2");
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + ZFBLS_ + "\"," +//我的流水
                "\"scene\":\"bar_code\"," +
                "\"auth_code\":\"" + sQRCODE + "\"," + //付款码
                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
                "\"subject\":\"" + dshxx.getSHMC_U() + "\"," +
                "\"buyer_id\":\"" + sQRCODE + "\"," + //付款码
                "\"seller_id\":\"" + dshzh.getZFZH_U() + "\"," +
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
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {

            Logger.log(tranObj, "LOG_ERR", e.toString());
        }

        if (response.isSuccess()) {
            Logger.log(tranObj, "LOG_DEBUG", "调用成功");
        } else {
            Logger.log(tranObj, "LOG_ERR", "调用失败");
            runERR(tranObj, "ZF0002");
            return false;
        }
        Logger.log(tranObj, "LOG_DEBUG", "response.getCode()=" + response.getCode());
        if (!"10000".equals(response.getCode())) {
            return false;
        }
        System.out.println("response.getCode()=" + response.getBody());



        /*==================================codeEnd=====================================*/
        Logger.log(tranObj, "LOG_IO", Com.getOut);
        return true;
    }
}
