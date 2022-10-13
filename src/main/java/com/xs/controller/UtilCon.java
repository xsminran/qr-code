package com.xs.controller;

import com.xs.common.QiNiuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/other")
public class UtilCon {

    // TODO 需要进行用户校验才能获取token
    @GetMapping("/getToken")
    @ResponseBody
    public String getToken(){
        return QiNiuUtils.getToken();
    }
}
