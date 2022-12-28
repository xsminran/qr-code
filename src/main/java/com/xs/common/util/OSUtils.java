package com.xs.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OSUtils {
    @Autowired
    private MinioUtil minioUtil;
    @Bean
    public ObjectStorage objectStorage(){
        return minioUtil;
    }
}
