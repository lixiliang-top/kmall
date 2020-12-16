package com.kgc.kmall.user;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.bean.PmsBaseCatalog2;
import com.kgc.kmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KmallManagerServiceApplicationTests {

	@Reference
	CatalogService catalogService;

	@Test
	void contextLoads() {
		List<PmsBaseCatalog1> pmsBaseCatalog1s = catalogService.selectPmsBaseCateLog1All();
		for (PmsBaseCatalog1 pmsBaseCatalog1 : pmsBaseCatalog1s) {
			System.out.println(pmsBaseCatalog1.toString());
		}
	}

	@Test
	void contextLoads2() {
		List<PmsBaseCatalog2> pmsBaseCatalog2s = catalogService.selectPmsBaseCateLog2All(1);
		for (PmsBaseCatalog2 pmsBaseCatalog2 : pmsBaseCatalog2s) {
			System.out.println(pmsBaseCatalog2.toString());
		}
	}

}
