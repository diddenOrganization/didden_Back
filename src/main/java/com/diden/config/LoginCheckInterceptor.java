package com.diden.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diden.config.vo.TokenVo;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        boolean result = false;
        String requestAccToken = request.getHeader("Authorization");

        TokenVo requestToken = new TokenVo();
        requestToken.setAccessJwsToken(requestAccToken);
        // requestToken.setRefreshJwsToken(requestRefToken);
        try {
            Claims claims = jwtTokenProvider.parseJwtToken(requestToken);
            result = (boolean) claims.get("result");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
