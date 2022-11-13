package com.xs.modules.qrcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.modules.qrcode.entity.QRFile;
import com.xs.modules.qrcode.mapper.QRFileMapper;
import com.xs.modules.qrcode.service.IQRFileService;
import org.springframework.stereotype.Service;

@Service
public class QRFileServiceImpl extends ServiceImpl<QRFileMapper, QRFile> implements IQRFileService {
}
