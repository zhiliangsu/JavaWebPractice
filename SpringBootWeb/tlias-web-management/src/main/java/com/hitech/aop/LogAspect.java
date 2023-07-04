package com.hitech.aop;

import com.alibaba.fastjson.JSONObject;
import com.hitech.mapper.OperateLogMapper;
import com.hitech.pojo.OperateLog;
import com.hitech.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.hitech.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 1.获取当前操作人
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");

        // 2.获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 3.获取操作全类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        // 4.获取操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        // 5.获取操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 调用原始方法,并返回执行结果
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        // 7.获取操作耗时
        long costTime = end - begin;
        // 6.获取操作方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 插入操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className,
                methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        return result;
    }
}
