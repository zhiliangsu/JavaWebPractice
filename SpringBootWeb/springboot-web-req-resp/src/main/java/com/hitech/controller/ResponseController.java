package com.hitech.controller;

import com.hitech.pojo.Address;
import com.hitech.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
// @Controller
// @ResponseBody
public class ResponseController {
    /* @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World ~");
        return "Hello World ~";
    }

    @RequestMapping("/getAddr")
    public Address getAddr() {
        Address address = new Address();
        address.setProvince("广东");
        address.setCity("广州");

        System.out.println(address);
        return address;
    }

    @RequestMapping("/listAddr")
    public List<Address> listAddr() {
        List<Address> list = new ArrayList<>();

        Address add1 = new Address();
        add1.setProvince("广东");
        add1.setCity("广州");

        Address add2 = new Address();
        add2.setProvince("广东");
        add2.setCity("深圳");

        list.add(add1);
        list.add(add2);

        System.out.println(list);
        return list;
    } */

    @RequestMapping("/hello")
    public Result hello() {
        System.out.println("Hello World ~");
        // return new Result(1, "success", "Hello World ~");
        return Result.success("Hello World ~");
    }

    @RequestMapping("/getAddr")
    public Result getAddr() {
        Address address = new Address();
        address.setProvince("广东");
        address.setCity("广州");

        System.out.println(address);
        return Result.success(address);
    }

    @RequestMapping("/listAddr")
    public Result listAddr() {
        List<Address> list = new ArrayList<>();

        Address add1 = new Address();
        add1.setProvince("广东");
        add1.setCity("广州");

        Address add2 = new Address();
        add2.setProvince("广东");
        add2.setCity("深圳");

        list.add(add1);
        list.add(add2);

        System.out.println(list);
        return Result.success(list);
    }
}
