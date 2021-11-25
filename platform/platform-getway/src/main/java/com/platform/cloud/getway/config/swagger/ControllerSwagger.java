package com.platform.cloud.getway.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

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
    private SwaggerResourcesProvider inMemoryProvider;

    @Autowired
    @Qualifier("swaggerRegCenterProvider")
    private SwaggerResourcesProvider regCenterProvider;

    @Autowired
    @Qualifier("swaggerYMLProvider")
    private SwaggerResourcesProvider ymlProvider;

    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    /*@GetMapping("/configuration/security")
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
        //inMemoryProvider,ymlProvider,regCenterProvider
        //去注册中心已注册的服务
        Mono<List<SwaggerResource>> res = Mono.just(Stream.of(inMemoryProvider,regCenterProvider).flatMap(swaggerResourcesProvider->swaggerResourcesProvider.get().stream()).collect(Collectors.toList()));
        return res;
    }*/

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration(){
        return Mono.just(new ResponseEntity<>(Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()),HttpStatus.OK));
    }

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration(){
        return Mono.just(new ResponseEntity<>(Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()),HttpStatus.OK));
    }

    @GetMapping("")
    public Mono<ResponseEntity> swaggerResources(){
        //return Mono.just((new ResponseEntity<>(Stream.of(regCenterProvider).flatMap(swaggerResourcesProvider->swaggerResourcesProvider.get().stream()).collect(Collectors.toList()),HttpStatus.OK)));
        return Mono.just(new ResponseEntity(regCenterProvider.get(),HttpStatus.OK));
    }
}