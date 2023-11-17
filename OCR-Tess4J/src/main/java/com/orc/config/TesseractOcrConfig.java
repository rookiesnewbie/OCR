package com.orc.config;

import lombok.Data;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiTeng
 * @Date 2023/11/17 11:07
 * Version 1.0
 * @Description Tess4j配置类
 */
@Configuration

public class TesseractOcrConfig {

    @Value("${tess4j.dataPath}")
    private String dataPath;

    @Bean
    public Tesseract tesseract() {

        Tesseract tesseract = new Tesseract();
        // 设置训练数据文件夹路径
        tesseract.setDatapath(dataPath);
        // 设置为中文简体
        tesseract.setLanguage("chi_sim");
        return tesseract;
    }

}
