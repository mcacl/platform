package com.platform.cloud.getway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建时间:2021/9/13 0013
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Component
public class SwaggerRegCenterProvider implements SwaggerResourcesProvider{
    private static final String API_URI = "/v2/api-docs";
    private final RouteDefinitionRepository routeDefinitionRepository;

    public SwaggerRegCenterProvider(RouteDefinitionRepository routeDefinitionRepository){
        this.routeDefinitionRepository = routeDefinitionRepository;
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
        List<RouteDefinition> routes = new ArrayList<>();
        routeDefinitionRepository.getRouteDefinitions().subscribe(routes::add);
        return routes.stream().flatMap(routeDefinition->routeDefinition.getPredicates().stream().filter(predicateDefinition->"Path".equalsIgnoreCase(predicateDefinition.getName())).map(predicateDefinition->swaggerResource(routeDefinition.getId(),predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**",API_URI)))).sorted(Comparator.comparing(SwaggerResource::getName)).collect(Collectors.toList());
    }
}