package com.orc.controller;

import com.orc.utils.ByteToBase64Converter;
import com.orc.utils.ClientProflieUtil;
import com.orc.utils.JsonParser;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.GeneralHandwritingOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralHandwritingOCRResponse;
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
 * @Date 2023/11/18 9:39
 * Version 1.0
 * @Description
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class TencentOcrController {
    @Resource
    private Credential credential;


    /**
     * 通用字体识别
     * @param file
     * @return
     * @throws TencentCloudSDKException
     * @throws IOException
     */
    @PostMapping(value = "/tencent",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String TencentOCR(@RequestParam("file")MultipartFile file) throws TencentCloudSDKException, IOException {


        // 实例化要请求产品的client对象,clientProfile是可选的
        OcrClient client = new OcrClient(credential, "ap-guangzhou", ClientProflieUtil.clientProfile());

        //将上传的文件转换成base64编码
        String encodeBytesToBase64 = ByteToBase64Converter.encodeBytesToBase64(file.getBytes());


        // 实例化一个请求对象,每个接口都会对应一个request对象
        GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
        req.setImageBase64(encodeBytesToBase64);

       // req.setImageUrl("https://ltmyblog.oss-cn-shenzhen.aliyuncs.com/myBlog/article/image-20230924213423855.png");

        // 返回的resp是一个GeneralBasicOCRResponse的实例，与请求对象对应
        GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);


        GeneralBasicOCRResponse.toJsonString(resp);
        // 输出json格式的字符串回包
        System.out.println(GeneralBasicOCRResponse.toJsonString(resp));
        return JsonParser.extractDetectedText(GeneralBasicOCRResponse.toJsonString(resp));
    }

    /**
     * 通用手写字体识别
     * @param file
     * @return
     * @throws TencentCloudSDKException
     * @throws IOException
     */
    @PostMapping(value = "/hand",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String GeneralHandwritingOCR(@RequestParam("file")MultipartFile file) throws TencentCloudSDKException, IOException {

        // 实例化要请求产品的client对象,clientProfile是可选的
        OcrClient client = new OcrClient(credential, "ap-guangzhou", ClientProflieUtil.clientProfile());

        //将上传的文件转换成base64编码
        String encodeBytesToBase64 = ByteToBase64Converter.encodeBytesToBase64(file.getBytes());


        // 实例化一个请求对象,每个接口都会对应一个request对象
        GeneralHandwritingOCRRequest req = new GeneralHandwritingOCRRequest();
        req.setImageBase64(encodeBytesToBase64);


        // 返回的resp是一个GeneralBasicOCRResponse的实例，与请求对象对应
        GeneralHandwritingOCRResponse resp = client.GeneralHandwritingOCR(req);


        GeneralHandwritingOCRResponse.toJsonString(resp);
        // 输出json格式的字符串回包
        System.out.println(GeneralHandwritingOCRResponse.toJsonString(resp));
        return JsonParser.extractDetectedText(GeneralHandwritingOCRResponse.toJsonString(resp));
    }

    //...其他API参考官网文档来实现，这里不做过多的赘述，（照葫芦画瓢）https://console.cloud.tencent.com/api/explorer?Product=ocr&Version=2018-11-19&Action=RecognizeTableOCR

}
