package com.poxiao.common.swagger;
import	java.util.Collections;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author qinqi
 * @date 2020/9/17
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
//todo @Import(BeanValidatorPluginsConfiguration.class)
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean(value = "userApi")
    @Order(value = 1)
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //添加登录认证
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                ;

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PoXiao Cloud 接口文档")
                .description("springcloud版本的破晓")
                .contact(new Contact("poxiao", "", ""))
                .version("0.0.1")
                .build();
    }

    /**设置请求头信息*/
    private ApiKey apiKey()
    {
        return new ApiKey("TOKEN", "token", "header");
    }

    /**设置需要登录认证的路径*/
    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("global","accessEverything")
        };
        return Collections.singletonList(new SecurityReference("BearerToken",authorizationScopes));
    }
}
