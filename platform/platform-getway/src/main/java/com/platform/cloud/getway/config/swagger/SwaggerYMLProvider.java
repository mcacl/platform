package com.platform.cloud.getway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2021/9/3 0003
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Component
public class SwaggerYMLProvider implements SwaggerResourcesProvider{
    private static final String API_URI = "v2/api-docs";

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public SwaggerYMLProvider(RouteLocator routeLocator,GatewayProperties gatewayProperties){
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    private static SwaggerResource swaggerResource(String name,String url){
        log.info("name:{},url:{}",name,url);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(url);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    @Override
    public List<SwaggerResource> get(){
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //获取所有路由的ID
        routeLocator.getRoutes().subscribe(route->routes.add(route.getId()));
        //过滤出配置文件中定义的路由->过滤出Path Route Predicate->根据路径拼接成api-docs路径->生成SwaggerResource
        gatewayProperties.getRoutes().stream().filter(routeDefinition->routes.contains(routeDefinition.getId())).forEach(route->{
            route.getPredicates().stream().filter(predicateDefinition->("Path").equalsIgnoreCase(predicateDefinition.getName())).forEach(predicateDefinition->resources.add(swaggerResource(route.getId(),predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("**",API_URI))));
        });

        return resources;
    }
}