package com.xs.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.modules.system.entity.SysAccount;
import com.xs.modules.system.mapper.SysAccountMapper;
import com.xs.modules.system.service.ISysAccountService;
import org.springframework.stereotype.Service;

@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {
}
