package com.diden.demo.config.adepter;

import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtTokenProvider;
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
