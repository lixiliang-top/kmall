package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-16 16:15
 */
public interface AttrService {

    List<PmsBaseAttrInfo> selectAttrInfoAll(Long catalog3Id);

    Integer add(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> selectAttrInfoId(Long attrId);


}
