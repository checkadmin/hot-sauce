package com.hot.sauce.common.redis.util;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/26 9:36
 * @description ：
 */
@Component
public class RedisLock {
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    // 当前设置 过期时间单位, EX = seconds; PX = milliseconds
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    //lua
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     * <p>
     * 支持重复，线程安全
     *
     * @param lockKey  加锁键
     * @param clientId 加锁客户端唯一标识(采用UUID)
     * @param seconds  锁过期时间
     * @return
     */
    public boolean tryLock(String lockKey, String clientId, long seconds) {
        // lettuce连接包下 redis 单机模式
        return Boolean.TRUE.equals(redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Object nativeConnection = redisConnection.getNativeConnection();
            RedisSerializer<String> stringRedisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
            byte[] keyByte = stringRedisSerializer.serialize(lockKey);
            byte[] valueByte = stringRedisSerializer.serialize(clientId);

            // lettuce连接包下 redis 单机模式
            if (nativeConnection instanceof RedisAsyncCommands) {
                RedisAsyncCommands connection = (RedisAsyncCommands) nativeConnection;
                RedisCommands commands = connection.getStatefulConnection().sync();
                String result = commands.set(keyByte, valueByte, SetArgs.Builder.nx().ex(seconds));
                if (LOCK_SUCCESS.equals(result)) {
                    return true;
                }
            }
            // lettuce连接包下 redis 集群模式
            if (nativeConnection instanceof RedisAdvancedClusterAsyncCommands) {
                RedisAdvancedClusterAsyncCommands connection = (RedisAdvancedClusterAsyncCommands) nativeConnection;
                RedisAdvancedClusterCommands commands = connection.getStatefulConnection().sync();
                String result = commands.set(keyByte, valueByte, SetArgs.Builder.nx().ex(seconds));
                if (LOCK_SUCCESS.equals(result)) {
                    return true;
                }
            }
            return false;
        }));
    }

    /**
     * 与 tryLock 相对应，用作释放锁
     *
     * @param lockKey
     * @param clientId
     * @return
     */
    public boolean releaseLock(String lockKey, String clientId) {
        DefaultRedisScript<Integer> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(RELEASE_LOCK_SCRIPT);
        redisScript.setResultType(Integer.class);
        Object execute = redisTemplate.execute((RedisConnection connection) -> connection.eval(
                RELEASE_LOCK_SCRIPT.getBytes(),
                ReturnType.INTEGER,
                1,
                lockKey.getBytes(),
                clientId.getBytes()));
        if (RELEASE_SUCCESS.equals(execute)) {
            return true;
        }
        return false;
    }
}
