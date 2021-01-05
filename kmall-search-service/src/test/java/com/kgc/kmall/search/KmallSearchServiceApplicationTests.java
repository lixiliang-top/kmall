package com.kgc.kmall.search;

import com.kgc.kmall.bean.PmsSearchSkuInfo;
import com.kgc.kmall.bean.PmsSkuInfo;
import com.kgc.kmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KmallSearchServiceApplicationTests {


	@Reference
	SkuService skuService;

	@Resource
	JestClient jestClient;

	@Test
	void contextLoads() {
		//查询全部的skuinfo信息
		List<PmsSkuInfo> allSku = skuService.getAllSku();
		//声明el实体的集合类
		List<PmsSearchSkuInfo> pmsSearchSkuInfos = new ArrayList<>();
		for (PmsSkuInfo skuInfo : allSku) {
			//声明PmsSearchSkuInfo实体
			PmsSearchSkuInfo searchSkuInfo = new PmsSearchSkuInfo();
			//使用BeanUtils下面的copyProperties方法进行赋值
			BeanUtils.copyProperties(skuInfo,searchSkuInfo);
			//给PmsSearchSkuInfo实体中的ProductId赋值
			searchSkuInfo.setProductId(skuInfo.getSpuId());
			//把下面声明的实体添加到el实体的集合类中
			pmsSearchSkuInfos.add(searchSkuInfo);
		}
		for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfos) {
			Index index=new Index.Builder(pmsSearchSkuInfo).index("kmall").type("PmsSkuInfo").id(pmsSearchSkuInfo.getId()+"").build();
			try {
				jestClient.execute(index);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
