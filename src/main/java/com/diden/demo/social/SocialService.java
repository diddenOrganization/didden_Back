package com.diden.demo.social;

import com.diden.demo.utils.AccountTypeEnum;

import java.io.IOException;

public interface SocialService {
  void socialLoginAndSignupProcess(AccountTypeEnum loginType, String accessToken) throws IOException;
}
