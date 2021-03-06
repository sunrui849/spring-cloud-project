package com.sr.cloud.user.api;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.user.dto.PrivilegeValidateDTO;
import com.sr.cloud.user.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "user-service-provider")
public interface UserServiceApi {

    @RequestMapping(value = "/user/queryById",method = RequestMethod.GET)
    CommonResponse<User> queryById(@RequestParam("id") Long id);

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    CommonResponse<String> login(@RequestParam("userName")String userName, @RequestParam("password")String password);

    @RequestMapping(value = "/user/queryPrivilegeById",method = RequestMethod.GET)
    CommonResponse<PrivilegeValidateDTO>  queryPrivilegeById(@RequestParam("id") Long id);
}
