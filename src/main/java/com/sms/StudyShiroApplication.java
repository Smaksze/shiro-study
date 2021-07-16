package com.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.sms.shiro.mapper")
public class StudyShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyShiroApplication.class, args);
    }

}
