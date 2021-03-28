package com.sr.cloud.business.controller;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.business.api.BusinessApi;
import com.sr.cloud.business.entity.TeacherEntity;
import com.sr.cloud.business.service.TeacherService;
import com.sr.cloud.common.aop.privilege.aspect.Privilege;
import com.sr.cloud.user.api.UserServiceApi;
import com.sr.cloud.user.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/business")
public class BusinessController implements BusinessApi, Serializable {

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private TeacherService teacherService;

    @Override
    @Privilege
    @RequestMapping("/doSomeThing")
    public CommonResponse<String> doSomeThing(Long userId) {
        User user = CommonResponse.parseResponse(userServiceApi.queryById(userId));
        TeacherEntity teacherPO = teacherService.queryById(3L);
        log.info("teacher : {}", teacherPO);
        return CommonResponse.getSuccessResult(UUID.randomUUID().toString()+user.getUserName());
    }

    @Override
    @Privilege
    @RequestMapping("/doSomeThing2")
    public CommonResponse<String> doSomeThing2(Long userId) {
        User user = CommonResponse.parseResponse(userServiceApi.queryById(userId));
        TeacherEntity teacherPO = teacherService.queryById2(3L);
        log.info("teacher : {}", teacherPO);
        return CommonResponse.getSuccessResult(UUID.randomUUID().toString()+user.getUserName());
    }
}
