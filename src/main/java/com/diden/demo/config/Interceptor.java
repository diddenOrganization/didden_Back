package com.diden.demo.config;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diden.demo.config.vo.TokenVo;
import com.diden.demo.user.controller.UserApiController;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.diden.demo.utils.ParsingJson;

import com.google.gson.JsonObject;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getServerName().startsWith("127") || request.getServerName().startsWith("local")) {
            return true;
        }

        try {
            if ("kakao".equals(request.getHeader("login_type"))) {
                JwtSocialTokenCheckInterface kakao = new JwtSocialKakaoTokenUtils();
                JsonObject obj = kakao.socialAccessToken(request.getHeader("Authorization"));

                return !obj.isJsonNull();
            }


            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
            ParsingJson parsingJson = new ParsingJson();

            boolean result = false;
            String requestToken = request.getHeader("Authorization");
            if (requestToken == null || requestToken.isEmpty()) {
                throw new Exception("토큰값이 존재하지 않습니다.");
            }

            List<Object> payLoad = Arrays.asList(requestToken.split("\\."));
            String body = (String) payLoad.get(1);
            byte[] decodedBytes = Base64.getDecoder().decode(body);
            String jsonPayload = new String(decodedBytes);
            String tokenName = parsingJson.stringToJsonObject(jsonPayload).get("iss").toString();

            TokenVo requestTokenVo = new TokenVo();
            if ("\"acc\"".equals(tokenName)) {
                requestTokenVo.setAccessJwsToken(requestToken);
            } else if ("\"ref\"".equals(tokenName)) {
                requestTokenVo.setRefreshJwsToken(requestToken);
            }

            log.info("{}", requestToken);
            log.debug("{}", requestToken);

            if ("\"acc\"".equals(tokenName)) {
                result = jwtTokenProvider.jwtAccTokenCheck(requestTokenVo);
            } else if ("\"ref\"".equals(tokenName)) {
                result = jwtTokenProvider.jwtRefTokenCheck(requestTokenVo);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
