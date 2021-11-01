package com.diden.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class GpConfig implements WebMvcConfigurer {
    public void addCorsMappings(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(request, response);
        // res.setHeader(name, value);
        // cr.addMapping("/**").allowedOrigins("http://127.0.0.1:8081") // 허용할 주소 및 포트
        // .allowedOrigins("http://localhost:8081"); // 허용할 주소 및 포트
    }
}