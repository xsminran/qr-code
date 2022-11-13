package com.xs.modules.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.modules.qrcode.entity.QRStatic;
import com.xs.modules.qrcode.mapper.QRStaticMapper;
import com.xs.modules.qrcode.service.IQRStaticService;
import org.springframework.stereotype.Service;

@Service
public class QRStaticServiceImpl extends ServiceImpl<QRStaticMapper, QRStatic> implements IQRStaticService {
}
