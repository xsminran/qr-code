package com.xs;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.xs.modules.system.mapper","com.xs.qr.mapper","com.xs.bm.dir.mapper"})
public class QRCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(QRCodeApplication.class, args);
    }
}
