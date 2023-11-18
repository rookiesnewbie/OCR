package com.orc.utils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Author LiTeng
 * @Date 2023/11/18 14:15
 * Version 1.0
 * @Description 解析腾讯云OCR的识别结果
 */
public class JsonParser {
    public static String extractDetectedText(String jsonString) {
        // 解析JSON字符串
        JSONObject jsonData = new JSONObject(jsonString);

        // 获取TextDetections数组
        JSONArray textDetections = jsonData.optJSONArray("TextDetections");

        // 提取DetectedText内容并拼接
        StringBuilder concatenatedText = new StringBuilder();
        if (textDetections != null) {
            for (int i = 0; i < textDetections.length(); i++) {
                JSONObject detection = textDetections.getJSONObject(i);
                String detectedText = detection.optString("DetectedText", "");
                concatenatedText.append(detectedText).append("\n");
            }
        }

        return concatenatedText.toString();
    }

}
