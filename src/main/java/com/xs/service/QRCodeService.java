package com.xs.service;

import com.google.zxing.WriterException;
import com.qiniu.storage.model.DefaultPutRet;
import com.xs.common.QRCodeUtils;
import com.xs.common.QiNiuUtils;
import com.xs.entity.LiveHash;
import com.xs.entity.QRCode;
import com.xs.entity.QRCodeStatic;
import com.xs.entity.Text;
import com.xs.mapper.LiveHashMapper;
import com.xs.mapper.QRCodeMapper;
import com.xs.mapper.QRCodeStaticMapper;
import com.xs.mapper.TextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class QRCodeService {
    @Autowired
    private TextMapper textMapper;

    @Autowired
    private QRCodeStaticMapper qrCodeStaticMapper;

    @Autowired
    private QRCodeMapper qrCodeMapper;

    @Autowired
    private LiveHashMapper liveHashMapper;


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
        qrCodeStaticMapper.insert(new QRCodeStatic("1", address, putRet.hash));
        return address;
    }

    public String liveQRCodeText(String str, String id) {
        String uid = "0";
        String qid = "aaaaaaaaaaaaaaa";
        // 保存值
        Text text = new Text();
        text.setUserId(uid);
        text.setText(str);
        final int insert = textMapper.insert(text);
        String textId = text.getId();
        System.out.println(insert);
        // 将用户资源上传到对象存储服务器


        // 保存二维码表

        return "";
    }

    public String liveQRCodeHash(String hash) {
        // 将hash值保存到数据库
        LiveHash liveHash = new LiveHash();
        liveHash.setHash(hash);
        liveHash.setUserId("1");
        int insert = liveHashMapper.insert(liveHash);
        if (insert == 1) {
            // 保存成功
            System.out.println(liveHash.getId());

        }
        return liveHash.getId();
    }

    // 生成二维码
    public String aas(String str) throws IOException, WriterException {
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
        return address;
    }

    public String Test() {
        return "http://rj0g1pmcb.hn-bkt.clouddn.com/qrcode%2Fstatic%2Fb418e7e1c8e2494ca9098f399beca6e6.png?e=1664887103&token=nccHwQX42Io_TrucTSQmksJZhCm2hxtT_X-msF7N:hUWTgbuUMnUdQBIJIRVr5dov0V0=";
    }

}
