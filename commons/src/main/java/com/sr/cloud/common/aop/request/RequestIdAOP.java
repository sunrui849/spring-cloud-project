package com.sr.cloud.common.aop.request;


import com.sr.cloud.common.constant.Constants;
import com.sr.cloud.common.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: wufaping
 * @date: 2020/3/24 10:18
 * @description: 请求id
 */
@Order(1)
@Component
@Aspect
@Slf4j
public class RequestIdAOP {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(com.jd.yoda.fire.rta.common.annotation.MethodLog)"
    )
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!(joinPoint.getSignature() instanceof MethodSignature)) {
            log.error("RequestIdAOP joinPoint.getSignature() instanceof MethodSignature error!");
            return joinPoint.proceed();
        }
        MDC.clear();
        MDC.put(Constants.REQUEST_ID, UUIDUtil.generateUUID());

        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("RequestIdAOP 调用出错 ", e);
            throw e;
        } finally {
            MDC.clear();
        }
        return result;
    }
}
