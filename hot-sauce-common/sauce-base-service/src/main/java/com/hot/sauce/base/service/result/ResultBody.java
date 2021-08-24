package com.hot.sauce.base.service.result;


import com.hot.sauce.base.service.exception.BaseErrorInfoInterface;
import com.hot.sauce.base.service.exception.ErrorCommonEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/24 14:24
 * @description ：返回数据包装
 */
@Getter
@Setter
@ToString
public class ResultBody {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;


    public ResultBody() {
    }

    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }


    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(ErrorCommonEnum.SUCCESS.getResultCode());
        rb.setMessage(ErrorCommonEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }



    /**
     * 失败
     */
    public static ResultBody error(BaseErrorInfoInterface errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error( String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(ErrorCommonEnum.SERVER_ERROR.getResultCode());
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

}
