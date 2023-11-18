package com.orc.config;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author LiTeng
 * @Date 2023/11/17 20:53
 * Version 1.0
 * @Description
 */
@Setter
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunOcrConfig {
    private String KeyId;
    private String KeySecret;
    private String endpoint;

    @Bean
    public Client ocrClient(){
        try {

            Client client = new Client(new Config().setAccessKeyId(KeyId).setAccessKeySecret(KeySecret).setEndpoint(endpoint));
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
