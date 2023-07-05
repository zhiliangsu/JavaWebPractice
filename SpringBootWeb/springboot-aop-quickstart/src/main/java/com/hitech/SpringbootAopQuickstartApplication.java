package com.hitech;

import com.example.EnableHeaderConfig;
import com.example.HeaderConfig;
import com.example.MyImportSelector;
import com.example.TokenParser;
import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// @ComponentScan({"com.hitech", "com.example"}) // 使用繁琐,性能低,不推荐
// @Import(TokenParser.class) // 导入普通类
// @Import(HeaderConfig.class) // 导入配置类
// @Import(MyImportSelector.class) // 导入ImportSelector接口实现类
@EnableHeaderConfig  //使用第三方依赖提供的Enable开头的注解
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
