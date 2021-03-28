package com.sr.cloud.common.aop.privilege.aspect;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.common.aop.privilege.PrivilegeUtil;
import com.sr.cloud.common.aop.privilege.PrivilegeValidate;
import com.sr.cloud.base.dto.constant.Constants;
import com.sr.cloud.common.aop.privilege.enu.PrivilegeEnum;
import com.sr.cloud.common.aop.privilege.enu.PrivilegeTypeEnum;
import com.sr.cloud.common.util.SpringContextHolder;
import com.sr.cloud.user.api.UserServiceApi;
import com.sr.cloud.user.dto.PrivilegeValidateDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
@Order(3)
public class PrivilegeValidateAspect {
    /**
     * 权限检查实现类缓存
     */
    private static final Map<String, PrivilegeValidate> PRIVILEGE_CHECK_IMPL_CACHE = new ConcurrentHashMap<>();

    @Autowired
    private UserServiceApi userServiceApi;

    @Pointcut("@annotation(com.sr.cloud.common.aop.privilege.aspect.Privilege)")
    private void privilegePointcut() {
    }

    /**
     * 拦截controller, 校验是否登陆，没有登陆直接返回
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "privilegePointcut()")
    public Object privilegeValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            // 这个方法才是目标对象上有注解的方法
            Signature signature = proceedingJoinPoint.getSignature();//方法签名
            Method method = ((MethodSignature) signature).getMethod();
            Method realMethod = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());

            Privilege annotation = realMethod.getAnnotation(Privilege.class);

            // feign之间调用不需要鉴权
            HttpServletRequest request = getRequest();
            if (Constants.FEIGN_CONTINUE.equals(request.getHeader(Constants.FEIGN_KEY))) {
                return proceedingJoinPoint.proceed();
            }

            String userId = request.getHeader(Constants.USER_KEY);

            // 1.获取用户信息
            PrivilegeValidateDTO privilegeValidateDTO = CommonResponse.parseResponse(userServiceApi.queryPrivilegeById(Long.valueOf(userId)));
            if (privilegeValidateDTO == null) {
                // 用户未登陆
                log.info("privilege check user not login.");
                return CommonResponse.getNotLoginResult();
            }
            PrivilegeUtil.putPrivilege(privilegeValidateDTO);

            // 2.获取所有支持的权限
            Set<PrivilegeEnum> privilegeEnumSet = PrivilegeUtil.getPrivilegeSet();

            // 3. 是否超级管理员
            if (privilegeEnumSet.contains(PrivilegeEnum.SUPPER_ADMINISTRATOR)) {
                return proceedingJoinPoint.proceed();
            }

            // 4.检查类型（交集、并集）检查权限是否满足
            boolean result = checkPrivilegeExist(annotation.privileges(), annotation.type(), privilegeEnumSet);
            if (!result) {
                if (log.isInfoEnabled()) {
                    log.info("privilege check fail, privilege no match. userId:{}", privilegeValidateDTO.getUserId());
                }
                return CommonResponse.getPrivilegeErrorResult();
            }

            // 5. 执行自定义处理类
            PrivilegeValidate privilegeValidate = getPrivilegeValidateImpl(annotation.customPrivilegeValidate());
            if (privilegeValidate == null) {
                return proceedingJoinPoint.proceed();
            }
            try {
                result = privilegeValidate.checkPrivilege();
                if (!result) {
                    return CommonResponse.getPrivilegeErrorResult();
                }
            } catch (Exception e) {
                log.error("执行自定义鉴权失败", e);
                return CommonResponse.getPrivilegeErrorResult();
            }
        } catch (Throwable throwable) {
            log.error("鉴权失败", throwable);
            return CommonResponse.getFailedResult();
        } finally {
            PrivilegeUtil.clear();
        }

        return proceedingJoinPoint.proceed();
    }

    /**
     * 检查权限是否存在
     *
     * @param privileges    需要权限
     * @param type          权限类型
     * @param privilegeList 具有的权限
     * @return
     */
    private boolean checkPrivilegeExist(PrivilegeEnum[] privileges, PrivilegeTypeEnum type, Set<PrivilegeEnum> privilegeList) {
        if (privileges == null || privileges.length <= 0) {
            return true;
        }

        if (PrivilegeTypeEnum.ALL.equals(type)) {
            return privilegeList.containsAll(Arrays.asList(privileges));
        } else if (PrivilegeTypeEnum.ARBITRARILY.equals(type)) {
            for (PrivilegeEnum pri : privileges) {
                if (privilegeList.contains(pri)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取请求
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        Object obj = requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        if (obj instanceof HttpServletRequest) {
            request = (HttpServletRequest) obj;
        }
        return request;
    }

    /**
     * 获取 PrivilegeValidate 实现类
     *
     * @param privilegeValidateClass 实现类Class
     * @return
     */
    private PrivilegeValidate getPrivilegeValidateImpl(Class<? extends PrivilegeValidate> privilegeValidateClass) {
        if (privilegeValidateClass == null) {
            return null;
        }

        String name = privilegeValidateClass.getName();

        if (PRIVILEGE_CHECK_IMPL_CACHE.containsKey(name)) {
            return PRIVILEGE_CHECK_IMPL_CACHE.get(name);
        }

        try {
            PrivilegeValidate privilegeValidateImpl = SpringContextHolder.getBean(privilegeValidateClass);
            PRIVILEGE_CHECK_IMPL_CACHE.putIfAbsent(name, privilegeValidateImpl);
            return privilegeValidateImpl;
        } catch (Exception e) {
            log.error("can not find bean by class [{}]", name);
            log.error("getPrivilegeValidateImpl error.", e);
        }

        return null;
    }
}
