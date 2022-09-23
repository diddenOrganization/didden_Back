package com.diden.demo.config.adepter;

import com.diden.demo.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;

import static com.diden.demo.utils.AccountTypeEnum.DEFAULT;

@Slf4j
public class LoginDefault implements LoginAdepter {

  @Override
  public boolean supports(String handler) {
    return (DEFAULT.getAccountType().equals(handler));
  }

  @Override
  public boolean process(String Authorization) {
    if(JwtTokenProvider.checkAccessToken(Authorization)){
      return JwtTokenProvider.checkRefreshToken(Authorization);
    } else {
      return false;
    }
  }
}
