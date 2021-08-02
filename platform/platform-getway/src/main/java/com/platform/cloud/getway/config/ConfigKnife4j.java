package com.platform.cloud.getway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2021/7/31
 * 创建人:pmc
 * 描述:
 */

@Configuration
@EnableSwagger2WebMvc
public class ConfigKnife4j{
    @Autowired
    private ApplicationContext applicationContext;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("网关").select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build();
        return docket;
    }

    // 生成接口信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("platform网关").description(applicationContext.getEnvironment().getProperty("project.name")).version(applicationContext.getEnvironment().getProperty("project.version")).build().securitySchemes(securitySchemes());
    }

    private List<ApiKey> securitySchemes(){
        java.util.List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization","认证参数","header"));
        return apiKeyList;
    }
}
