package com.diden.demo.common.adepter;

import com.diden.demo.common.config.properties.JwtProperties;
import com.diden.demo.common.jwt.JwtTokenProvider;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginLogoutDefault implements LoginLogoutAdepter {

  @Override
  public boolean supports(String handler) {
    return (AccountTypeEnum.DEFAULT.getAccountType().equals(handler));
  }

  @Override
  public boolean loginProcess(String authorization) {
    return JwtTokenProvider.checkAccessToken(authorization);
  }

  @Override
  public boolean logoutProcess(String authorization) {
    return false;
  }

  @Override
  public String findUserEmail(String authorization) {
    return JwtTokenProvider.releaseTokenGetClaims(authorization, JwtProperties.USER_EMAIL);
  }
}
