package com.xs.qr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.qr.entity.QRFile;
import com.xs.qr.mapper.QRFileMapper;
import com.xs.qr.service.IQRFileService;
import org.springframework.stereotype.Service;

@Service
public class QRFileServiceImpl extends ServiceImpl<QRFileMapper, QRFile> implements IQRFileService {
}
