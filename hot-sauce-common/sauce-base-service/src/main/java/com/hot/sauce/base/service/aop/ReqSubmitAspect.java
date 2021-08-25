package com.hot.sauce.base.service.aop;


import com.alibaba.fastjson.JSON;
import com.hot.sauce.base.service.annotation.Idempotent;
import com.hot.sauce.base.service.result.ResultBody;
import com.hot.sauce.base.service.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/25 15:52
 * @description ：重复提交处理业务逻辑实现
 */
@Slf4j
@Aspect
@Component
public class ReqSubmitAspect {
    @Autowired
    private RedissonClient redissonClient;


    @Around("@annotation(com.hot.sauce.base.service.annotation.Idempotent)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent ide = method.getAnnotation(Idempotent.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Assert.notNull(request, "request can not null");
        //目标类、方法
        String path = request.getServletPath();
        String ip = IpUtil.getIP(request);
        String params = "";
        Object[] args = joinPoint.getArgs();
        if (!ObjectUtils.isEmpty(args)){
            params = JSON.toJSONString(args);
        }
        String ipKey = String.format("%s#%s#%s", ip, path, params);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d", ip, hashCode);
        boolean lock = redissonClient.getLock(key).tryLock(ide.lockTime(), TimeUnit.SECONDS);
        if (lock){
            Object result;
            try {
                result = joinPoint.proceed();
            }finally {
                redissonClient.getLock(key).unlock();
                log.info("分布式锁释放成功，key={}",key);
            }
            return result;
        }else {
            log.info("分布式锁获取失败，key={}",key);
            return ResultBody.error("请求不要太频繁");
        }
    }
}
