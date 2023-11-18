package com.orc.config;

import com.baidu.aip.ocr.AipOcr;
import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiTeng
 * @Date 2023/11/17 14:23
 * Version 1.0
 * @Description
 */

@Setter
@Configuration
@ConfigurationProperties(prefix = "baidu")
public class baiduOcrConfig {

    private String APP_ID;
    private String API_KEY;
    private String SECRET_KEY;

    @Bean
    public AipOcr aipOcr() {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

}
