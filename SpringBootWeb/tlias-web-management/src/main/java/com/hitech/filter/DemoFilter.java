package com.hitech.filter;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter.super.init(filterConfig);
        System.out.println("init 初始化方法执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到了请求...放行前的逻辑");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Demo 拦截到了请求...放行后的逻辑");
    }

    @Override
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
        // Filter.super.destroy();
    }
}
