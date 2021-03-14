package com.sr.cloud.business.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "business-service-provider")
public interface BusinessApi {

    @RequestMapping(value = "/business/doSomeThing",method = RequestMethod.GET)
    String doSomeThing(@RequestParam("userId") Long userId);
}
