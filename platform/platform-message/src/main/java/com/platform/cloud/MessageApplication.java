package com.platform.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringCloudApplication
@EnableKafka
@MapperScan(basePackages = "com.platform.cloud.*.mapper")
//@EnableRetry
public class MessageApplication{

    public static void main(String[] args){
        SpringApplication.run(MessageApplication.class,args);
    }

}