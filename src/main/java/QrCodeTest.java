import java.io.File;
import java.util.Date;

import com.google.zxing.Result;

public class QrCodeTest {

    public static void main(String[] args) {
        try {
            CreatrQrCode creatrQrCode = new CreatrQrCode();
            //	ͼƬ��ŵ�ַ
            String projectPath = new File("").getAbsolutePath();
            System.out.println("����·����" + projectPath);
            String path = projectPath + "/QrCode";
            System.out.println("��ά����·����" + path);
            //	·�������ھʹ�����·��
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
//            path += "/" + new Date().toString() + ".png";
            path += "/a.png";
            //	ͼƬ��ʾ������
            String text = "http://106.14.155.125/";

            //	LogoͼƬ��ַ������
//            String LogoPath = projectPath + "/src/main/webapp/images/logo.png";
            String LogoPath = "D://testfile/logo.png";
            //	��ά��
//            creatrQrCode.createQrCode(path,text,300,300);

            //	Logo
            creatrQrCode.createLogoQrCode(path,text,300,300,LogoPath);

            //	����
//            creatrQrCode.createWordQrcode(path, "",text, 400, 470);

            //	Logo+����
//            creatrQrCode.createWordLogoQrcode(path, "", text, 400, 470, LogoPath);

            //System.out.println(File.separator);
            /**
             * ������ά��
             */
//            Result result = creatrQrCode.readQrCode(path);
//            System.out.println("��ȡ��ά�룺 " + result.toString());
//            System.out.println("��ά���ʽ�� " + result.getBarcodeFormat());
//            System.out.println("��ά�����ݣ� " + result.getText());
//
//            //	���ܶ�ά������
//            String encryptStr = result.getText();
//            String decryptStr = DESUtils.doDecrypt(encryptStr, num);

//            System.out.println("���ܺ�Ķ�ά�����ݣ� " + decryptStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}