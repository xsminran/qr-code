package com.xs.controller;

import com.alibaba.fastjson2.JSONObject;
import com.xs.common.api.vo.Result;
import com.xs.common.util.PasswordUtil;
import com.xs.modules.system.entity.SysAccount;
import com.xs.modules.system.service.ISysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/sys/account")
@Controller
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

    @PostMapping("/register")
    public Result<JSONObject> userRegister(@RequestBody JSONObject jsonObject, SysAccount sysAccount) {
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
