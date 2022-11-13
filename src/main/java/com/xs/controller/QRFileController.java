package com.xs.controller;

import com.google.zxing.WriterException;
import com.xs.common.QiNiuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/api/qrcode")
public class QRFileController {

    @PostMapping("/live")
    @ResponseBody
    public String postAddLiveQRCode(@RequestParam() String str, @RequestParam() String id) throws IOException, WriterException {
//        System.out.println("live");
//        String userid = "0";
//        System.out.println("str:"+str);
//        String res = qrCodeService.liveQRCodeHash(str);
//        System.out.println("res:"+res);
//        String visitAddress = QiNiuUtils.getVisitAddressByHash(str);
//        System.out.println("redirect:"+visitAddress);
//        String url = qrCodeService.aas(visitAddress);
//        return url;
        return "";
    }
}
