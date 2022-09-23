package com.diden.demo.config.adepter;

import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginDefault implements LoginAdepter {

  @Override
  public boolean supports(String handler) {
    return (AccountTypeEnum.DEFAULT.getAccountType().equals(handler));
  }

  @Override
  public boolean process(String authorization) {
    return JwtTokenProvider.checkAccessToken(authorization);
  }
}
