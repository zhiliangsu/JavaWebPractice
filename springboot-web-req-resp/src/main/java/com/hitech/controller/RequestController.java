package com.hitech.controller;

import com.hitech.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {
    // 原始方式
    /* @RequestMapping("simpleParam")
    public String simpleParam(HttpServletRequest request) {
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        Integer age = Integer.parseInt(ageStr);
        System.out.println(name + ": " + age);
        return "OK";
    } */

    // springboot方式
    /* @RequestMapping("simpleParam")
    public String simpleParam(String name, Integer age) {
        System.out.println(name + ": " + age);
        return "OK";
    } */

    // 1.简单参数
    @RequestMapping("simpleParam")
    public String simpleParam(@RequestParam(name = "name", required = false) String username, int age) {
        System.out.println(username + ": " + age);
        return "OK";
    }

    // 2.实体参数
    @RequestMapping("simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "OK";
    }

    @RequestMapping("complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "OK";
    }

    // 3.数组参数
    @RequestMapping("arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    // 4.集合参数
    @RequestMapping("listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "OK";
    }

    // 5.日期时间参数
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    // 6.json参数
    @RequestMapping("jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "OK";
    }

    // 7.路径参数
    @RequestMapping("/path/{id}")
    public String pathParam(@PathVariable Integer id) {
        System.out.println(id);
        return "OK";
    }

    @RequestMapping("/path/{id}/{name}")
    public String pathParam(@PathVariable Integer id, @PathVariable String name) {
        System.out.println(id);
        System.out.println(name);
        return "OK";
    }

}
