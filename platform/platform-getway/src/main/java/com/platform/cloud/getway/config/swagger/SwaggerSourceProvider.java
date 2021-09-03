package com.platform.cloud.getway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建时间:2021/9/3 0003
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Component
public class SwaggerSourceProvider implements SwaggerResourcesProvider, WebFluxConfigurer{
    private static final String API_URI = "/v2/api-docs";

    private final RouteDefinitionRepository routeDefinitionRepository;

    public SwaggerSourceProvider(RouteDefinitionRepository routeDefinitionRepository){
        this.routeDefinitionRepository = routeDefinitionRepository;
    }

    private static SwaggerResource swaggerSource(String name,String url){
        log.info("name:{},url:{}",name,url);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(url);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    /**
     * Gets a result.
     * @return a result
     */
    @Override
    public List<SwaggerResource> get(){
        List<RouteDefinition> routes = new ArrayList<>();
        routeDefinitionRepository.getRouteDefinitions().subscribe(routes::add);

        List<SwaggerResource> list = routes.stream().flatMap(x->x.getPredicates().stream().filter(y->"Path".equalsIgnoreCase(y.getName())).map(z->swaggerSource(x.getId(),z.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**",API_URI)))).sorted(Comparator.comparing(SwaggerResource::getName)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        /** swagger-ui 地址 */
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
