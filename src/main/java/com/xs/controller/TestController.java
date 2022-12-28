package com.xs.controller;

import com.xs.common.util.MinioUtil;
import com.xs.common.util.OSUtils;
import com.xs.common.util.ObjectStorage;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController()
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ObjectStorage objectStorage;

    @GetMapping("/t1")
    public String test(){
        System.out.println("333");
        List<?> allBuckets = objectStorage.getAllBuckets();
        for (Object allBucket : allBuckets) {
            Bucket b = (Bucket) allBucket;
            System.out.println(b.name());
        }
        System.out.println(allBuckets.toString());
        return "ss";
    }
}
