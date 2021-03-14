package com.sr.cloud.business.api;


import com.sr.cloud.base.dto.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "business-service-provider")
public interface BusinessApi {

    @RequestMapping(value = "/business/doSomeThing",method = RequestMethod.GET)
    CommonResponse<String> doSomeThing(@RequestParam("userId") Long userId);
}
