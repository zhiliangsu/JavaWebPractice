package com.hitech.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// @Aspect
public class TimeAspect {

    // @Around("execution(* com.hitech.service.*.*(..))")
    @Around("com.hitech.aop.MyAspect1.pt()") // 引用MyAspect1切面类中的切入点表达式
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 1.记录方法执行开始时间
        long begin = System.currentTimeMillis();

        // 2.调用原始方法
        Object result = proceedingJoinPoint.proceed();

        // 3.记录方法执行结束时间
        long end = System.currentTimeMillis();

        // 4.计算执行方法耗时
        log.info(proceedingJoinPoint.getSignature() + "执行耗时: {}ms", end - begin);

        // 5.返回原始方法的执行返回结果
        return result;
    }
}
