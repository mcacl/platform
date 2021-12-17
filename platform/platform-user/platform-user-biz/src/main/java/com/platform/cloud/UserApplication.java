package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 创建时间:2021/9/26 0026
 * 创建人:pmc
 * 描述:
 */
@EnableRetry
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.platform.cloud.*.mapper")
@SpringCloudApplication
public class UserApplication{
    public static void main(String[] args){
        SpringApplication.run(UserApplication.class,args);
    }
}