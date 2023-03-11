package com.diden.demo.common.security.handler;

import com.diden.demo.common.security.filter.JwtAuthenticationFilter;
import com.diden.demo.common.security.filter.JwtAuthorizationFilter;
import com.diden.demo.domain.user.service.UserService;
import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, JwtAuthenticationFilter> {

    private final UserService userService;

    public CustomAuthenticationDetailsSource(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JwtAuthenticationFilter buildDetails(HttpServletRequest context) {
        System.out.println("확인 CustomAuthenticationDetailsSource");
        return new JwtAuthenticationFilter(userService);
    }
}
