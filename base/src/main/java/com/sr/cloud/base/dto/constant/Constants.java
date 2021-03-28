package com.sr.cloud.base.dto.constant;

public class Constants {
    /**
     * 请求uuid常量
     */
    public static final String REQUEST_ID = "requestId";
    /**
     * feign调用请求头
     */
    public static final String FEIGN_KEY = "FEIGN";
    /**
     * feign调用忽略鉴权value
     */
    public static final String FEIGN_CONTINUE = "CONTINUE";
    /**
     * 用户ID请求头
     */
    public static final String USER_KEY = "USER_ID";
    /**
     * token请求头
     */
    public static final String TOKEN_KEY = "TOKEN";

    /**
     * 私有化构造函数
     */
    private Constants() {
    }
}
