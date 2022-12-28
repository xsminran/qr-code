package com.xs.qr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.qr.entity.QRUrl;
import com.xs.qr.mapper.QRUrlMapper;
import com.xs.qr.service.IQRUrlService;
import org.springframework.stereotype.Service;

@Service
public class QRUrlServiceImpl extends ServiceImpl<QRUrlMapper, QRUrl> implements IQRUrlService {
}
