package com.stackroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo()
    {

        return new ApiInfo(
                "Authentication Service REST ",
                "This code is developed By Parth Sharma.",
                "1.0",
                "Terms of service",
                new Contact("Parth Sharma", "www.salon-service.com", "parth.sharma@globallogic.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    public static final String AUTHORIZATION_HEADER="Authorization";


    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }


    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }
    private List<SecurityReference> securityReferences(){
        AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
    return  Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{ authorizationScope} ));
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null).operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}