package com.diden.demo.config;


import com.diden.demo.config.vo.TokenVo;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
public class TokenCheckAdepter {
    protected JsonObject tokenCheckMethod(@NotNull final String loginType, @NotNull final String Authorization) {
        final boolean result;
        if ("kakao".equals(loginType)) result = kakaoLogin(Authorization);
        else if ("naver".equals(loginType)) result = naverLogin(Authorization);
        else if ("apple".equals(loginType)) result = appleLogin(Authorization);
        else result = defaultLogin(Authorization);

        JsonObject obj = new JsonObject();
        obj.addProperty("result", result);

        return obj;
    }

    private boolean defaultLogin(final String Authorization) {
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

    private boolean kakaoLogin(final String Authorization) {
        JwtSocialTokenCheckInterface kakao = new JwtSocialKakaoTokenUtils();
        JsonObject obj = kakao.socialAccessToken(Authorization);
        log.info("login check : {}", obj);
        log.info("login boolean : {}", obj != null);
        return obj != null;
    }

    private boolean naverLogin(final String Authorization) {
        return false;
    }

    private boolean appleLogin(final String Authorization) {
        return false;
    }


}
