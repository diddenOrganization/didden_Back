package com.diden.demo.social;

import java.io.IOException;

public interface SocialService {

  void socialAccessTokenUpdate(String accessToken, String loginType) throws IOException;
  void socialSignup(String loginType, String accessToken);
}
