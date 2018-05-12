package XQBHServer.Utils.RSA;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


/**
 * RSA��ȫ�������
 */
public abstract class RSAUtil{
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * ��˽Կ����Ϣ��������ǩ��
     * 
     * @param data
     *            ��������
     * @param privateKey
     *            Base64�����ʽ��˽Կ
     * 
     * @return ����Base64������ַ���
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // ������base64�����˽Կ
        byte[] keyBytes = HashUtil.decryptBASE64(privateKey);

        // ����PKCS8EncodedKeySpec����
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // ȡ˽Կ�׶���
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // ��˽Կ����Ϣ��������ǩ��
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return HashUtil.encryptBASE64(signature.sign());
    }

    /**
     * У������ǩ��
     * 
     * @param data
     *            ��������
     * @param publicKey
     *            ��Կ
     * @param sign
     *            ����ǩ��
     * 
     * @return У��ɹ�����true ʧ�ܷ���false
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {

        // ������base64����Ĺ�Կ
        byte[] keyBytes = HashUtil.decryptBASE64(publicKey);

        // ����X509EncodedKeySpec����
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // ȡ��Կ�׶���
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // ��֤ǩ���Ƿ�����
        return signature.verify(HashUtil.decryptBASE64(sign));
    }

    /**
     * ����<br>
     * ��˽Կ����
     * 
     * @param data
     * @param key Base64�����ʽ��˽Կ
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        byte[] decryptedData = null;
        
        // ����Կ����
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int maxDecryptBlockSize = getMaxDencryptBytesByPrivate(keyFactory, privateKey);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < dataLength; i += maxDecryptBlockSize) {
                int decryptLength = dataLength - i < maxDecryptBlockSize ? dataLength - i : maxDecryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, decryptLength);
                bout.write(doFinal);
            }
            decryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }

        return decryptedData;

    }
    
    /**
     * ��Base64��������Ľ���Ϊ�ַ��� 
     * @param base64Str
     * @param key
     * @return
     * @throws Exception
     * @author renteng 
     * @date 2015��12��24�� ����12:35:34
     */
    public static String decryptByPrivateKeyToString(String base64Str, String key) throws Exception{
        byte[] data = HashUtil.decryptBASE64(base64Str);
        byte[] oriData = decryptByPrivateKey(data, key);
        
        return new String(oriData);
    }
    
    /**
     * ��ȡ��Կ���ܿɼ��ܵ���������ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxEncryptBytesByPublicKey(KeyFactory keyFactory, Key key){
        return getPublicKeyBitLength(keyFactory, key) / 8 - 11;
    }
    
       /**
     * ��ȡ��Կ����ÿ����ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxDencryptBytesByPrivate(KeyFactory keyFactory, Key key){
        return getPrivateKeyBitLength(keyFactory, key) / 8;
    }
    
    /**
     * ��ȡ��Կ���ܿɼ��ܵ���������ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxEncryptBytesByPrivate(KeyFactory keyFactory, Key key){
        return getPrivateKeyBitLength(keyFactory, key) / 8 - 11;
    }
    
       /**
     * ��ȡ��Կ����ÿ����ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxDencryptBytesByPublicKey(KeyFactory keyFactory, Key key){
        return getPublicKeyBitLength(keyFactory, key) / 8;
    }
    
    /**
     * ��ȡ��Կ���ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getPublicKeyBitLength(KeyFactory keyFactory, Key key){
        try {
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
            return publicKeySpec.getModulus().bitLength();
        } catch (Exception e) {

        }
        return 2048;
    }
    
    /**
     * ��ȡ˽Կ���ֽڳ���
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getPrivateKeyBitLength(KeyFactory keyFactory, Key key){
        try {
            RSAPrivateKeySpec publicKeySpec = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
            return publicKeySpec.getModulus().bitLength();
        } catch (Exception e) {

        }
        
        return 2048;
    }

    /**
     * ����<br>
     * �ù�Կ����
     * 
     * @param data
     * @param key Base64�����ʽ�Ĺ�Կ
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        byte[] decryptedData = null;
        
        // ����Կ����
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        int maxDecryptBlockSize = getMaxDencryptBytesByPublicKey(keyFactory, publicKey);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < dataLength; i += maxDecryptBlockSize) {
                int decryptLength = dataLength - i < maxDecryptBlockSize ? dataLength - i
                    : maxDecryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, decryptLength);
                bout.write(doFinal);
            }
            decryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }

        return decryptedData;
    }

    /**
     * ����<br>
     * �ù�Կ����
     * 
     * @param data
     * @param key Base64�����ʽ�Ĺ�Կ
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        byte[] encryptedData = null;
        // �Թ�Կ����
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        int maxEncryptBlockSize = getMaxEncryptBytesByPublicKey(keyFactory, publicKey);
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < data.length; i += maxEncryptBlockSize) {
                int encryptLength = dataLength - i < maxEncryptBlockSize ? dataLength - i
                    : maxEncryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, encryptLength);
                bout.write(doFinal);
            }
            encryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }
        
        return encryptedData;
    }

    /**
     * ����<br>
     * ��˽Կ����
     * 
     * @param data ���Ķ���������
     * @param key BASE64�����˽Կ�ַ���
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        byte[] encryptedData = null;
        
        // ����Կ����
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        
        int maxEncryptBlockSize = getMaxEncryptBytesByPrivate(keyFactory, privateKey);
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < data.length; i += maxEncryptBlockSize) {
                int encryptLength = dataLength - i < maxEncryptBlockSize ? dataLength - i
                    : maxEncryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, encryptLength);
                bout.write(doFinal);
            }
            encryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }

        return encryptedData;
    }

    /**
     * ȡ��˽Կ
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return HashUtil.encryptBASE64(key.getEncoded());
    }

    /**
     * ȡ�ù�Կ
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return HashUtil.encryptBASE64(key.getEncoded());
    }

    /**
     * ��ʼ����Կ
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // ��Կ
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // ˽Կ
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
}

