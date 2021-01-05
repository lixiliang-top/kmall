package com.kgc.kmall.redisson.controller;

import com.kgc.kmall.redisson.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author 李锡良
 * @create 2021-01-04 14:31
 */
@RestController
public class RedissonController {

    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/test")
    public String testRedisson(){
        Jedis jedis = redisUtil.getJedis();

        String k = jedis.get("k");
        if (k==null){
            k = "1";
        }
        System.out.println("->" + k);
        jedis.set("k",(Integer.parseInt(k)+1)+"");

        jedis.close();
        return "success";
    }



}
