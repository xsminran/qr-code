package com.xs.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.common.api.vo.Result;
import com.xs.common.util.PasswordUtil;
import com.xs.modules.system.entity.SysAccount;
import com.xs.modules.system.model.SysLoginModel;
import com.xs.modules.system.service.ISysAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/sys/account")
@RestController
@Api(tags="用户登录")
@Slf4j
public class SysAccountController {
    
    @Autowired
    private ISysAccountService sysAccountService;


    @PostMapping("/a")
    public Result<JSONObject> userRegister(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        return Result.OK();
    }

    @PostMapping("/b")
    public Result<String> userRegister(@RequestParam String str) {
        System.out.println(str);
        return Result.OK();
    }

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();
//        //update-begin--Author:scott  Date:20190805 for：暂时注释掉密码加密逻辑，有点问题
//        //前端密码加密，后端进行密码解密
//        //password = AesEncryptUtil.desEncrypt(sysLoginModel.getPassword().replaceAll("%2B", "\\+")).trim();//密码解密
//        //update-begin--Author:scott  Date:20190805 for：暂时注释掉密码加密逻辑，有点问题
//
//        //update-begin-author:taoyan date:20190828 for:校验验证码
//        String captcha = sysLoginModel.getCaptcha();
//        if(captcha==null){
//            result.error500("验证码无效");
//            return result;
//        }
//        String lowerCaseCaptcha = captcha.toLowerCase();
//        String realKey = MD5Util.MD5Encode(lowerCaseCaptcha+sysLoginModel.getCheckKey(), "utf-8");
//        Object checkCode = redisUtil.get(realKey);
//        //当进入登录页时，有一定几率出现验证码错误 #1714
//        if(checkCode==null || !checkCode.toString().equals(lowerCaseCaptcha)) {
//            result.error500("验证码错误");
//            return result;
//        }
//        //update-end-author:taoyan date:20190828 for:校验验证码
//
//        //1. 校验用户是否有效
//        //update-begin-author:wangshuai date:20200601 for: 登录代码验证用户是否注销bug，if条件永远为false
//        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysAccount::getUsername,username);
//        SysAccount sysUser = sysAccountService.getOne(queryWrapper);
//        //update-end-author:wangshuai date:20200601 for: 登录代码验证用户是否注销bug，if条件永远为false
//        result = sysAccountService.checkUserIsEffective(sysUser);
//        if(!result.isSuccess()) {
//            return result;
//        }
//
//        //2. 校验用户名或密码是否正确
//        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
//        String syspassword = sysUser.getPassword();
//        if (!syspassword.equals(userpassword)) {
//            result.error500("用户名或密码错误");
//            return result;
//        }
//
//        //用户登录信息
//        userInfo(sysUser, result);
//        //update-begin--Author:wangshuai  Date:20200714  for：登录日志没有记录人员
//        LoginUser loginUser = new LoginUser();
//        BeanUtils.copyProperties(sysUser, loginUser);
//        baseCommonService.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null,loginUser);
//        //update-end--Author:wangshuai  Date:20200714  for：登录日志没有记录人员
//        return result;
        return null;
    }

    @PostMapping("/register")
    public Result<JSONObject> userRegister(@RequestBody JSONObject jsonObject, SysAccount sysAccount) {
        System.out.println("register");
        Result<JSONObject> result = new Result<>();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        // TODO:未设置用户名，则用手机号作为用户名
        // TODO:未设置密码，则随机生成一个密码

        try {
            // TODO:设置创建时间
//            String salt = oConvertUtils.randomGen(8);
            String salt = "abcdefgh";
            String passwordEncode = PasswordUtil.encrypt(username, password, salt);
            sysAccount.setSalt(salt);
            sysAccount.setUsername(username);
            sysAccount.setPassword(passwordEncode);
            sysAccountService.saveOrUpdate(sysAccount);
            result.success("注册成功");
        } catch (Exception e) {
            result.error500("注册失败");
        }
        return result;
    }

//    @PostMapping("/register")
//    public Result<JSONObject> userRegister(@RequestBody JSONObject jsonObject, SysAccount user) {
//        Result<JSONObject> result = new Result<JSONObject>();
//        String phone = jsonObject.getString("phone");
//        String smscode = jsonObject.getString("smscode");
//        Object code = redisUtil.get(phone);
//        String username = jsonObject.getString("username");
//        // 未设置用户名，则用手机号作为用户名
//        if (oConvertUtils.isEmpty(username)) {
//            username = phone;
//        }
//        //未设置密码，则随机生成一个密码
//        String password = jsonObject.getString("password");
//        if (oConvertUtils.isEmpty(password)) {
//            password = RandomUtil.randomString(8);
//        }
//        String email = jsonObject.getString("email");
//        SysUser sysUser1 = sysUserService.getUserByName(username);
//        if (sysUser1 != null) {
//            result.setMessage("用户名已注册");
//            result.setSuccess(false);
//            return result;
//        }
//        SysUser sysUser2 = sysUserService.getUserByPhone(phone);
//        if (sysUser2 != null) {
//            result.setMessage("该手机号已注册");
//            result.setSuccess(false);
//            return result;
//        }
//
//        if (oConvertUtils.isNotEmpty(email)) {
//            SysUser sysUser3 = sysUserService.getUserByEmail(email);
//            if (sysUser3 != null) {
//                result.setMessage("邮箱已被注册");
//                result.setSuccess(false);
//                return result;
//            }
//        }
//        if (null == code) {
//            result.setMessage("手机验证码失效，请重新获取");
//            result.setSuccess(false);
//            return result;
//        }
//        if (!smscode.equals(code.toString())) {
//            result.setMessage("手机验证码错误");
//            result.setSuccess(false);
//            return result;
//        }
//
//        try {
//            user.setCreateTime(new Date());// 设置创建时间
//            String salt = oConvertUtils.randomGen(8);
//            String passwordEncode = PasswordUtil.encrypt(username, password, salt);
//            user.setSalt(salt);
//            user.setUsername(username);
//            user.setRealname(username);
//            user.setPassword(passwordEncode);
//            user.setEmail(email);
//            user.setPhone(phone);
//            user.setStatus(CommonConstant.USER_UNFREEZE);
//            user.setDelFlag(CommonConstant.DEL_FLAG_0);
//            user.setActivitiSync(CommonConstant.ACT_SYNC_0);
//            sysUserService.addUserWithRole(user, "ee8626f80f7c2619917b6236f3a7f02b");//默认临时角色 test
//            result.success("注册成功");
//        } catch (Exception e) {
//            result.error500("注册失败");
//        }
//        return result;
//    }
}
