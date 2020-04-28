package com.xmvp.xcynice.base;

import java.io.IOException;

/**
 * Description : XBaseException
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


public class XBaseException extends IOException {

    /**
     * 解析数据失败
     */
    public static final String PARSE_ERROR_MSG = "解析数据失败";
    /**
     * 网络问题
     */
    static final String BAD_NETWORK_MSG = "网络问题";
    /**
     * 连接错误
     */
    static final String CONNECT_ERROR_MSG = "连接错误";
    /**
     * 连接超时
     */
    static final String CONNECT_TIMEOUT_MSG = "连接超时";
    /**
     * 未知错误
     */
    static final String OTHER_MSG = "未知错误";

    private String errorMsg;
    private int errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public XBaseException(String message) {
        this.errorMsg = message;
    }

    XBaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public XBaseException(int errorCode, String message) {
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

}