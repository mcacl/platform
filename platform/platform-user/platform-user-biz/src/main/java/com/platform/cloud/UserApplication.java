package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 创建时间:2021/9/26 0026
 * 创建人:pmc
 * 描述:
 */
@MapperScan(basePackages = "com.platform.cloud.user.mapper")
@EnableRetry
@EnableFeignClients(basePackages = {"com.platform.cloud.*"})
@SpringCloudApplication
public class UserApplication{
    public static void main(String[] args){
        SpringApplication.run(UserApplication.class,args);
    }
}
