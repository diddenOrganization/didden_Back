package com.diden.demo.config.adepter;

import com.diden.demo.error.exception.SocialProcessException;

import static com.diden.demo.utils.AccountTypeEnum.NAVER;

public class LoginNaver implements LoginAdepter {
  @Override
  public boolean supports(String handler) {
    return (NAVER.getAccountType().equals(handler));
  }

  @Override
  public boolean process(String authorization) {
    throw new SocialProcessException("애플 로그인은 현재 구현되지 않았습니다.");
  }
}
