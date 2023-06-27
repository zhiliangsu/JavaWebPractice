package com.hitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @ComponentScan({"dao", "com.hitech"}) //不推荐
@SpringBootApplication
public class SpringbootWebReqRespApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebReqRespApplication.class, args);
    }

}
