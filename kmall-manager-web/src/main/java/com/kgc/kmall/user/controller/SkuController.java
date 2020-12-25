package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.PmsSkuInfo;
import com.kgc.kmall.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李锡良
 * @create 2020-12-23 18:59
 */
@CrossOrigin
@RestController
@Api(tags = "sku销售属性信息管理",description = "提供sku相关的Rest API")
public class SkuController {

    @Reference
    SkuService skuService;

    @ApiOperation(value = "添加skuInfo表信息",httpMethod = "GET")
    @RequestMapping("/saveSkuInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuInfo",value = "sku销售属性",required = false)
    })
    public String saveSkuInfo(@RequestBody PmsSkuInfo skuInfo){
        String result = skuService.saveSkuInfo(skuInfo);
        return result;
    }

}
