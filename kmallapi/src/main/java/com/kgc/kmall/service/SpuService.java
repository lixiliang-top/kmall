package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsProductInfo;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:20
 */
public interface SpuService {

    List<PmsProductInfo> selectPmsProductCatalog3Id(Long catalog3Id);

}
