package XQBHServer.Test;

import java.util.Map;


public class test {
    public static void main(String[] args) {
//        MyWxConfig config = null;
//        WXPay wxpay = null;
//        Map<String, String> response = new HashMap<String, String>();
//        Map<String, String> require = new HashMap<String, String>();
//        try {
//            config = new MyWxConfig("111");
//            wxpay = new WXPay(config, true, Com.wxpayUseSandbox);
//
//
//        } catch (Exception e) {
//        }
//
//        String aaa = "<xml>\n" +
//                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
//                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
//                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
//                "   <device_info><![CDATA[1000]]></device_info>\n" +
//                "   <nonce_str><![CDATA[GOp3TRyMXzbMlkun]]></nonce_str>\n" +
//                "   <sign><![CDATA[D6C76CB785F07992CDE05494BB7DF7FD]]></sign>\n" +
//                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
//                "   <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>\n" +
//                "   <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
//                "   <trade_type><![CDATA[MICROPAY]]></trade_type>\n" +
//                "   <bank_type><![CDATA[CCB_DEBIT]]></bank_type>\n" +
//                "   <total_fee>1</total_fee>\n" +
//                "   <coupon_fee>0</coupon_fee>\n" +
//                "   <fee_type><![CDATA[CNY]]></fee_type>\n" +
//                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
//                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
//                "   <attach><![CDATA[¶©µ¥¶îÍâÃèÊö]]></attach>\n" +
//                "   <time_end><![CDATA[20141111170043]]></time_end>\n" +
//                "</xml>";
//        System.out.println(aaa);
//
//
//        Document document = null;
//        try {
//            document = (Document) DocumentHelper.parseText(aaa);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        Element esbEntry = document.getRootElement();
//        Map<String,String> map=new HashMap<String,String> ();
//
//        for (Iterator iterator = esbEntry.elementIterator(); iterator.hasNext();) {
//            Element e = (Element) iterator.next();
//            //System.out.println(e.getName());
//            List list = e.elements();
//
//                map.put(e.getName(), e.getText());
//            System.out.println(e.getName()+" "+e.getText());
//        }




    Map a=null;
    aaa(a);
        System.out.println(a.get("1"));
    }
    static void aaa(Map map){
        map.put("1","1");
    }
}