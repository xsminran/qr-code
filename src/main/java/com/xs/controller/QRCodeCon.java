package com.xs.controller;

import com.google.zxing.WriterException;
import com.xs.common.QiNiuUtils;
import com.xs.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/qrcode")
public class QRCodeCon {
    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping("/ordinary")
    @ResponseBody
    public String postOrdinaryQRCode(@RequestParam() String str) {
        System.out.println("ordinary");
        try {
            return qrCodeService.ordinaryQRCode(str);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
        return "err";
    }

    @PostMapping("/live")
    @ResponseBody
    public String postAddLiveQRCode(@RequestParam() String str, @RequestParam() String id) throws IOException, WriterException {
        System.out.println("live");
        String userid = "0";
        System.out.println("str:"+str);
        String res = qrCodeService.liveQRCodeHash(str);
        System.out.println("res:"+res);
        String visitAddress = QiNiuUtils.getVisitAddressByHash(str);
        System.out.println("redirect:"+visitAddress);
        String url = qrCodeService.aas(visitAddress);
        return url;
    }

    @GetMapping("/t1")
    public String t1() {
        return "redirect:" + qrCodeService.Test();
    }

}
