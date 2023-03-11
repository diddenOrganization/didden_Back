package com.diden.demo.domain.user.service;

import com.diden.demo.domain.user.enums.AccountTypeEnum;

import java.io.IOException;

public interface SocialService {
  void socialLoginAndSignupProcess(final AccountTypeEnum loginType, final String accessToken) throws IOException;
}
