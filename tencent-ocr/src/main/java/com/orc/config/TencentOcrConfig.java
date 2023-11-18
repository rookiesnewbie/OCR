package com.orc.config;

import com.tencentcloudapi.common.Credential;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiTeng
 * @Date 2023/11/18 9:31
 * Version 1.0
 * @Description 腾讯云ocr配置类
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "tencent")
public class TencentOcrConfig {

    private String SecretId;
    private String SecretKey;

    @Bean
    public Credential credential(){
        return new Credential(SecretId,SecretKey);
    }

}
