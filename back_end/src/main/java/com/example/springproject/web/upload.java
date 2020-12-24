package com.example.springproject.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


@RestController
@RequestMapping("/upload")
@CrossOrigin
public class upload {
    @PostMapping("/singlefile")
    public Object singleFileUpload(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get("E:\\ooad_pro\\game\\" + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("E:\\ooad_pro\\game\\"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }



    @PostMapping("/upphoto")
    public Object photoUP(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }
        try {

            byte[] bytes = file.getBytes();
            String name = file.getOriginalFilename();
            Path path = Paths.get("E:\\ooad_pro\\avatars\\" + name.substring(0, name.indexOf('-')));
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("E:\\ooad_pro\\avatars\\"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }
}