package com.diden.demo.config.adepter;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginLogoutKakao implements LoginLogoutAdepter {
  private final JwtSocialTokenCheckInterface jwtSocialKakaoTokenUtils =
      new JwtSocialKakaoTokenUtils();

  @Override
  public boolean supports(final String handler) {
    return AccountTypeEnum.KAKAO.getAccountType().equals(handler);
  }

  @Override
  public boolean loginProcess(final String authorization) throws IOException {
    return jwtSocialKakaoTokenUtils.socialExecuteResponse(authorization).isJsonObject();
  }

  @Override
  public boolean logoutProcess(String authorization) {
    throw new SocialProcessException("카카오 로그아웃은 현재 구현되지 않았습니다.");
  }

  @Override
  public String findUserEmail(String authorization) {
    return null;
  }
}
