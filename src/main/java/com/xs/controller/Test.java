package com.xs.controller;

import com.xs.common.QiNiuUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class Test {
    @GetMapping("/t1")
    @ResponseBody
    public String t1() {
        System.out.println("ok");
        return "ok";
    }

    //图片上传
//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Result upload(@RequestPart("imgFile") MultipartFile imgFile) {
//        try {
//            //获取原始文件名
//            String originalFilename = imgFile.getOriginalFilename();
//            int lastIndexOf = originalFilename.lastIndexOf(".");
//            //获取文件后缀
//            String suffix = originalFilename.substring(lastIndexOf - 1);
//            //使用UUID随机产生文件名称，防止同名文件覆盖
//            String fileName = UUID.randomUUID().toString() + suffix;
//            QiNiuUtils.upload2QiNiu(imgFile.getBytes(), fileName);
//            //图片上传成功
//            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS);
//            result.setData(fileName);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            //图片上传失败
//            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
//        }
//    }

}
