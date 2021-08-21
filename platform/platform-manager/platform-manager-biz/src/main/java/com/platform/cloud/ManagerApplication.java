package com.platform.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ManagerApplication{

    public static void main(String[] args){
        SpringApplication.run(ManagerApplication.class,args);
    }

}
