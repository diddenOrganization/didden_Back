package com.diden.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class GpConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/data/**").addResourceLocations("classpath:/static/data/");
        // 업로드 이미지용 외부 폴더 추가 registry.addResourceHandler("/upload/**")
        // .addResourceLocations("file:///"+uploadImagePath) // 웹에서 이미지 호출시 'file:///'
        // 설정됨 .setCachePeriod(3600) .resourceChain(true) .addResolver(new
        // PathResourceResolver());
    }

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
                        , "/**/user/api/social/login"
                        , "/**/anno/**"
                        , "/**/main/content/images"
                        , "/**/info"
                        , "/**/img/**"
                );
    }
}