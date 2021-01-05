package com.kgc.kmall.redisson.config;

import com.kgc.kmall.redisson.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李锡良
 * @create 2020-12-31 15:13
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host:disabled}")
    private String host;
    @Value("${spring.redis.port:0}")
    private int port;
    @Value("${spring.redis.database:0}")
    private int database;

    @Bean
    public RedisUtil getRedisUtil(){
        if (host.equals("disabled")){
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initPool(host,port,database);
        return redisUtil;
    }

}
