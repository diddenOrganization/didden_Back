package com.diden.demo.social;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtSocialNaverTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.diden.demo.utils.SocialJwtProperties;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class NaverSocialAdepterImpl implements SocialAdepter {

  private final JwtSocialTokenCheckInterface jwtSocialNaverTokenUtils =
      new JwtSocialNaverTokenUtils();

  @Override
  public boolean supports(final String handler) {
    return (AccountTypeEnum.NAVER.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(final String accessToken) throws IOException {
    // TODO :: 소셜 리프레쉬 토큰 작업
    final JsonObject jsonObject = jwtSocialNaverTokenUtils.socialExecuteResponse(accessToken);

    if (jsonObject.get("error_code").isJsonPrimitive()) {
      throw new SocialProcessException(jsonObject.get("message").getAsString());
    }

    final JsonObject responseNaverAccount =
        jsonObject.getAsJsonObject(SocialJwtProperties.RESPONSE_NAVER_ACCOUNT);

    log.info(":: NaverSocialAdepterImpl.process = {} ::", responseNaverAccount.toString());

    return UserVo.builder()
        .userPassword(accessToken)
        .userEmail(responseNaverAccount.get("email").getAsString())
        .userNickname(responseNaverAccount.get("name").getAsString())
        .userPrivacyConsent(UserVo.PrivacyConsent.AGREED)
        .userLoginType(AccountTypeEnum.NAVER.getAccountType())
        .userAccessToken(accessToken)
        .build();
  }
}
