package com.cn.jasmine.demo.conf;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class CommonConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024 * 1024 * 1024); // 限制上传文件大小
        factory.setMaxRequestSize(1024 * 1024 * 1024 * 1024);
        return factory.createMultipartConfig();
    }

}
