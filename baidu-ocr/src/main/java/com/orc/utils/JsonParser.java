package com.orc.utils;

import org.json.JSONObject;

/**
 * @Author LiTeng
 * @Date 2023/11/18 15:05
 * Version 1.0
 * @Description 解析百度OCR的识别结果
 */
public class JsonParser {

    public static String extractWords(JSONObject result){
        // 解析百度OCR的识别结果
        StringBuilder sb = new StringBuilder();
        result.getJSONArray("words_result").forEach(item -> {
            JSONObject word = (JSONObject) item;
            sb.append(word.getString("words")).append("\n");
        });

        return sb.toString();
    }

}
