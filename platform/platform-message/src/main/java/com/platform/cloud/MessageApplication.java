package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.platform.cloud.*.mapper")
@SpringCloudApplication
public class MessageApplication{

    public static void main(String[] args){
        SpringApplication.run(MessageApplication.class,args);
    }

}