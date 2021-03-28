package com.sr.cloud.user.controller;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.user.api.UserServiceApi;
import com.sr.cloud.user.dto.PrivilegeDTO;
import com.sr.cloud.user.dto.PrivilegeValidateDTO;
import com.sr.cloud.user.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController implements UserServiceApi {

    @Override
    @RequestMapping("/queryById")
    public CommonResponse<User> queryById(Long id) {
        log.info("user 004, id:{}", id);
        if ("1".equals(String.valueOf(id))) {
            return CommonResponse.getSuccessResult(new User(id, "孙瑞", 25, "ee@qq.com"));
        } else if ("2".equals(String.valueOf(id))) {
            return CommonResponse.getSuccessResult(new User(id, "孙瑞22", 38, "ee@333qq.com"));
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse<String> login(String userName, String password) {
        if ("sunrui".equals(userName) && "123".equals(password)) {
            return CommonResponse.getSuccessResult(UUID.randomUUID().toString());
        }
        return CommonResponse.getFailedResult();
    }

    @Override
    @RequestMapping(value = "/queryPrivilegeById", method = RequestMethod.GET)
    public CommonResponse<PrivilegeValidateDTO> queryPrivilegeById(Long id) {
        PrivilegeValidateDTO privilegeValidateDTO = new PrivilegeValidateDTO();
        privilegeValidateDTO.setUserId("1");
        privilegeValidateDTO.setUserName("sunrui");
        List<PrivilegeDTO> list = new ArrayList<>();
        PrivilegeDTO privilegeDTO = new PrivilegeDTO();
        privilegeDTO.setPrivilegeCode("SUPPER_ADMINISTRATOR");
        list.add(privilegeDTO);
        privilegeValidateDTO.setPrivilegeDTOList(list);
        return CommonResponse.getSuccessResult(privilegeValidateDTO);
    }
}
