package com.poxiao.gateway.controller;
import	java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import javax.annotation.Resource;

/**
 * @author qinqi
 * @date 2020/9/14
 */
@RestController
@RequestMapping("swagger-resources")
public class SwaggerController {

    @Resource
    private SwaggerResourcesProvider swaggerResourcesProvider;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;


    @SuppressWarnings("rowtypes")
    @GetMapping("")
    public Mono<ResponseEntity> getSwaggerResource() {
        return Mono.just(new ResponseEntity<>(swaggerResourcesProvider.get(), HttpStatus.OK));
    }

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()),HttpStatus.OK));
    }

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()),HttpStatus.OK));
    }
}
