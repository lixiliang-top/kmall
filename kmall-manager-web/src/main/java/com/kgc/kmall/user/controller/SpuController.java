package com.kgc.kmall.user.controller;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductSaleAttr;
import com.kgc.kmall.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-17 14:26
 */
@CrossOrigin
@RestController
@Api(tags = "spu销售属性信息管理",description = "提供spu相关的Rest API")
public class SpuController {

    @Reference
    SpuService spuService;

    @Value("${fileServer.url}")
    String fileUrl;

    @ApiOperation(value = "根据catalog3Id查询出spuInfo表信息数据",httpMethod = "GET")
    @RequestMapping("/spuList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalog3Id",value = "三级分类Id",required = true)
    })
    public List<PmsProductInfo> a(Long catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.selectPmsProductCatalog3Id(catalog3Id);
        return pmsProductInfos;
    }

    @ApiOperation(value = "查询出销售属性全部信息pms-base-sale-attr",httpMethod = "GET")
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> saleAttrList = spuService.selectPmsBaseSaleAttrAll();
        return saleAttrList;
    }

    @ApiOperation(value = "上传头像到fastDFS",httpMethod = "GET")
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file) throws IOException, MyException {
        //文件上传
        //返回文件上传后的路径
        String imgUrl="http://"+fileUrl;
        if(file!=null){
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String filename=    file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(filename);

            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imgUrl+="/"+path;
            }
        }
        System.out.println(imgUrl);
        return imgUrl;
    }

    @ApiOperation(value = "添加平台属性信息pms-product-info",httpMethod = "POST")
    @RequestMapping("/saveSpuInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pmsProductInfo",value = "平台信息",required = false)
    })
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.insSpuInfo(pmsProductInfo);
        return "success";
}

    @ApiOperation(value = "根据spuId查询出销售属性部分信息",httpMethod = "GET")
    @RequestMapping("/spuSaleAttrList")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "销售Id",name = "spuId",required = true)
    })
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrList=spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }

    @ApiOperation(value = "根据spuId查询出销售图片",httpMethod = "GET")
    @RequestMapping("/spuImageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId",value = "销售Id",required = true)
    })
    public List<PmsProductImage> spuImageList(Long spuId){
        List<PmsProductImage> pmsProductImageList = spuService.spuImageList(spuId);
        return pmsProductImageList;
    }


}
