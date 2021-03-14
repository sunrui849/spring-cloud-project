package com.sr.cloud.common.aop.privilege;


import com.sr.cloud.user.dto.PrivilegeValidateDTO;

public class PrivilegeUtil {
    private static final ThreadLocal<PrivilegeValidateDTO> threadLocal = new ThreadLocal();

    public static void putPrivilege(PrivilegeValidateDTO privilegeValidateDTO){
        threadLocal.set(privilegeValidateDTO);
    }

    public static void clear(){
        threadLocal.remove();
    }

}
