package com.hitech.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// @Aspect
@Order(2)
public class MyAspect2 {
    // 切入点方法（公共的切入点表达式）
    @Pointcut("execution(* com.hitech.service.impl.DeptServiceImpl.*(..))")
    // private void pt(){};
    public void pt() {}

    // 前置通知（引用切入点）
    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("before...2");
    }


    // 后置通知(最终通知)
    @After("pt()")
    public void after(JoinPoint joinPoint) {
        log.info("after...2");
    }


}
