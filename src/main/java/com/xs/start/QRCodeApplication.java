package com.xs.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xs")
public class QRCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(QRCodeApplication.class, args);
    }
}
