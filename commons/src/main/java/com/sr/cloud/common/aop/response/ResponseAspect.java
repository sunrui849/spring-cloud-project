package com.sr.cloud.common.aop.response;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.base.dto.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Deprecated
@Aspect
//@Component
@Slf4j
@Order(2)
public class ResponseAspect {

    @Pointcut("execution(public * com.sr.cloud..**.controller..*.*(..))")
    private void responsePointcut() {
    }

    /**
     * 拦截controller, 校验是否登陆，没有登陆直接返回
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "responsePointcut()")
    public Object privilegeValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // feign之间调用不封装
        HttpServletRequest request = getRequest();
        if (Constants.FEIGN_CONTINUE.equals(request.getHeader(Constants.FEIGN_KEY))) {
            return proceedingJoinPoint.proceed();
        }

        try {
            // 这个方法才是目标对象上有注解的方法
            Signature signature = proceedingJoinPoint.getSignature();//方法签名
            Method method = ((MethodSignature) signature).getMethod();
            Method realMethod = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());

            if (CommonResponse.class.equals(realMethod.getReturnType())) {
                return proceedingJoinPoint.proceed();
            } else {
                return CommonResponse.getSuccessResult(proceedingJoinPoint.proceed());
            }
        } catch (Throwable throwable) {
            log.error("controller执行失败", throwable);
        }
        return CommonResponse.getFailedResult();
    }

    /**
     * 获取请求
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        Object obj = requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        if (obj instanceof HttpServletRequest) {
            request = (HttpServletRequest) obj;
        }
        return request;
    }
}
