package com.starapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.FileNotFoundException;


@SpringBootApplication
@MapperScan(basePackages="com.starapp.dao")
@Configuration
public class AllenApplication {
    //http://localhost:8989/starApp/
    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(AllenApplication.class, args);
        System.out.println("############[starApp is staring..]##########");


    }

    /**
     * 文件上传配置.Spring Boot工程嵌入的tomcat限制了请求的文件大小
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大,即150MB，配置文件中配置的也是150MB
        factory.setMaxFileSize("153600KB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("153600KB");
        return factory.createMultipartConfig();
    }
}
