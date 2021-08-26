package com.hot.sauce.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/25 16:58
 * @description ：
 */
@Configuration
public class RedissonConfig {
    /***
     * 创建RedissonClient对象
     *      创建锁、解锁
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        //创建Config
        Config config = new Config();
        //集群实现
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress(
                        "redis://r-bp1bz5kzij8dbbd2djpd.redis.rds.aliyuncs.com:6379"/*,
                        "redis://192.168.100.130:7002",
                        "redis://192.168.100.130:7003",
                        "redis://192.168.100.130:7004",
                        "redis://192.168.100.130:7005",
                        "redis://192.168.100.130:7006"*/)
                .setPassword("i1QdWm8AtOjm");
        config.setLockWatchdogTimeout(3*1000);

        //创建RedissonClient
        return Redisson.create(config);
    }
}
