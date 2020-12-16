package com.kgc.kmall.user.service;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.service.CatalogService;
import com.kgc.kmall.user.mapper.PmsBaseCatalog1Mapper;
import com.kgc.kmall.user.mapper.PmsBaseCatalog2Mapper;
import com.kgc.kmall.user.mapper.PmsBaseCatalog3Mapper;
import com.sun.org.apache.xml.internal.resolver.CatalogManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-16 15:19
 */
@Component
@Service
public class CatalogServiceImpl implements CatalogService {

    @Resource
    PmsBaseCatalog1Mapper catalog1Mapper;

    @Resource
    PmsBaseCatalog2Mapper catalog2Mapper;

    @Resource
    PmsBaseCatalog3Mapper catalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> selectPmsBaseCateLog1All() {
        PmsBaseCatalog1Example example = new PmsBaseCatalog1Example();
        PmsBaseCatalog1Example.Criteria criteria = example.createCriteria();
        List<PmsBaseCatalog1> pmsBaseCatalog1s = catalog1Mapper.selectByExample(example);
        return pmsBaseCatalog1s;
    }

    @Override
    public List<PmsBaseCatalog2> selectPmsBaseCateLog2All(Integer catalog1Id) {
        PmsBaseCatalog2Example example = new PmsBaseCatalog2Example();
        PmsBaseCatalog2Example.Criteria criteria = example.createCriteria();
        if (catalog1Id!=0&&catalog1Id!=null){
            criteria.andCatalog1IdEqualTo(catalog1Id);
        }
        List<PmsBaseCatalog2> pmsBaseCatalog2s = catalog2Mapper.selectByExample(example);
        return pmsBaseCatalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> selectPmsBaseCateLog3All(Long catalog2Id) {
        PmsBaseCatalog3Example example = new PmsBaseCatalog3Example();
        PmsBaseCatalog3Example.Criteria criteria = example.createCriteria();
        if (catalog2Id!=0&&catalog2Id!=null){
           criteria.andCatalog2IdEqualTo(catalog2Id);
        }
        List<PmsBaseCatalog3> pmsBaseCatalog3s = catalog3Mapper.selectByExample(example);
        return pmsBaseCatalog3s;
    }
}
