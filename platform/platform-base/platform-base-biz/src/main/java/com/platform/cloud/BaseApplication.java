package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableFeignClients
@MapperScan(basePackages = "com.platform.cloud.base.mapper")
@SpringCloudApplication
public class BaseApplication{
    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class,args);
    }
}