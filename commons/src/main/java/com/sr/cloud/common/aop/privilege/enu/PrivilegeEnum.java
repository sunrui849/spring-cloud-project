package com.sr.cloud.common.aop.privilege.enu;

public enum PrivilegeEnum {
    // 超管权限
    SUPPER_ADMINISTRATOR("SUPPER_ADMINISTRATOR","超管权限"),

    // 菜单功能类权限
    GENERAL_SITUATION("GENERAL_SITUATION", "概况"),
    ;

    private String code;
    private String name;

    PrivilegeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PrivilegeEnum selectByCode(String code) {
        PrivilegeEnum[] values = values();
        for (PrivilegeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
