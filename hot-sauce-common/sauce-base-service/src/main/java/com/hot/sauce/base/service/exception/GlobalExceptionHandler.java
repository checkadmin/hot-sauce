package com.hot.sauce.base.service.exception;

import com.hot.sauce.base.service.result.ResultBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/24 15:11
 * @description ：全局异常捕获
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 参数与校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value =IllegalArgumentException.class)
    @ResponseBody
    public ResultBody exceptionHandler(IllegalArgumentException e){
        log.error("参数校验异常！:",e);
        return ResultBody.error(ErrorCommonEnum.SERVER_ERROR.getResultCode(),e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(Exception e){
        log.error("未知异常！原因是:",e);
        return ResultBody.error(ErrorCommonEnum.SERVER_ERROR);
    }
}
