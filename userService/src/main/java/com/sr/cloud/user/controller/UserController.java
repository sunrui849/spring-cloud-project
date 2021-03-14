package com.sr.cloud.user.controller;

import com.sr.cloud.user.api.UserServiceApi;
import com.sr.cloud.user.dto.PrivilegeValidateDTO;
import com.sr.cloud.user.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController implements UserServiceApi {

    @Override
    @RequestMapping("/queryById")
    public User queryById(Long id) {
        System.out.println("user 004, id" + id);
        if ("1".equals(String.valueOf(id))) {
            return new User(id, "孙瑞", 25, "ee@qq.com");
        } else if ("2".equals(String.valueOf(id))) {
            return new User(id, "孙瑞22", 38, "ee@333qq.com");
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password) {
        if ("sunrui".equals(userName) && "123".equals(password)) {
            return UUID.randomUUID().toString();
        }
        return "password error.";
    }

    @Override
    public PrivilegeValidateDTO queryPrivilegeById(Long id) {
        return null;
    }
}
