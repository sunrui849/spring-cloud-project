package com.sr.cloud.common.aop.privilege.aspect;


import com.sr.cloud.common.aop.privilege.PrivilegeValidate;
import com.sr.cloud.common.aop.privilege.enu.PrivilegeEnum;
import com.sr.cloud.common.aop.privilege.enu.PrivilegeTypeEnum;
import com.sr.cloud.common.aop.privilege.impl.DefaultValidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
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

    /**
     * 校验权限串组合方式
     *
     * @return
     */
    PrivilegeTypeEnum type() default PrivilegeTypeEnum.ARBITRARILY;
}
