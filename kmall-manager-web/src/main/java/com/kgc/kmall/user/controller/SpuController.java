package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:26
 */
@CrossOrigin
@RestController
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("/spuList")
    public List<PmsProductInfo> a(Long catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.selectPmsProductCatalog3Id(catalog3Id);
        return pmsProductInfos;
    }

}
