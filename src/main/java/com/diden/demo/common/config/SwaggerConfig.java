package com.diden.demo.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "디든 API 명세서",
                description = "API 명세서",
                version = "v1"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    /*@Bean
    public GroupedOpenApi diddenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("디든 API V1 ")
                .pathsToMatch(paths)
                .build();
    }*/
}
