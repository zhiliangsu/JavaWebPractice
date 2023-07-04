package com.hitech.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// @Aspect
public class MyAspect1 {
    // 切入点方法（公共的切入点表达式）
    @Pointcut("execution(* com.hitech.service.impl.DeptServiceImpl.*(..))")
    // private void pt(){};
    public void pt() {};

    // 前置通知（引用切入点）
    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("before...");
    }

    // 环绕通知
    @Around("pt()")
    public Object aroundBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before...");

        // 2.调用原始方法
        Object result = proceedingJoinPoint.proceed();

        // 原始方法在执行时：发生异常
        // 后续代码不在执行
        log.info("around after...");
        return result;
    }

    // 后置通知(最终通知)
    @After("pt()")
    public void after(JoinPoint joinPoint) {
        log.info("after...");
    }

    // 返回后通知（程序在正常执行的情况下，会执行的后置通知）
    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning...");
    }


    // 异常通知（程序在出现异常的情况下，执行的后置通知）
    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing...");
    }


}
