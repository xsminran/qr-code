package com.xs.modules.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.modules.qrcode.entity.QRUrl;
import com.xs.modules.qrcode.mapper.QRUrlMapper;
import com.xs.modules.qrcode.service.IQRUrlService;
import org.springframework.stereotype.Service;

@Service
public class QRUrlServiceImpl extends ServiceImpl<QRUrlMapper, QRUrl> implements IQRUrlService {
}
