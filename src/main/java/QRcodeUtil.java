package com.lckj.api.card.util;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

@Service
public class QRcodeUtil {

    /**
     * 生成发送二维码方法
     *
     * @param text     二维码生成规则(二维码可以是任何英文字母加数字生成的二维码)
     * @param width    宽度
     * @param height   高度
     * @param filePath 输出图片地址
     */
    public void getQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        //生成二维码类
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //生成的二维码
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        //生成图片唯一名称,加上.png格式
        String pat = getUUID() + ".png";
        //图片路劲加上图片名称  (输出地址)
        filePath += pat;
        Path path = FileSystems.getDefault().getPath(filePath);
        //输出二维码图片
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }


    //运行测试
    public static void main(String[] args) {
        try {
            QRcodeUtil qRcodeUtil = new QRcodeUtil();

            //Xiaojie wants to eat eggs就是我们的生成规则，可随便填写
            qRcodeUtil.getQRCodeImage("http://106.14.155.125/", 350, 350, "D:\\testfile\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 把String自带的UUID去除下划线返回
     *
     * @return String UUID
     */
    public String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
