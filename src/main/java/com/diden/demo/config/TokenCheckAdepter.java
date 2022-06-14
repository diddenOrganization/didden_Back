package com.diden.demo.config;


import com.diden.demo.config.adepter.*;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TokenCheckAdepter {
    private final static List<LoginAdepter> loginMapping = new ArrayList<>();

    protected TokenCheckAdepter() {
        loginMapping.add(new LoginKakao());
        loginMapping.add(new LoginApple());
        loginMapping.add(new LoginNaver());
        loginMapping.add(new LoginDefault());
    }

    protected JsonObject tokenCheckMethod(HttpServletRequest request) {
        final LoginAdepter loginAdepter;
        final String loginType = request.getHeader("login_type");
        final String Authorization = request.getHeader("Authorization");

        for (LoginAdepter adepter : loginMapping) {
            if(adepter.supports(loginType)){
                loginAdepter = adepter;
                final boolean result = loginAdepter.process(Authorization);
                return jsonObjectResult(result);
            }
        }

        throw new IllegalArgumentException("로그인 타입을 설정하지 않았거나 잘못 설정하였습니다. 다시 설정해주세요.");
    }

    private JsonObject jsonObjectResult(final boolean result) {
        JsonObject obj = new JsonObject();
        obj.addProperty("result", result);
        return obj;
    }
}
