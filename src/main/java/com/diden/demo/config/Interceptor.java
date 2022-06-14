package com.diden.demo.config;

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
        log.info("========================================== Authorization : Interceptor : Start ==========================================");

        final boolean result;
        if (request.getServerName().startsWith("127") || request.getServerName().startsWith("local")) return true;

        final JsonObject resultObject = this.tokenCheckAdepter.tokenCheckMethod(request);
        result = resultObject.get("result").getAsBoolean();

        log.info("========================================== Authorization : Interceptor : End ==========================================");

        if(result) return true;
        else {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().print(resultObject.toString());
            return false;
        }
    }
}
