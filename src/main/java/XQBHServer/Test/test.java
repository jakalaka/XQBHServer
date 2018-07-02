package XQBHServer.Test;

import XQBHServer.Server.Com;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        Map map=new HashMap<String ,String>();
//
//        map.put("Log\\2018\\05\\28\\System_0.log","");
//
//        map.put("Log\\2018\\05\\28\\ZDLogin/ZDLogin_0.log",null);
//        System.out.println(map.get("Log\\2018\\05\\28\\ZDLogin/ZDLogin_0.log"));

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("in");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("over");

    }
}
