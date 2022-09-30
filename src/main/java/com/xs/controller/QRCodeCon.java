package com.xs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/qrcode")
public class QRCodeCon {
    @PostMapping("/ordinary")
    @ResponseBody
    public String postOrdinaryQRCode(@RequestParam() String str) {
        System.out.println("ordinary");
        System.out.println(str);
        return "ok";
    }

    @GetMapping("/t1")
    @ResponseBody
    public String t1(){
        System.out.println("ok");
        return "ok";
    }

}
