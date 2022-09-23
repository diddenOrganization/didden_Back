package com.diden.demo.user;

import com.diden.demo.error.exception.SocialProcessException;

import static com.diden.demo.utils.AccountTypeEnum.NAVER;

public class NaverSocialAdepterImpl implements SocialAdepter {
  @Override
  public boolean supports(String handler) {
    return (NAVER.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(String accessToken) {
    throw new SocialProcessException("네이버 회원가입은 현재 구현되지 않았습니다.");
  }
}
