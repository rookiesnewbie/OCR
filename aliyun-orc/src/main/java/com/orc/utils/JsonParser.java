package com.orc.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Author LiTeng
 * @Date 2023/11/18 14:56
 * Version 1.0
 * @Description 解析阿里云OCR的识别结果
 */
public class JsonParser {
    public static String extractContent(String jsonString) {

        String[] split = jsonString.split(",");

        return split[1].split(":")[1].replace("\\","").replace("\"","");
    }

}
