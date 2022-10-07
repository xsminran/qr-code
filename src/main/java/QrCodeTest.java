import java.io.File;
import java.util.Date;

import com.google.zxing.Result;

public class QrCodeTest {

    public static void main(String[] args) {
        try {
            CreatrQrCode creatrQrCode = new CreatrQrCode();
            //	图片存放地址
            String projectPath = new File("").getAbsolutePath();
            System.out.println("工程路径：" + projectPath);
            String path = projectPath + "/QrCode";
            System.out.println("二维码存放路径：" + path);
            //	路径不存在就创建此路径
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
//            path += "/" + new Date().toString() + ".png";
            path += "/a.png";
            //	图片显示的内容
            String text = "http://106.14.155.125/";

            //	Logo图片地址加名称
//            String LogoPath = projectPath + "/src/main/webapp/images/logo.png";
            String LogoPath = "D://testfile/logo.png";
            //	二维码
            creatrQrCode.createQrCode(path,text,300,300);

            //	Logo
//            creatrQrCode.createLogoQrCode(path,text,300,300,LogoPath);

            //	文字
//            creatrQrCode.createWordQrcode(path, "",text, 400, 470);

            //	Logo+文字
//            creatrQrCode.createWordLogoQrcode(path, "", text, 400, 470, LogoPath);

            //System.out.println(File.separator);
            /**
             * 解析二维码
             */
//            Result result = creatrQrCode.readQrCode(path);
//            System.out.println("读取二维码： " + result.toString());
//            System.out.println("二维码格式： " + result.getBarcodeFormat());
//            System.out.println("二维码内容： " + result.getText());
//
//            //	解密二维码内容
//            String encryptStr = result.getText();
//            String decryptStr = DESUtils.doDecrypt(encryptStr, num);

//            System.out.println("解密后的二维码内容： " + decryptStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}