package com.xs.controller;

import com.google.zxing.WriterException;
import com.xs.modules.qrcode.service.IQRTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/api/qrcode")
public class QRTextController {
    @Autowired
    private IQRTextService iqrTextService;

    @PostMapping("/ordinary")
    @ResponseBody
    public String postOrdinaryQRCode(@RequestParam() String str) {
        System.out.println("ordinary");
        try {
            return iqrTextService.ordinaryQRCode(str);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
        return "err";
    }
}
