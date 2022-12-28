package com.xs.qr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.zxing.WriterException;
import com.xs.qr.entity.QRText;

import java.io.IOException;

public interface IQRTextService extends IService<QRText> {
    String ordinaryQRCode(String str) throws IOException, WriterException;
}
