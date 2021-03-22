package com.poxiao.gateway.config;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinqi
 * @date 2020/9/14
 * SwaggerProvider
 */
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {


    @Resource
    private RouteLocator routeLocator;
    @Resource
    private GatewayProperties gatewayProperties;

    private static final String API_URI = "/v2/api-docs";


    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> swaggerResources = new ArrayList<>();
        List<String> routeIds = new ArrayList<>();

        routeLocator.getRoutes().subscribe(route -> routeIds.add(route.getId()));
        gatewayProperties.getRoutes().parallelStream()
                .filter(routeDefinition -> routeIds.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().parallelStream()
                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> {
                            swaggerResources.add(swaggerResource(routeDefinition.getId()
                                    ,predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX+"0")
                                            .replace("/**",API_URI)));
                        })
        );
        return swaggerResources;
    }

    private SwaggerResource swaggerResource(String name,String url) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(url);
        swaggerResource.setSwaggerVersion("0.0.1");
        return swaggerResource;
    }
}
