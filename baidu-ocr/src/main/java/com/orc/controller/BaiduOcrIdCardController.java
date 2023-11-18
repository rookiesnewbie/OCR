package com.orc.controller;

import com.baidu.aip.ocr.AipOcr;
import com.orc.utils.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author LiTeng
 * @Date 2023/11/17 14:28
 * Version 1.0
 * @Description
 */

@RestController
@Slf4j
@RequestMapping("/api")
public class BaiduOcrIdCardController {


    @Autowired
    private AipOcr aipOcr;


    @PostMapping(value = "/idCard",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String processIdCard(@RequestParam("idCard") MultipartFile file,@RequestParam("idCardSide") String idCardSide) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        log.info(idCardSide);

        try {
            byte[] imageBytes = file.getBytes();
            JSONObject result = aipOcr.idcard(imageBytes,idCardSide, options);

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

}
