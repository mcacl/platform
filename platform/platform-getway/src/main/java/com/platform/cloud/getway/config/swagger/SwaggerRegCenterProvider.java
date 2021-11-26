package com.platform.cloud.getway.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 创建时间:2021/9/13 0013
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Component
public class SwaggerRegCenterProvider implements SwaggerResourcesProvider{
    private static final String API_URI = "/v2/api-docs";
    private static final String API_PARAM = "?group=";
    private final RouteDefinitionRepository routeDefinitionRepository;
    private final DiscoveryClient discoveryClient;
    @Autowired
    private ApplicationContext applicationContext;

    public SwaggerRegCenterProvider(RouteDefinitionRepository routeDefinitionRepository,DiscoveryClient discoveryClient){
        this.routeDefinitionRepository = routeDefinitionRepository;
        this.discoveryClient = discoveryClient;
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
        /*List<RouteDefinition> routes = new ArrayList<>();
        routeDefinitionRepository.getRouteDefinitions().subscribe(routes::add);
        return routes.stream().flatMap(routeDefinition->routeDefinition.getPredicates().stream().filter(predicateDefinition->"Path".equalsIgnoreCase(predicateDefinition.getName())).map(predicateDefinition->swaggerResource(routeDefinition.getId(),predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**",API_URI)))).sorted(Comparator.comparing(SwaggerResource::getName)).collect(Collectors.toList());*/
        List<SwaggerResource> routes = new LinkedList<>();
        Map<String,List<ServiceInstance>> list = getServer();
        String getway = applicationContext.getEnvironment().getProperty("project.name");
        SwaggerResource getways;
        list.forEach((key,val)->{
            String name = key;
            String uri = "/" + key + API_URI + API_PARAM + key;
            if(key.contains("-biz")){
                name = key.replace("-biz","");
            }
            if(!key.equals(getway)){
                routes.add(swaggerResource(name,uri));
            }
        });
        return routes;
    }

    /**
     * 获取注册中心的服务列表
     * @return
     */
    public Map<String,List<ServiceInstance>> getServer(){
        Map<String,List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            List<ServiceInstance> sis = discoveryClient.getInstances(service);
            msl.put(service,sis);
        }
        return msl;
    }
}