package com.diden.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diden.config.vo.TokenVo;

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
        // String requestAccToken = request.getParameter("token_acc");
        // String requestRefToken = request.getParameter("token_ref");
        String requestAccToken = request.getHeader("token_acc");
        String requestRefToken = request.getHeader("token_ref");

        TokenVo requestToken = new TokenVo();
        requestToken.setAccessJwsToken(requestAccToken);
        requestToken.setRefreshJwsToken(requestRefToken);
        System.out.println("Acc : " + requestAccToken);
        System.out.println("Ref : " + requestRefToken);
        try {
            Claims claims = jwtTokenProvider.parseJwtToken(requestToken);
            System.out.println("claims : " + claims);
            result = (boolean) claims.get("result");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
