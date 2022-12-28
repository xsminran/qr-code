package com.xs.common.constant.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * minio 属性值
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {
    /**
     * 连接url
     */
    @Value("${minio.endpoint}")
    private String endpoint;
    /**
     * 用户名
     */
    @Value("${minio.accessKey}")
    private String accesskey;
    /**
     * 密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;
}
