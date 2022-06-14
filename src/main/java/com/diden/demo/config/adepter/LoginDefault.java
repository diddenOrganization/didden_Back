package com.diden.demo.config.adepter;

import com.diden.demo.config.JwtTokenProvider;
import com.diden.demo.config.TokenException;
import com.diden.demo.config.vo.TokenVo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
public class LoginDefault implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return ("default".equals(handler));
    }

    @Override
    public boolean process(String Authorization) {
        final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        boolean result = false;

        try {
            if (Authorization == null || Authorization.isEmpty()) throw new TokenException("토큰값이 존재하지 않습니다.");

            final List<String> payLoad = Arrays.asList(Authorization.split("\\."));
            final String body = payLoad.get(1);

            final byte[] decodedBytes = Base64.getDecoder().decode(body);
            final String jsonPayload = new String(decodedBytes);

            final JsonObject stringToJsonObjectTokenName = new Gson().fromJson(jsonPayload, JsonObject.class);
            final String iss = stringToJsonObjectTokenName.get("iss").getAsString();

            final TokenVo requestTokenVo = new TokenVo();
            if ("acc".equals(iss)) requestTokenVo.setAccessJwsToken(Authorization);
            else if ("ref".equals(iss)) requestTokenVo.setRefreshJwsToken(Authorization);

            log.info("Authorization : {}", Authorization);

            if ("acc".equals(iss)) result = jwtTokenProvider.jwtAccTokenCheck(requestTokenVo);
            else if ("ref".equals(iss)) result = jwtTokenProvider.jwtRefTokenCheck(requestTokenVo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
