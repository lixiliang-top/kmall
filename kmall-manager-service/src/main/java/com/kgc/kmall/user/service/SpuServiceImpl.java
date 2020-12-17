package com.kgc.kmall.user.service;

import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductInfoExample;
import com.kgc.kmall.service.SpuService;
import com.kgc.kmall.user.mapper.PmsProductInfoMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:21
 */
@Component
@Service
public class SpuServiceImpl implements SpuService {

    @Resource
    PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public List<PmsProductInfo> selectPmsProductCatalog3Id(Long catalog3Id) {
        PmsProductInfoExample example = new PmsProductInfoExample();
        PmsProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCatalog3IdEqualTo(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectByExample(example);
        return pmsProductInfos;
    }
}
