package com.diden.demo.common.adepter;

import com.diden.demo.domain.user.exception.SocialProcessException;
import com.diden.demo.common.jwt.JwtSocialNaverTokenUtils;
import com.diden.demo.common.jwt.JwtSocialTokenCheckInterface;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.diden.demo.domain.user.enums.AccountTypeEnum.NAVER;

@Slf4j
public class LoginLogoutNaver implements LoginLogoutAdepter {
  final JwtSocialTokenCheckInterface jwtSocialNaverTokenUtils = new JwtSocialNaverTokenUtils();

  @Override
  public boolean supports(String handler) {
    return (NAVER.getAccountType().equals(handler));
  }

  /**
   *
   * <a href="https://developers.naver.com/docs/common/openapiguide/errorcode.md">네이버 에러 코드</a>
   * @param authorization
   * @return
   * @throws IOException
   */
  @Override
  public boolean loginProcess(String authorization) throws IOException {
    final JsonObject jsonObject =
        jwtSocialNaverTokenUtils.socialExecuteResponse(authorization);

    if(jsonObject.get("error_code") != null && jsonObject.get("error_code").isJsonPrimitive()) {
      throw new SocialProcessException(jsonObject.get("message").getAsString());
    }

    return true;
  }

  @Override
  public boolean logoutProcess(String authorization) {
    throw new SocialProcessException("네이버 로그아웃은 현재 구현되지 않았습니다.");
  }

  @Override
  public String findUserEmail(String authorization) {
    return null;
  }
}
