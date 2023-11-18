package com.orc.controller;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.google.gson.Gson;
import com.orc.utils.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/17 21:02
 * Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AliyunOcrController {

    @Resource
    private Client client;

    @PostMapping(value = "ocr",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String OcrTest(@RequestParam("file")MultipartFile file) throws IOException {

        //通用字体识别API
        RecognizeGeneralRequest request = new RecognizeGeneralRequest();
        request.setBody(file.getInputStream());
        try {
            RecognizeGeneralResponse response = client.recognizeGeneral(request);
            System.out.println(JsonParser.extractContent(new Gson().toJson(response.getBody())));
           /* String json = new Gson().toJson(response.getBody());
            System.out.println(json);
            String[] split = json.split(",");

            return split[1].split(":")[1].replace("\\","").replace("\"","");*/
            return JsonParser.extractContent(new Gson().toJson(response.getBody()));

        } catch (TeaException error) {
            Common.assertAsString(error.message);
        } catch (Exception e) {
            TeaException error = new TeaException(e.getMessage(), e);
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
        }
        return null;
    }

}
