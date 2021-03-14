package com.sr.cloud.common.aop.privilege.aspect;


import com.sr.cloud.common.aop.privilege.PrivilegeValidate;
import com.sr.cloud.common.aop.privilege.enu.PrivilegeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Privilege {
    /**
     * 校验权限串集合
     *
     */
    PrivilegeEnum[] privileges() default {};

    /**
     * 自定义权限校验
     *
     */
    Class<? extends PrivilegeValidate> customPrivilegeValidate() default DefaultValidate.class;
}
