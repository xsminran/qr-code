package com.xs.modules.qrcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.zxing.WriterException;
import com.xs.modules.qrcode.entity.QRText;

import java.io.IOException;

public interface IQRTextService extends IService<QRText> {
    String ordinaryQRCode(String str) throws IOException, WriterException;
}
