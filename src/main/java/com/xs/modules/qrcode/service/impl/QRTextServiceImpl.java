package com.xs.modules.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.zxing.WriterException;
import com.qiniu.storage.model.DefaultPutRet;
import com.xs.common.QRCodeUtils;
import com.xs.common.QiNiuUtils;
import com.xs.modules.qrcode.entity.QRText;
import com.xs.modules.qrcode.mapper.QRTextMapper;
import com.xs.modules.qrcode.service.IQRTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class QRTextServiceImpl extends ServiceImpl<QRTextMapper, QRText> implements IQRTextService {

    @Autowired
    private QRTextMapper textMapper;
    // 生成普通二维码
    public String ordinaryQRCode(String str) throws IOException, WriterException {
        // 生成二维码存入本地
        String projectPath = new File("").getAbsolutePath();
        System.out.println("工程路径：" + projectPath);
        String path = projectPath + "/QrCode/";
        System.out.println("二维码存放路径：" + path);
        final String qrCodeImage = QRCodeUtils.getQRCodeImage(str, 350, 350, path);
        // 将图片上传到对象存储服务器
        final DefaultPutRet putRet = QiNiuUtils.uploadFile(path, qrCodeImage);
        // 删除本地文件
        Files.delete(Paths.get(path + qrCodeImage));
        // 获取图片访问地址
        String address = QiNiuUtils.getVisitAddress(qrCodeImage);
        // 存储信息到数据库
        assert putRet != null;
        QRText qrText = new QRText();
        qrText.setContent(str);
        qrText.setAccountId("1");
        qrText.setQiniuHash(putRet.hash);

        textMapper.insert(qrText);
        return address;
    }
}
