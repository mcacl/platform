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
        return Mono.just((new ResponseEntity<>(Stream.of(inMemoryProvider,regCenterProvider).flatMap(swaggerResourcesProvider->swaggerResourcesProvider.get().stream()).collect(Collectors.toList()),HttpStatus.OK)));
    }
}