package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:25
 */
@CrossOrigin
@RestController
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.selectAttrInfoAll(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @RequestMapping("/saveAttrInfo")
    public Integer saveAttrInfo(@RequestBody PmsBaseAttrInfo attrInfo){
        Integer i = attrService.add(attrInfo);
        return i;
    }


    @RequestMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(Long attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.selectAttrInfoId(attrId);
        return pmsBaseAttrValues;
    }

}
