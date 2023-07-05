package com.hitech.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hitech.pojo.Result;
import com.hitech.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    // 目标资源方法执行前执行。 返回true：放行    返回false：不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);

        // 2.判断url是否包含login,如果包含,说明是登录操作,放行
        if (url.contains("login")) {
            log.info("登录操作: 放行...");
            return true;
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
            return false;
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
            return false;
        }

        // 6.如果token存在且解析没有问题,放行
        log.info("令牌合法, 放行");
        return true;
    }

    // 目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    // 视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
