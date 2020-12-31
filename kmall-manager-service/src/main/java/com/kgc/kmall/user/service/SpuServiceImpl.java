package com.kgc.kmall.user.service;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.service.SpuService;
import com.kgc.kmall.user.mapper.*;
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

    @Resource
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Resource
    PmsProductImageMapper pmsProductImageMapper;

    @Resource
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Resource
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> selectPmsProductCatalog3Id(Long catalog3Id) {
        PmsProductInfoExample example = new PmsProductInfoExample();
        PmsProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCatalog3IdEqualTo(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectByExample(example);
        return pmsProductInfos;
    }

    @Override
    public List<PmsBaseSaleAttr> selectPmsBaseSaleAttrAll() {
        PmsBaseSaleAttrExample example = new PmsBaseSaleAttrExample();
        PmsBaseSaleAttrExample.Criteria criteria = example.createCriteria();
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrMapper.selectByExample(example);
        return pmsBaseSaleAttrs;
    }

    @Override
    public Integer insSpuInfo(PmsProductInfo pmsProductInfo) {
        try {
            //添加商品
            pmsProductInfoMapper.insertSelective(pmsProductInfo);

            //添加图片
            if (pmsProductInfo.getSpuImageList()!=null&&pmsProductInfo.getSpuImageList().size()>0){
                pmsProductImageMapper.insertProductInfo(pmsProductInfo.getId(),pmsProductInfo.getSpuImageList());
            }

            //添加销售属性
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            if (spuSaleAttrList!=null&&spuSaleAttrList.size()>0){
                pmsProductSaleAttrMapper.insertProductSaleAttr(pmsProductInfo.getId(),spuSaleAttrList);
                for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                    if (pmsProductSaleAttr.getSpuSaleAttrValueList()!=null&&pmsProductSaleAttr.getSpuSaleAttrValueList().size()>0){
                        pmsProductSaleAttrValueMapper.insertProductSaleAttrValue(pmsProductInfo.getId(),pmsProductSaleAttr.getSpuSaleAttrValueList());
                    }
                }
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId) {
        PmsProductSaleAttrExample example=new PmsProductSaleAttrExample();
        PmsProductSaleAttrExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.selectByExample(example);
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
            PmsProductSaleAttrValueExample example1=new PmsProductSaleAttrValueExample();
            PmsProductSaleAttrValueExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSaleAttrIdEqualTo(pmsProductSaleAttr.getSaleAttrId());
            criteria1.andProductIdEqualTo(spuId);

            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttrValueMapper.selectByExample(example1);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValueList);
        }
        return pmsProductSaleAttrList;
    }

    @Override
    public List<PmsProductImage> spuImageList(Long spuId) {
        PmsProductImageExample example = new PmsProductImageExample();
        PmsProductImageExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.selectByExample(example);
        return pmsProductImages;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListIsCheck(Long spuId, Long skuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.spuSaleAttrListIsCheck(spuId, skuId);
        return pmsProductSaleAttrList;
    }
}
