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
        ConfigurableApplicationContext context = SpringApplication.run(QRCodeApplication.class, args);

        //获取当前容器中，所有beanId的数组；
//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//            System.out.println("对象类型是：" + context.getBean(beanName).getClass().getName());
//            System.out.println("对象内容是：" + context.getBean(beanName).toString());
//            System.out.println("=======================");
//        }
    }
}
