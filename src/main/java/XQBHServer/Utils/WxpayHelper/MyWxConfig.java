package XQBHServer.Utils.WxpayHelper;

import XQBHServer.Server.Com;
import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import java.io.*;

public class MyWxConfig extends WXPayConfig {

    private byte[] certData;
    private String MchID;

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    public MyWxConfig(String MchID_) throws Exception {
        this.MchID=MchID_;

        System.out.println(Com.wxpayCertPath);
        InputStream certStream = Class.class.getResourceAsStream(Com.wxpayCertPath);



        this.certData = readInputStream(certStream);
        certStream.close();
    }

    public String getAppID() {
        return Com.wxpayAppID;
    }

    public String getMchID() {
        return this.MchID;
    }

    public String getKey() {
        return Com.wxpayKey;
    }

    @Override
    public void setKey(String key) {
        Com.wxpayKey=key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return Com.wxpayConnectTimeout;
    }

    public int getHttpReadTimeoutMs() {
        return Com.wxpayReadTimeout;
    }
//
//    @Override
//    public IWXPayDomain getWXPayDomain() {
//        return null;
//    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }
}