package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.bean.PmsBaseCatalog2;
import com.kgc.kmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-16 15:15
 */
public interface CatalogService {

    List<PmsBaseCatalog1> selectPmsBaseCateLog1All();

    List<PmsBaseCatalog2> selectPmsBaseCateLog2All(Integer catalog1Id);

    List<PmsBaseCatalog3> selectPmsBaseCateLog3All(Long catalog2Id);



}
