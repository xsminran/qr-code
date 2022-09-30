package com.xs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    @GetMapping("/t1")
    @ResponseBody
    public String t1(){
        System.out.println("ok");
        return "ok";
    }
}
