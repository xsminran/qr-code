import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

/**
 * DES���ܽ��� DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��DES�����㷨����IBM���о���
 * ����������������ʽ���ã�֮��ʼ�㷺���������ǽ�Щ��ʹ��Խ��Խ�٣���ΪDESʹ��56λ��Կ�����ִ�����������
 * 24Сʱ�ڼ��ɱ��ƽ⡣��Ȼ��ˣ���ĳЩ��Ӧ���У����ǻ��ǿ���ʹ��DES�����㷨�����ļ򵥽���DES��JAVAʵ�� ��
 * ע�⣺DES���ܺͽ��ܹ����У���Կ���ȶ�������8�ı���
 */
public class DES {
    public DES() {
    }

    // ����
    public static void main(String args[]) {
        // ����������
        String str = "��������";
        // ���룬����Ҫ��8�ı���
        String password = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

        byte[] result = DES.encrypt(str.getBytes(), password);
        System.out.println("���ܺ�" + Arrays.toString(result));

        // ֱ�ӽ��������ݽ���
        try {
            byte[] decryResult = DES.decrypt(result, password);
            System.out.println("���ܺ�" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    /**
     * ����
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher����ʵ����ɼ��ܲ���
            Cipher cipher = Cipher.getInstance("DES");
            // ���ܳ׳�ʼ��Cipher����
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // ���ڣ���ȡ���ݲ�����
            // ��ʽִ�м��ܲ���
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES�㷨Ҫ����һ�������ε������Դ
        SecureRandom random = new SecureRandom();
        // ����һ��DESKeySpec����
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // ����һ���ܳ׹���
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // ��DESKeySpec����ת����SecretKey����
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher����ʵ����ɽ��ܲ���
        Cipher cipher = Cipher.getInstance("DES");
        // ���ܳ׳�ʼ��Cipher����
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // ������ʼ���ܲ���
        return cipher.doFinal(src);
    }
}