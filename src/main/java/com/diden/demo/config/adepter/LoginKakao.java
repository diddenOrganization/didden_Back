package com.diden.demo.config.adepter;

import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginKakao implements LoginAdepter{
    @Override
    public boolean supports(String handler) {
        return ("kakao".equals(handler));
    }

    @Override
    public boolean process(String Authorization) {
        JwtSocialTokenCheckInterface kakao = new JwtSocialKakaoTokenUtils();
        JsonObject obj = kakao.socialAccessToken(Authorization);
        log.info("login check : {}", obj);
        log.info("login boolean : {}", obj != null);
        return obj != null;
    }
}
