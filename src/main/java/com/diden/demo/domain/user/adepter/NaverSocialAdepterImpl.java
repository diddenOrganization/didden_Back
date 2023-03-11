package com.diden.demo.domain.user.adepter;

import com.diden.demo.common.config.properties.SocialJwtProperties;
import com.diden.demo.domain.user.exception.SocialProcessException;
import com.diden.demo.common.jwt.JwtSocialNaverTokenUtils;
import com.diden.demo.common.jwt.JwtSocialTokenCheckInterface;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
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
  public UserVoRequest process(final String accessToken) throws IOException {
    // TODO :: 소셜 리프레쉬 토큰 작업
    final JsonObject jsonObject = jwtSocialNaverTokenUtils.socialExecuteResponse(accessToken);

    if (jsonObject.get("error_code") != null && jsonObject.get("error_code").isJsonPrimitive()) {
      throw new SocialProcessException(jsonObject.get("message").getAsString());
    }

    final JsonObject responseNaverAccount =
        jsonObject.getAsJsonObject(SocialJwtProperties.RESPONSE_NAVER_ACCOUNT);

    log.info(":: NaverSocialAdepterImpl.process = {} ::", responseNaverAccount.toString());

    return UserVoRequest.builder()
        .userPassword(accessToken)
        .userEmail(responseNaverAccount.get("email").getAsString())
        .userNickname(responseNaverAccount.get("name").getAsString())
        .userPrivacyConsentEnum(PrivacyConsentEnum.AGREED)
        .userLoginType(AccountTypeEnum.NAVER.getAccountType())
        .userAccessToken(accessToken)
        .build();
  }
}
