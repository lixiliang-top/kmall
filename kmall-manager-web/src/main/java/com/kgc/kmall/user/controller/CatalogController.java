package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.service.AttrService;
import com.kgc.kmall.service.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-16 15:34
 */
@CrossOrigin
@RestController
@Api(tags = "分类信息管理",description = "提供分类相关的Rest API")
public class CatalogController {

    @Reference
    CatalogService catalogService;

    @ApiOperation(value = "一级分类",httpMethod = "GET")
    @RequestMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> pmsBaseCatalog1s = catalogService.selectPmsBaseCateLog1All();
        return pmsBaseCatalog1s;
    }

    @ApiOperation(value = "根据一级分类查询出二级分类",httpMethod = "GET")
    @RequestMapping("/getCatalog2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalog1Id",value = "一级分类id",required = true)
    })
    public List<PmsBaseCatalog2> getCatalog2(Integer catalog1Id){
        List<PmsBaseCatalog2> pmsBaseCatalog2s = catalogService.selectPmsBaseCateLog2All(catalog1Id);
        return pmsBaseCatalog2s;
    }

    @ApiOperation(value = "根据二级分类查询出三级分类",httpMethod = "GET")
    @RequestMapping("/getCatalog3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalog2Id",value = "二级分类id",required = true)
    })
    public List<PmsBaseCatalog3> getCatalog3(Long catalog2Id){
        List<PmsBaseCatalog3> pmsBaseCatalog3s = catalogService.selectPmsBaseCateLog3All(catalog2Id);
        return pmsBaseCatalog3s;
    }

}
