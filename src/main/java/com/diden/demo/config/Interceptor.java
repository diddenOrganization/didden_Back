package com.diden.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Interceptor implements HandlerInterceptor {
    final private TokenCheckAdepter tokenCheckAdepter = new TokenCheckAdepter();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization : Interceptor");
        if (request.getServerName().startsWith("127") || request.getServerName().startsWith("local")) return true;

        final String loginType = request.getHeader("login_type");
        final String Authorization = request.getHeader("Authorization");

        return this.tokenCheckAdepter.tokenCheckMethod(loginType, Authorization);
    }
}
