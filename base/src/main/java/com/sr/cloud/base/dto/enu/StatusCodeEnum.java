package com.sr.cloud.base.dto.enu;

public enum StatusCodeEnum {

    /**
     * 全局状态码
     **/
    SUCCESS("200", "成功"),

    FAILED("5000", "未知错误"),

    PRIVILEGE_ERROR("4001", "权限不足"),

    EMPTY_DATA("4004", "响应数据为空"),

    TOKEN_EMPTY("4005", "token不存在"),

    TOKEN_ERROR("4005", "token错误或过期"),

    PARAM_ERROR("4000", "请求参数非法");

    /**
     * code码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 构造器
     *
     * @param code
     * @param message
     */
    StatusCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
