package com.dhr.entity;

public class ResponseInfo {
    public static final int ERROR_CODE_SUCCESS = 0;
    public static final int ERROR_CODE_MAPPING_FAILED = 100;
    public static final int ERROR_CODE_BUSINESS_FAILED = 130;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 数据
     */
    private Object data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
