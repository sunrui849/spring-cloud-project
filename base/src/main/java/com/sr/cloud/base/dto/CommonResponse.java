package com.sr.cloud.base.dto;

import com.sr.cloud.base.dto.enu.StatusCodeEnum;
import org.slf4j.MDC;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {
    /**
     * 请求ID
     */
    public static final String REQUEST_ID_KEY = "requestId";

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 状态编码
     */
    private String code;
    /**
     * 编码对应中文
     */
    private String message;
    /**
     * 对应数据
     */
    private T data;
    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 构造方法
     *
     * @param statusCode 状态枚举
     * @param data       对应数据
     */
    protected CommonResponse(StatusCodeEnum statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
        this.setRequestId(MDC.get(REQUEST_ID_KEY));
    }

    /**
     * 构造方法
     */
    private CommonResponse() {

    }

    /**
     * 返回数据+返回码&描述信息
     *
     * @param data
     * @param statusCode
     * @return
     */
    public static <T> CommonResponse<T> getInstance(T data, StatusCodeEnum statusCode) {
        CommonResponse<T> confirmBean = new CommonResponse<T>();
        confirmBean.setData(data);
        confirmBean.setCode(statusCode.getCode());
        confirmBean.setMessage(statusCode.getMessage());
        confirmBean.setRequestId(MDC.get(REQUEST_ID_KEY));
        return confirmBean;
    }

    /**
     * 返回数据+返回码&描述信息
     *
     * @param statusCode
     * @return
     */
    public static <T> CommonResponse<T> getInstance(StatusCodeEnum statusCode) {
        CommonResponse<T> confirmBean = new CommonResponse<T>();
        confirmBean.setCode(statusCode.getCode());
        confirmBean.setMessage(statusCode.getMessage());
        confirmBean.setRequestId(MDC.get(REQUEST_ID_KEY));
        return confirmBean;
    }

    /**
     * 获取成功的结果
     *
     * @return
     */
    public static <T> CommonResponse<T> getSuccessResult() {
        return getInstance(StatusCodeEnum.SUCCESS);
    }

    /**
     * 获取成功的结果
     *
     * @return
     */
    public static <T> CommonResponse<T> getSuccessResult(T data) {
        return getInstance(data, StatusCodeEnum.SUCCESS);
    }

    /**
     * 获取错误的结果
     *
     * @return
     */
    public static <T> CommonResponse<T> getFailedResult() {
        return getInstance(StatusCodeEnum.FAILED);
    }

    /**
     * 权限不足
     *
     * @return
     */
    public static <T> CommonResponse<T> getPrivilegeErrorResult() {
        return getInstance(StatusCodeEnum.PRIVILEGE_ERROR);
    }

    /**
     * 获取非法参数 结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> getIllegalArguResult() {
        return getInstance(StatusCodeEnum.PARAM_ERROR);
    }

    /**
     * 获取未知错误的结果
     *
     * @return
     */
    public static <T> CommonResponse<T> getUnkownErrorResult() {
        return getInstance(StatusCodeEnum.FAILED);
    }


    /**
     * 赋值状态
     *
     * @param statusCode 状态枚举
     */
    public CommonResponse<T> fillStatusCode(StatusCodeEnum statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        return this;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return StatusCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String retMessage) {
        this.message = retMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "ClientUnifiedResult [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}
