package com.kgc.kmall.search.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李锡良
 * @create 2021-01-04 18:38
 */
@Configuration
public class MyConfig {

    @Value("${spring.el.host:disabled}")
    private String elHost;

    @Bean
    public JestClient getJestCline() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(elHost)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }

}
