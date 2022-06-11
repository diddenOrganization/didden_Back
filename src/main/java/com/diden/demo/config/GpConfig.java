package com.diden.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class GpConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry cr) {
        cr.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).order(1) // 인터셉터 체인 순서
                .addPathPatterns("/**") // 모든 requestURL에 대해 적용
                .excludePathPatterns("/" // 제외하고 싶은 whitelist
                        , "/**/login"
                        , "/**/logout"
                        , "/**/error"
                        , "/**/tour/api/**"
                        , "/**/user/insert"
                        , "/**/user/list"
                        , "/user/api/social/json"
                        , "/**/anno/**"
                        , "/**/main/content/images"
                        , "/user"
                        , "/**/img/**"
                        , "/info"
                );
    }
}