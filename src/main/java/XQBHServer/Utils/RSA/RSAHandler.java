package XQBHServer.Utils.RSA;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAHandler {
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //½«base64±àÂëºóµÄË½Ô¿×Ö·û´®×ª³ÉPrivateKeyÊµÀı
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //¹«Ô¿¼ÓÃÜ
    public static byte[] encrypt(byte[] content,PrivateKey privateKey ) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");//javaÄ¬ÈÏ"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    //Ë½Ô¿½âÃÜ
    public static byte[] decrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static void main(String[] args) {
        //for test
        String privatek="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDYRwlUWTrXg9jPiU9JDq8fh2pfNwtT6KpE18hPfo5yicAGUUsefxL9oC5ioWcPkV+Xu++2oxdmaGLr/ujj2m1WjCcO70Ou4SrbbNbmtcJBy7OEGlgNqf7tl1Zow0mYhT6ujcgtY57hlu8TGFQV4kGQf8XKQYJ7keVkY127YXuKU6CmLGqXCYeQJJHguP9J0TqNLny6BOlOjSS44dSpnX0UPU4NhroNS5HLDv9iamyxgctehSN9hiPY891pB82C0qXB49GxOhXmbKydhPtu75sWEeEqQDAp1YnoaaquF7DZRFAgjS0kkrcUwkV2PxPmeYRhi52ihn48evJCGiN6Hs9VAgMBAAECggEBANHm15oiY6ZIkwKQ2/8mnjX2Yfl43aiZFa9s0T69sBhfspsvCL3XTKIUdRBKX1DPoTwNLRBPZuWACAnMw1BobFdj/IBVHJY8eDCviD4vRxI+VcKvIqhYRU9n4ngYmHPLVdNpTU8n8Uo2B1+769e1WDaam2a5f57YMQ7mFVFHwfOzf2YKv9gWecvtHCPXuDmoWKvV/KYVvo8xZ4nGwGJdTyY0vZTfbjonKyEnQiljzULR9thqR/eejTbG34gaACvucvsupCdF/mKqaHPioEcNrEfwmAa+sFEMXpUemZQnrS22C4f7dAsu9PIiTZpipIm6YbjJ0lywnpf9yYJ7VYfsbIECgYEA7P078bVP3QR+XIHPqJjWaDGGAxTWJrVT16Iv5xhMBNHCB4tIwEQFVChDNVPEw8UdeQQvc3gvnIsVn2hca29zXkVYqH/degugVr1rxGLuePRGFmGitgcn9QqmWKFOm/PNiDZW576gsLZjd6b+mPaevKsdd54YdPDofJTc8LM/I7UCgYEA6aB4caAnwQ0BBUxZd2ZSGVwJrmHn5nXKDjxrL87Z6vg4OwDX6dVcAH9OtknihmAtduai2ayg4EWe8kFVnd51iXaSfzEiwdKHKPgUA6eta1huBHp3SP5DNumVTDPtD/bf4nd2vrJBor3LVbe2r6L/H2ShmPHPnQhW8kTDSz4pgSECgYBAQw69JSgpy20kUoLnucHx8PPg5AaJ6oN4pl8M8Ba0+9f8SbWJhShYwK4wyK1DVLEAPrVLP1zRuxk654agD1GeT3mR/1IkJQDuZGDTmOwHWl2i9gi0CU65cJDY2azCNyMVe36nSpayNFLWgC7rdXxntpK/+9uv4h94oLkkf8ZwPQKBgQCqtT2s0PibYDQhufMZgqN0skLEr/dx9xmII2+yxDOJNIxp2KjrzKHoHx3VptElnPs7iTTvVutKVLTakRDNRPKfWgubcrzR4VIvhm2hahEWgcwJ665joJ5ebnlP8BVFd/+Ji/8xQjEhiAsefBm55qECQFav2ej49lIJvmLxBN/w4QKBgQCuLrqQiacvOuucK/0Tv/R2czOCRV1Sc4GgpZBGZhv0R+7CXGk2VlQ/KQ+NiRdOTLO0wzisUYGBsHwj1k6IJcfKn/9qm5FvfKoXPPkamWivCWJNbwE6AzIvmwDRiZ9m6AXgWGVZTdd/DK8hc3qegbE9rjQciA5Bk2Xk+LtfI5BdWA==";
        String publick="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2EcJVFk614PYz4lPSQ6vH4dqXzcLU+iqRNfIT36OconABlFLHn8S/aAuYqFnD5Ffl7vvtqMXZmhi6/7o49ptVownDu9DruEq22zW5rXCQcuzhBpYDan+7ZdWaMNJmIU+ro3ILWOe4ZbvExhUFeJBkH/FykGCe5HlZGNdu2F7ilOgpixqlwmHkCSR4Lj/SdE6jS58ugTpTo0kuOHUqZ19FD1ODYa6DUuRyw7/YmpssYHLXoUjfYYj2PPdaQfNgtKlwePRsToV5mysnYT7bu+bFhHhKkAwKdWJ6Gmqrhew2URQII0tJJK3FMJFdj8T5nmEYYudooZ+PHryQhojeh7PVQIDAQAB";
        String a="{\"head\":{\"HTJYM_\":\"ZDLogin\",\"QTRQ_U\":\"20180227\",\"ZDBH_U\":\"ZD0000000001\",\"IP_UUU\":\"192057t6r9.iask.in:26617\",\"QTLS_U\":\"CZD00000000010000004\",\"ZDJYM_\":\"1234567890\",\"QTSJ_U\":\"005250\",\"QTJYM_\":\"ZDLogin\"},\"body\":{}}";
        String tmp="\n" +
                ":?>â²YÊµšˆ;?\u001E9ßGr,œíéÉ±Œ?% K\u000F?\u001D\uE5BC? )\u001Fl\u001C+0öÄ?\u001Fäş¸ÊÜ{>/ZU\u0011²¨Z3\uE3D07?\u0001k\uE2FF\u001A1êP?%ˆİØæ\u0004NB?4\u0014ê£(½ ùj?\u0003\u0003s?Ÿ°k?=m/Àƒ?\u001B7b\u0017I?\u0006?\f?5œÒ†\u0002\n" +
                "¿İşI\uE6EB\u000B\\÷¾çõ4u\uE4C2Úö‡B\u000FSmƒ-?\u001Ewºè\n" +
                "3Ä¢¾WÆÃL?\u0011";
//        System.out.println(tmp);
        try {
            PublicKey publicKey=getPublicKey(publick);
            PrivateKey privateKey=getPrivateKey(privatek);
            byte []bbb=encrypt(a.getBytes(),privateKey);
            String bbbstr=new String(bbb);
            System.out.println("bbb=["+new String (bbb)+"]");
            System.out.println("bbb.length=["+new String (bbb).length()+"]");
            System.out.println("bbbstr=["+bbbstr+"]");
            System.out.println("bbbstr.length=["+bbbstr.length()+"]");
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(bbbstr);
            System.out.println("stringBuilder=["+stringBuilder.toString()+"]");
            System.out.println("stringBuilder.length=["+stringBuilder.toString().length()+"]");

            System.out.println("tmp=["+tmp+"]");
            System.out.println("tmp.length=["+tmp.length()+"]");

            System.out.println(tmp.equals(new String (bbb)));
            byte []ccc=decrypt(bbb,publicKey);
            System.out.println(new String(ccc));
//            System.out.println(new String(decrypt(tmp.getBytes(),publicKey)));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
