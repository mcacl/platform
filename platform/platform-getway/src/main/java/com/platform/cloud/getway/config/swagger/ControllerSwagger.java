package com.platform.cloud.getway.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建时间:2021/9/3 0003
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("/swagger-resources")
public class ControllerSwagger{
    @Autowired
    @Qualifier("inMemorySwaggerResourcesProvider")
    private SwaggerResourcesProvider swaggerRegCenterProvider;

    @Autowired
    @Qualifier("swaggerYMLProvider")
    private SwaggerResourcesProvider swaggerYMLProvider;

    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @GetMapping("/configuration/security")
    public Mono<SecurityConfiguration> securityConfiguration(){
        return Mono.just(Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()));
    }

    @GetMapping("/configuration/ui")
    public Mono<UiConfiguration> uiConfiguration(){
        return Mono.just(Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()));
    }

    @SuppressWarnings("rawtypes")
    @GetMapping
    public Mono<List<SwaggerResource>> swaggerResources(){
        return Mono.just(Stream.of(swaggerRegCenterProvider,swaggerYMLProvider).flatMap(swaggerResourcesProvider->swaggerResourcesProvider.get().stream()).collect(Collectors.toList()));
    }
}