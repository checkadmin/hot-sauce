package com.hot.sauce.base.service.exception;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/24 14:28
 * @description ：异常，常量编码
 */
public enum ErrorCommonEnum  implements BaseErrorInfoInterface{
    /**
     * 成功
     */
    SUCCESS("0", "成功!"),
    /**
     * 服务器内部错误
     */
    SERVER_ERROR("-1", "服务器内部错误!"),
    ;

    ErrorCommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;



    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
