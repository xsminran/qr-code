package com.xs.qr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.qr.mapper.QRStaticMapper;
import com.xs.qr.service.IQRStaticService;
import org.springframework.stereotype.Service;
import com.xs.qr.entity.QRStatic;

@Service
public class QRStaticServiceImpl extends ServiceImpl<QRStaticMapper, QRStatic> implements IQRStaticService {
}
