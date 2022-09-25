package com.diden.demo.config.adepter;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialNaverTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.diden.demo.utils.AccountTypeEnum.NAVER;

@Slf4j
public class LoginLogoutNaver implements LoginLogoutAdepter {
  @Override
  public boolean supports(String handler) {
    return (NAVER.getAccountType().equals(handler));
  }

  @Override
  public boolean loginProcess(String authorization) throws IOException {
    final JwtSocialTokenCheckInterface naver = new JwtSocialNaverTokenUtils();
    final JsonObject obj = naver.socialAccessToken(authorization);
    log.info("login check : {}", obj);
    log.info("login boolean : {}", obj != null);
    return obj != null;
  }

  @Override
  public boolean logoutProcess(String authorization) {
    throw new SocialProcessException("네이버 로그아웃은 현재 구현되지 않았습니다.");
  }

  @Override
  public String findUserEmail(String authorization) {
    return null;
  }
}
