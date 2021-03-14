package com.sr.cloud.business.controller;

import com.sr.cloud.business.api.BusinessApi;
import com.sr.cloud.common.aop.privilege.aspect.Privilege;
import com.sr.cloud.user.api.UserServiceApi;
import com.sr.cloud.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/business")
public class BusinessController implements BusinessApi {

    @Autowired
    private UserServiceApi userServiceApi;

    @Override
    @Privilege
    @RequestMapping("/doSomeThing")
    public String doSomeThing(Long userId) {
        User user = userServiceApi.queryById(userId);
        return UUID.randomUUID().toString() + user.getUserName();
    }
}
