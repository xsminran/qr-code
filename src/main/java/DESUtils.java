import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * DES加密工具类
 */
public class DESUtils {


    /**
     * 随意定一个私钥（长度必须为24位）
     */
    public static final String SECRET_KEY = "ABCDEFGHIJKLMN1234567890";

    /**
     * 加密
     *
     * @param inStr     需要加密的内容
     * @param secretKey 密钥
     * @return 加密后的数据
     */

    public static String encrypt(String inStr, String secretKey) {
        SecretKey deskey = new SecretKeySpec(secretKey.getBytes(), "DESede");
        Cipher cipher;
        String outStr = null;
        try {
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            outStr = byte2hex(cipher.doFinal(inStr.getBytes()));
        } catch (Exception e) {
            System.err.println("3DES加密异常" + e.getMessage());
        }
        System.out.println("3DES加密后字符串：" + outStr);
        return outStr;
    }

    /**
     * 解密
     *
     * @param inStr     需要解密的内容
     * @param secretKey 密钥
     * @return 解密后的数据
     */

    public static String decrypt(String inStr, String secretKey) {
        SecretKey deskey = new SecretKeySpec(secretKey.getBytes(), "DESede");
        Cipher cipher;
        String outStr = null;
        try {
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            outStr = new String(cipher.doFinal(hex2byte(inStr)));
        } catch (Exception e) {
            System.err.println("3DES解密异常" + e.getMessage());
        }
        System.out.println("3DES解密后数据：" + outStr);
        return outStr;
    }

    /**
     * 转化为16进制字符串方法
     *
     * @param digest 需要转换的字节组
     * @return 转换后的字符串
     */
    private static String byte2hex(byte[] digest) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < digest.length; n++) {
            stmp = Integer.toHexString(digest[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0" + stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    /**
     * 十六进转二进制
     *
     * @param hexStr 待转换16进制字符串
     * @return 二进制字节组
     */
    public static byte[] hex2byte(String hexStr) {
        if (hexStr == null)
            return null;
        hexStr = hexStr.trim();
        int len = hexStr.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] digest = new byte[len / 2];
        try {
            for (int i = 0; i < hexStr.length(); i += 2) {
                digest[i / 2] = (byte) Integer.decode("0x" + hexStr.substring(i, i + 2)).intValue();
            }
            return digest;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
//        String pwd = "11111111";
//        String encrypt = encrypt(pwd,SECRET_KEY);
//        System.out.println(encrypt);
//
//        System.out.println(decrypt("6AFD5420C5BB8F4F",SECRET_KEY));
//        System.out.println(decrypt(encrypt,SECRET_KEY));

        int num = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要加密的类型数字： 1、加密账号密码字符串   2、加密xsd文件  3、解密数据");
            String str = sc.nextLine();
            if (str.equals("1")) {
                System.out.println("请输入要加密的数据：");
                String data = sc.nextLine();
                System.out.println("加密后的数据为:" + encrypt(data, SECRET_KEY));
            } else if (str.equals("2")) {
                System.out.println("请输入要加密的xsd路径：");
                String xsdPath = sc.nextLine();
                System.out.println("加密后的xsd内容为:" + encryptXsd(xsdPath));
            } else {
                System.out.println("请输入要解密的数据：");
                String data = sc.nextLine();
                System.out.println("解密后的数据为:" + decrypt(data, SECRET_KEY));
            }
            System.out.println("是否继续操作？ 0 继续， 1 退出");
            num = sc.nextInt();
        } while (num == 0);
        System.exit(0);
    }


    public static String encryptXsd(String xsdPath) {
        ByteArrayInputStream bytein = null;
        String data = null;
//        String xsdPath = "xsd/pacs.008.001.06.xsd";
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(DESUtils.class.getClassLoader().getResource(xsdPath).toURI()));
            StringBuilder stringBuilder = new StringBuilder();
            String liner = "";
            while ((liner = bufferedReader.readLine()) != null) {
                stringBuilder.append(liner);
            }
            System.out.println("读取的xsd内容:" + stringBuilder.toString());
            data = encrypt(stringBuilder.toString(), SECRET_KEY);
            System.out.println("加密后内容：" + data);
            bytein = new ByteArrayInputStream(data.getBytes());
            String decStr = decrypt(data, SECRET_KEY);
            System.out.println("解密后内容：" + decStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}