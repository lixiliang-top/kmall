package com.kgc.kmall.config;

import com.kgc.kmall.util.RedisUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李锡良
 * @create 2021-01-04 15:52
 */
@Configuration
public class RedissonConfig {


    @Value("${spring.redis.host:disabled}")
    private String host;
    @Value("${spring.redis.port:0}")
    private int port;
    @Value("${spring.redis.database:0}")
    private int database;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

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
