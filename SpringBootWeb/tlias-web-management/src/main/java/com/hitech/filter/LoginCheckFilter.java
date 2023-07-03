package com.hitech.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hitech.pojo.Result;
import com.hitech.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
// @WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);

        // 2.判断url是否包含login,如果包含,说明是登录操作,放行
        if (url.contains("login")) {
            log.info("登录操作: 放行...");
            filterChain.doFilter(request, response);
            return;
        }

        // 3.如果请求url不包含login,则需要通过请求头获取token
        String token = request.getHeader("token");
        log.info("获取到的令牌为: {}", token);

        // 4. 校验token是否存在,如果不存在,返回错误结果(NOT_LOGIN)
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(notLogin);
            return;
        }

        // 5.如果token存在,则需要解析token,如果解析失败,返回错误结果(NOT_LOGIN)
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(notLogin);
            return;
        }

        // 6.如果token存在且解析没有问题,放行
        log.info("令牌合法, 放行");
        filterChain.doFilter(request, response);

    }
}
