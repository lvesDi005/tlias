package com.itcast.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UploadController {
/*    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile image)throws  Exception {
        log.info("文件上传开始{}", image.getOriginalFilename());
        // 调用阿里云OSS上传方法
        String url = aliyunOSSOperator.upload(image.getBytes(), image.getOriginalFilename());
        // 返回结果
        return Result.success(url);
    }*/
}
