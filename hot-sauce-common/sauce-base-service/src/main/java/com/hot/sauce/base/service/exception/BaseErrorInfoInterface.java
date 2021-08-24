package com.hot.sauce.base.service.exception;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/24 14:26
 * @description ：异常处理，基础接口
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     * @return code
     */
    String getResultCode();

    /**
     * 错误描述
     * @return msg
     */
    String getResultMsg();
}
