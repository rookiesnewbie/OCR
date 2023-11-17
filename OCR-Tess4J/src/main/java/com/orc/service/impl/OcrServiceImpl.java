package com.orc.service.impl;

import com.orc.service.OcrService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LiTeng
 * @Date 2023/11/17 11:15
 * Version 1.0
 * @Description
 */
@Service
public class OcrServiceImpl implements OcrService {

    private final Tesseract tesseract;

    public OcrServiceImpl(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    /**
     *
     * @param imageFile 要识别的图片
     * @return
     */
    @Override
    public String recognizeText(MultipartFile imageFile) throws IOException, TesseractException {
        // 转换
        InputStream sbs = new ByteArrayInputStream(imageFile.getBytes());
        BufferedImage bufferedImage = ImageIO.read(sbs);

        // 对图片进行文字识别
        return tesseract.doOCR(bufferedImage);
    }
}
