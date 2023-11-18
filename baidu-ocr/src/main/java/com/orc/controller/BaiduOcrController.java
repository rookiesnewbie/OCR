package com.orc.controller;

import com.baidu.aip.ocr.AipOcr;
import com.orc.config.baiduOcrConfig;
import com.orc.utils.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static javax.crypto.Cipher.SECRET_KEY;

/**
 * @Author LiTeng
 * @Date 2023/11/17 14:28
 * Version 1.0
 * @Description
 */

@RestController
@Slf4j
@RequestMapping("/api")
public class BaiduOcrController {


    @Autowired
    private AipOcr aipOcr;

    /**
     * 获取图片的文字
     * @param file 要获取文字的图片
     * @return
     */
    @PostMapping(value = "/process",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String processImage(@RequestParam("file") MultipartFile file) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");
        try {
            byte[] imageBytes = file.getBytes();
            JSONObject result = aipOcr.basicGeneral(imageBytes, options);

            /*// 解析百度OCR的识别结果
            StringBuilder sb = new StringBuilder();
            result.getJSONArray("words_result").forEach(item -> {
                JSONObject word = (JSONObject) item;
                sb.append(word.getString("words")).append("\n");
            });

            return sb.toString();*/
            return JsonParser.extractWords(result);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing image";
        }

    }


    /**
     * 获取车牌号
     * @param file 要获取文字的图片
     * @return
     */
    @PostMapping(value = "/car",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String plateLicense(@RequestParam("file") MultipartFile file) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");
        try {
            byte[] imageBytes = file.getBytes();
            JSONObject result = aipOcr.plateLicense(imageBytes, options);

           /* // 解析百度OCR的识别结果
            StringBuilder sb = new StringBuilder();
            result.getJSONArray("words_result").forEach(item -> {
                JSONObject word = (JSONObject) item;
                sb.append(word.getString("words")).append("\n");
            });

            return sb.toString();*/
            return JsonParser.extractWords(result);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing image";
        }

    }



}
