package com.orc.controller;

import com.orc.service.OcrService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/17 11:18
 * Version 1.0
 * @Description 测试
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class OcrController {
    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping(value = "/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String recognizeImage(@RequestParam("file") MultipartFile file) throws TesseractException, IOException {

        log.info(ocrService.recognizeText(file));

        // 调用OcrService中的方法进行文字识别
        return ocrService.recognizeText(file);
    }
}
