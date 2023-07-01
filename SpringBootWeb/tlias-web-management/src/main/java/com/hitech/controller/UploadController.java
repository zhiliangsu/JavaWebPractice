package com.hitech.controller;

import com.hitech.pojo.Result;
import com.hitech.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传: {}, {}, {}", username, age, image);

        // 调用阿里云OSS工具类,将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        log.info("文件访问url: {}", url);

        // 将图片上传完成后的url返回,用于浏览器回显显示
        return Result.success(url);
    }

    /* @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传: {}, {}, {}", username, age, image);

        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();
        
        //构建新的文件名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extname;

        // 把文件存储在服务器的磁盘目录
        image.transferTo(new File("C:\\Users\\szl\\Favorites\\images\\" + newFileName));

        return Result.success();
    } */

}
