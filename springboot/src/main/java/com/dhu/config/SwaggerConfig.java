package com.dhu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ComingLiu
 * Date: 2022/4/18 19:08
 */
@Configuration
@EnableSwagger2 // 开启Swagger2
public class SwaggerConfig {
    @Autowired
    Environment environment;
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                选择是否启动Swagger, 如果为False, 则Swagger不能在浏览器中访问
//                .enable(false)
                .select()
//                配置要扫描接口的方式
//                withClassAnnotation 扫描类上的注解, 参数是一个注解的反射对象
//                withMethodAnnotation 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.dhu.Controller"))
//                过滤什么路径
//                .paths()
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    @Bean
    public Docket UserDocket(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户");
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("CL", "https://www,baidu.com", "1328004790@qq.com");
       return new ApiInfoBuilder()
               .title("后端-API文档")
               .description("本文描述了一个接口文档")
               .version("2.0")
               .contact(new Contact("", "", ""))
               .build();
    }
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }


    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }


    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
