package com.kgc.kmall.user.service;

import com.alibaba.fastjson.JSON;
import com.kgc.kmall.bean.*;
import com.kgc.kmall.config.RedissonConfig;
import com.kgc.kmall.service.SkuService;
import com.kgc.kmall.user.mapper.PmsSkuAttrValueMapper;
import com.kgc.kmall.user.mapper.PmsSkuImageMapper;
import com.kgc.kmall.user.mapper.PmsSkuInfoMapper;
import com.kgc.kmall.user.mapper.PmsSkuSaleAttrValueMapper;
import com.kgc.kmall.util.RedisUtil;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @author 李锡良
 * @create 2020-12-23 19:22
 */
@Component
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Resource
    PmsSkuImageMapper pmsSkuImageMapper;
    @Resource
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Resource
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    //首先导入redis工具类
    @Resource
    RedisUtil redisUtil;

    @Resource
    RedissonClient redissonClient;
    @Override
    public String saveSkuInfo(PmsSkuInfo skuInfo) {
        pmsSkuInfoMapper.insert(skuInfo);
        Long skuInfoId = skuInfo.getId();
        for (PmsSkuImage pmsSkuImage : skuInfo.getSkuImageList()) {
            pmsSkuImage.setSkuId(skuInfoId);
            pmsSkuImageMapper.insert(pmsSkuImage);
        }
        for (PmsSkuAttrValue pmsSkuAttrValue : skuInfo.getSkuAttrValueList()) {
            pmsSkuAttrValue.setSkuId(skuInfoId);
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuInfo.getSkuSaleAttrValueList()) {
            pmsSkuSaleAttrValue.setSkuId(skuInfoId);
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }
        return "success";
    }

    @Override
    public PmsSkuInfo selectBySkuId(Long id) {
        PmsSkuInfo pmsSkuInfo =null;
        Jedis jedis = redisUtil.getJedis();
        String key = "sku:"+id+":info";
        String s = jedis.get(key);
        if (s!=null){
            //缓存中查询数据
            pmsSkuInfo = JSON.parseObject(s, PmsSkuInfo.class);
            jedis.close();
            return pmsSkuInfo;
        }else{
                //如果缓存中和没有数据，从数据库查询数据，并写入redis
                //先从数据库中查询出来
            Lock lock = redissonClient.getLock("lock");// 声明锁
            lock.lock();//上锁
            try {
                pmsSkuInfo = pmsSkuInfoMapper.selectByPrimaryKey(id);
                if (pmsSkuInfo!=null){
                    //吧查询出来的json格式转换位string
                    String string = JSON.toJSONString(pmsSkuInfo);
                    //有效期随机，放值缓存雪崩
                    Random random = new Random();
                    int i = random.nextInt(10);
                    //把数据写入redis
                    jedis.setex(key,i*60*1000,string);
                }else{
                    jedis.setex(key,5*60*1000, "empty");
                }
                jedis.close();
               // 写完缓存后要删除分布式锁
            }finally {
                lock.unlock();
            }
        }
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> selectBySpuId(Long spuId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectBySpuId(spuId);
        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectByExample(null);
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            PmsSkuAttrValueExample example = new PmsSkuAttrValueExample();
            PmsSkuAttrValueExample.Criteria criteria = example.createCriteria();
            criteria.andSkuIdEqualTo(pmsSkuInfo.getId());
            List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.selectByExample(example);
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);
        }
        return pmsSkuInfos;
    }
}
