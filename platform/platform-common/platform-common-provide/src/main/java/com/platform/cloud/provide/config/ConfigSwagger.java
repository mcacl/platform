package com.platform.cloud.provide.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2021/7/31
 * 创建人:pmc
 * 描述:
 */
//限制环境dev或test可用swagger网关
@Profile({"dev","test"})
@Configuration
@EnableSwagger2
public class ConfigSwagger{
    @Autowired
    private ApplicationContext applicationContext;

    private String getGroup(){
        return applicationContext.getEnvironment().getProperty("project.name");
    }

    private String getVersion(){
        return applicationContext.getEnvironment().getProperty("project.version");
    }

    private String getDescription(){
        return applicationContext.getEnvironment().getProperty("project.description");
    }

    @Bean(value = "docket")
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(getGroup()).select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build().securitySchemes(securitySchemes());
        return docket;
    }

    // 生成接口信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title(getGroup()).description(getDescription()).version(getVersion()).build();
    }

    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization","认证参数","header"));
        return apiKeyList;
    }
}