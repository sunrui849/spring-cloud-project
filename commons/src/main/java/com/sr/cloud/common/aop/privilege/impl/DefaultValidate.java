package com.sr.cloud.common.aop.privilege.impl;

import com.sr.cloud.common.aop.privilege.PrivilegeValidate;
import org.springframework.stereotype.Component;

@Component
public class DefaultValidate implements PrivilegeValidate {
    @Override
    public boolean checkPrivilege() {
        return true;
    }
}
