package com.platform.cloud.getway.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.stream.Collectors;

/**
 * 创建时间:2021/7/31
 * 创建人:pmc
 * 描述:
 */

@Configuration
@EnableSwagger2
public class ConfigKnife4j{
    @Autowired
    private ApplicationContext applicationContext;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("网关").select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build().securitySchemes(securitySchemes());
        return docket;
    }

    // 生成接口信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("platform网关").description(applicationContext.getEnvironment().getProperty("project.name")).version(applicationContext.getEnvironment().getProperty("project.version")).build();
    }

    private List<SecurityScheme> securitySchemes(){
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization","认证参数","header"));
        return apiKeyList.stream().collect(Collectors.toList());
    }
}
