package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启servlet组件支持
@SpringBootApplication
public class WebProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebProject1Application.class, args);
    }

}
