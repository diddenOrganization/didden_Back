package com.diden.demo.social;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.user.UserVo;
import org.springframework.stereotype.Service;

import static com.diden.demo.utils.AccountTypeEnum.APPLE;

@Service
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
