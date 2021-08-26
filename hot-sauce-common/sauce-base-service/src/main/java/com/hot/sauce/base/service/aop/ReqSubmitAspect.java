package com.hot.sauce.base.service.aop;


import com.alibaba.fastjson.JSON;
import com.hot.sauce.base.service.annotation.Idempotent;
import com.hot.sauce.base.service.result.ResultBody;
import com.hot.sauce.base.service.util.IpUtil;
import com.hot.sauce.common.redis.util.RedisLock;
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
import java.util.UUID;

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
    private RedisLock redisLock;


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
        String clientId = UUID.randomUUID().toString();
        int lockSeconds = ide.lockTime()*1000;
        boolean lock = redisLock.tryLock(key, clientId, lockSeconds);
        if (lock){
            Object result;
            try {
                result = joinPoint.proceed();
            }finally {
                // 解锁
                redisLock.releaseLock(key, clientId);
                log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }
            return result;
        }else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = [{}]", key);
            return ResultBody.error("重复请求，请稍后再试!");
        }
    }
}
