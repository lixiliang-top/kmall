package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:20
 */
public interface SpuService {

    List<PmsProductInfo> selectPmsProductCatalog3Id(Long catalog3Id);

    List<PmsBaseSaleAttr> selectPmsBaseSaleAttrAll();

    Integer insSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(Long spuId);

    List<PmsProductImage> spuImageList(Long spuId);

    //添加了isChecked属性
    List<PmsProductSaleAttr> spuSaleAttrListIsCheck(Long spuId,Long skuId);
}
