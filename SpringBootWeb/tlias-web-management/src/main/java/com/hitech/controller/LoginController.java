package com.hitech.controller;

import com.hitech.pojo.Emp;
import com.hitech.pojo.Result;
import com.hitech.service.EmpService;
import com.hitech.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        // 调用业务层: 登录功能
        log.info("用户登录操作: {}", emp);
        Emp e = empService.login(emp);
        // 判断: 用户是否存在
        if (e != null) {
            // 自定义信息
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("name", e.getName());
            // 使用JWT工具类, 生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

}
