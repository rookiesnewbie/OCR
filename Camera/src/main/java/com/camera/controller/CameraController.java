package com.camera.controller;


import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author LiTeng
 * @Date 2023/11/18 13:17
 * Version 1.0
 * @Description
 */
@Controller
public class CameraController {

    @GetMapping("/startCamera")
    public String startCamera() {
        // 创建视频捕获对象，0表示默认摄像头
        VideoCapture capture = new VideoCapture(0);

        // 检查摄像头是否成功打开
        if (!capture.isOpened()) {
            System.out.println("Error: Could not open camera.");
            return "error";
        }

        // 创建窗口
        CanvasFrame frame = new CanvasFrame("Camera Capture");

        // 设置窗口关闭时的操作
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // 无限循环，不断读取摄像头数据并显示
        while (true) {
            // 读取一帧视频
            Mat mat = new Mat();
            capture.read(mat);

            // 将帧显示在窗口中
            frame.showImage(new Frame());

            // 等待一段时间，可以控制视频的播放速度
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
