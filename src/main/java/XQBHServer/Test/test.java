package XQBHServer.Test;

import XQBHServer.Server.Com;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;

public class test {
    public static void main(String[] args) {
        AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();
        AlipayClient alipayClient = new MyAlipayClient(Com.alipayGateway, Com.alipayAppid, Com.appPrivateKey, "json", "GBK", Com.alipayPulicKey, "RSA2");


        cancelRequest.setBizContent("{" +
                "\"out_trade_no\":\""+"20171209SZD0000010000011"+"\"," +
                "\"trade_no\":\"\"" +
                "  }");
        AlipayTradeCancelResponse calcelResponse = null;

        try {
            calcelResponse = alipayClient.execute(cancelRequest);
        } catch (Exception e) {
            //���Ϊδ֪����
            System.out.println(e);
        }
        if (calcelResponse.isSuccess()) {
            System.out.println("���óɹ�");

        }else {
            System.out.println("����ʧ��");
        }
        System.out.println(        calcelResponse.getBody());

    }
}
