package com.diden.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diden.config.vo.TokenVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        boolean result = false;
        String requestAccToken = request.getParameter("token_acc");
        String requestRefToken = request.getParameter("token_ref");

        TokenVo requestToken = new TokenVo();
        requestToken.setAccessJwsToken(requestAccToken);
        requestToken.setRefreshJwsToken(requestRefToken);
        try {
            Claims claims = jwtTokenProvider.parseJwtToken(requestToken);
            result = (boolean) claims.get("result");
        } catch (Exception e) {

        }

        return result;
    }
}
