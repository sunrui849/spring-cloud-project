package com.sr.cloud.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class FeignRequestConfig implements RequestInterceptor {
    private static final String FEIGN_KEY = "FEIGN";
    /**
     * feign之间调用去掉鉴权，需要在鉴权aop上增加该逻辑判断
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        template.header(FEIGN_KEY, "CONTINUE");
        if (headerNames == null) {
            return;
        }
        //处理上游请求头信息，传递时继续携带
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String values = request.getHeader(name);
            template.header(name, values);
        }
    }
}