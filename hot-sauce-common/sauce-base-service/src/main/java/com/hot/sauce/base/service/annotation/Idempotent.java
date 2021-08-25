package com.hot.sauce.base.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/25 15:49
 * @description ：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    /**
     * 设置请求锁定时间,超时后自动释放锁
     *
     * @return
     */
    int lockTime() default 3;
}
