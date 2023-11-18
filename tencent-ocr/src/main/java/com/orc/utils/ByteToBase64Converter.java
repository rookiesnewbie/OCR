package com.orc.utils;

import java.util.Base64;

/**
 * @Author LiTeng
 * @Date 2023/11/18 13:54
 * Version 1.0
 * @Description  将文件转换成base64编码
 */
public class ByteToBase64Converter {

    public static String encodeBytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
