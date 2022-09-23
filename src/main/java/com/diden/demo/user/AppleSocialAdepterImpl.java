package com.diden.demo.user;

import com.diden.demo.error.exception.SocialProcessException;

import static com.diden.demo.utils.AccountTypeEnum.APPLE;

public class AppleSocialAdepterImpl implements SocialAdepter {
  @Override
  public boolean supports(String handler) {
    return (APPLE.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(String accessToken) {
    throw new SocialProcessException("애플 회원가입은 현재 구현되지 않았습니다.");
  }
}
