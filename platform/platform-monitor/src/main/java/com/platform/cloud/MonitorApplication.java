package com.platform.cloud;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableAutoConfiguration
@EnableAdminServer
@SpringCloudApplication
public class MonitorApplication{
    public static void main(String[] args){
        SpringApplication.run(MonitorApplication.class,args);
    }

}