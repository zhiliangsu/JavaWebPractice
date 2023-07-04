package com.hitech;

import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAopQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopQuickstartApplication.class, args);
    }

    // 在启动类上声明第三方bean(不推荐)
    /* @Bean // 将当前方法的返回值对象交给IOC容器管理, 成为IOC容器bean
    public SAXReader saxReader() {
        return new SAXReader();
    } */
}
