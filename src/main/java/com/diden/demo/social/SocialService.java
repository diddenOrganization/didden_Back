package com.diden.demo.social;

import com.diden.demo.utils.AccountTypeEnum;

import java.io.IOException;

public interface SocialService {

  void socialAccessTokenUpdate(String accessToken, String loginType) throws IOException;
  void socialSignup(String loginType, String accessToken) throws IOException;
  void socialLoginAndSignupProcess(AccountTypeEnum loginType, String accessToken) throws IOException;
}
