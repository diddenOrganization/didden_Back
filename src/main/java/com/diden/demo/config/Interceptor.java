package com.diden.demo.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Interceptor implements HandlerInterceptor {
    final private TokenCheckAdepter tokenCheckAdepter = new TokenCheckAdepter();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final boolean result;
        log.info("Authorization : Interceptor");
        if (request.getServerName().startsWith("127") || request.getServerName().startsWith("local")) return true;

        final String loginType = request.getHeader("login_type");
        final String Authorization = request.getHeader("Authorization");

        final JsonObject resultObject = this.tokenCheckAdepter.tokenCheckMethod(loginType, Authorization);
        result = resultObject.get("result").getAsBoolean();

        if(result) return true;
        else {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().print(resultObject.toString());
            return false;
        }
    }
}
