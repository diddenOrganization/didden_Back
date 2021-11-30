package com.diden.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(request);
        System.out.println(response);
        System.out.println(handler);
        // String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Claims claims = jwtTokenProvider.parseJwtToken("");

        return true;
    }
}
