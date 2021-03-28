package com.sr.cloud.common.aop.privilege;


import com.sr.cloud.common.aop.privilege.enu.PrivilegeEnum;
import com.sr.cloud.user.dto.PrivilegeValidateDTO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class PrivilegeUtil {
    private static final ThreadLocal<PrivilegeValidateDTO> THREAD_LOCAL = new ThreadLocal();

    public static void putPrivilege(PrivilegeValidateDTO privilegeValidateDTO){
        THREAD_LOCAL.set(privilegeValidateDTO);
    }

    private PrivilegeUtil(){}

    /**
     * 获取所有权限集合
     * @return
     */
    public static Set<PrivilegeEnum> getPrivilegeSet(){
        PrivilegeValidateDTO privilege = THREAD_LOCAL.get();
        if (privilege == null || CollectionUtils.isEmpty(privilege.getPrivilegeDTOList())){
            return Collections.EMPTY_SET;
        }
        return privilege.getPrivilegeDTOList().stream().map(e -> PrivilegeEnum.selectByCode(e.getPrivilegeCode())).collect(Collectors.toSet());
    }

    public static void clear(){
        THREAD_LOCAL.remove();
    }

}
