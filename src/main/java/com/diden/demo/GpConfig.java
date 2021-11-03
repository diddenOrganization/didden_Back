package com.diden.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class GpConfig implements WebMvcConfigurer {
    public void addCorsMappings(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;

        res.setContentType("application/json; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        chain.doFilter(request, res);
    }

    public void addCorsMappings(CorsRegistry cr) {
        cr.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}