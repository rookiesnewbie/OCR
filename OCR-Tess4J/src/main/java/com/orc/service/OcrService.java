package com.orc.service;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/17 11:13
 * Version 1.0
 * @Description
 */
public interface OcrService {

    /**
     * 识别图片中的文件
     * @param imageFile 要识别的图片
     * @return  识别出来的文字
     */
    String recognizeText(MultipartFile imageFile) throws IOException, TesseractException;
}
