package com.xs.common;

import org.springframework.beans.factory.annotation.Value;

public class QiNiu {
    @Value("qiniu.accessKey")
    private String accessKey;
    @Value("qiniu.secretKey")
    private String secretKey;
    String bucket = "bucket name";
}
