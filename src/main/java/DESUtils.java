import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * DES���ܹ�����
 */
public class DESUtils {


    /**
     * ���ⶨһ��˽Կ�����ȱ���Ϊ24λ��
     */
    public static final String SECRET_KEY = "ABCDEFGHIJKLMN1234567890";

    /**
     * ����
     *
     * @param inStr     ��Ҫ���ܵ�����
     * @param secretKey ��Կ
     * @return ���ܺ������
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
            System.err.println("3DES�����쳣" + e.getMessage());
        }
        System.out.println("3DES���ܺ��ַ�����" + outStr);
        return outStr;
    }

    /**
     * ����
     *
     * @param inStr     ��Ҫ���ܵ�����
     * @param secretKey ��Կ
     * @return ���ܺ������
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
            System.err.println("3DES�����쳣" + e.getMessage());
        }
        System.out.println("3DES���ܺ����ݣ�" + outStr);
        return outStr;
    }

    /**
     * ת��Ϊ16�����ַ�������
     *
     * @param digest ��Ҫת�����ֽ���
     * @return ת������ַ���
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
     * ʮ����ת������
     *
     * @param hexStr ��ת��16�����ַ���
     * @return �������ֽ���
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
            System.out.println("������Ҫ���ܵ��������֣� 1�������˺������ַ���   2������xsd�ļ�  3����������");
            String str = sc.nextLine();
            if (str.equals("1")) {
                System.out.println("������Ҫ���ܵ����ݣ�");
                String data = sc.nextLine();
                System.out.println("���ܺ������Ϊ:" + encrypt(data, SECRET_KEY));
            } else if (str.equals("2")) {
                System.out.println("������Ҫ���ܵ�xsd·����");
                String xsdPath = sc.nextLine();
                System.out.println("���ܺ��xsd����Ϊ:" + encryptXsd(xsdPath));
            } else {
                System.out.println("������Ҫ���ܵ����ݣ�");
                String data = sc.nextLine();
                System.out.println("���ܺ������Ϊ:" + decrypt(data, SECRET_KEY));
            }
            System.out.println("�Ƿ���������� 0 ������ 1 �˳�");
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
            System.out.println("��ȡ��xsd����:" + stringBuilder.toString());
            data = encrypt(stringBuilder.toString(), SECRET_KEY);
            System.out.println("���ܺ����ݣ�" + data);
            bytein = new ByteArrayInputStream(data.getBytes());
            String decStr = decrypt(data, SECRET_KEY);
            System.out.println("���ܺ����ݣ�" + decStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}