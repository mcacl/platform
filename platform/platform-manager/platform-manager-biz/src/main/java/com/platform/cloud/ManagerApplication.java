package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@MapperScan(basePackages = "com.platform.cloud.*.mapper")
@EnableRetry
@EnableFeignClients()
@SpringCloudApplication
public class ManagerApplication{
    public static void main(String[] args){
        SpringApplication.run(ManagerApplication.class,args);
    }
}