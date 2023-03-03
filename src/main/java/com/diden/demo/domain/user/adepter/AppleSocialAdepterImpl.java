package com.diden.demo.domain.user.adepter;

import com.diden.demo.common.error.exception.SocialProcessException;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import org.springframework.stereotype.Service;

import static com.diden.demo.domain.user.enums.AccountTypeEnum.APPLE;

@Service
public class AppleSocialAdepterImpl implements SocialAdepter {
  @Override
  public boolean supports(String handler) {
    return (APPLE.getAccountType().equals(handler));
  }

  @Override
  public UserVoRequest process(String accessToken) {
    throw new SocialProcessException("애플 회원가입은 현재 구현되지 않았습니다.");
  }
}
