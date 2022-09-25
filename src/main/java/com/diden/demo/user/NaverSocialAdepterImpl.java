package com.diden.demo.user;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtSocialNaverTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.diden.demo.utils.SocialJwtProperties;
import com.google.gson.JsonObject;

public class NaverSocialAdepterImpl implements SocialAdepter {

  private final JwtSocialTokenCheckInterface jwtSocialNaverTokenUtils =
      new JwtSocialNaverTokenUtils();

  @Override
  public boolean supports(final String handler) {
    return (AccountTypeEnum.NAVER.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(final String accessToken) {
    try {
      // TODO :: 소셜 리프레쉬 토큰 작업
      final JsonObject response = jwtSocialNaverTokenUtils.socialAccessToken(accessToken);
      final JsonObject responseNaverAccount =
          response.getAsJsonObject(SocialJwtProperties.RESPONSE_NAVER_ACCOUNT);

      return UserVo.builder()
          .userPassword(accessToken)
          .userEmail(responseNaverAccount.get("email").getAsString())
          .userNickname(responseNaverAccount.get("name").getAsString())
          .userPrivacyConsent(UserVo.PrivacyConsent.AGREED)
          .userLoginType(AccountTypeEnum.NAVER.getAccountType())
          .userAccessToken(accessToken)
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw new SocialProcessException("네이버 소셜 회원가입 에러 발생");
    }
  }
}
