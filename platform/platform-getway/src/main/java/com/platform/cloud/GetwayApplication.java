package com.platform.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GetwayApplication{

    public static void main(String[] args){
        SpringApplication.run(GetwayApplication.class,args);
    }

}
