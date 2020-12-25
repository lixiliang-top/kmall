package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.service.AttrService;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:25
 */
@CrossOrigin
@RestController
@Api(tags = "商品信息管理",description = "提供商品相关的Rest API")
public class AttrController {

    @Reference
    AttrService attrService;

    @ApiOperation(value = "根据catalog3Id查询平台属性列表集合信息",httpMethod = "GET")
    @RequestMapping("/attrInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalog3Id",value ="三级分类",required = true)
    })
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.selectAttrInfoAll(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @ApiOperation(value = "添加平台属性实体类",httpMethod = "POST")
    @RequestMapping("/saveAttrInfo")
    public Integer saveAttrInfo(@RequestBody @ApiParam(name = "attrInfo",value = "平台属性对象",required = true) PmsBaseAttrInfo attrInfo){
        Integer i = attrService.add(attrInfo);
        return i;
    }


    @ApiOperation(value = "获取平台属性值集合列表信息",httpMethod = "GET")
    @RequestMapping("/getAttrValueList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attrId",value = "平台属性Id",required = false)
    })
    public List<PmsBaseAttrValue> getAttrValueList(Long attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.selectAttrInfoId(attrId);
        return pmsBaseAttrValues;
    }

}
