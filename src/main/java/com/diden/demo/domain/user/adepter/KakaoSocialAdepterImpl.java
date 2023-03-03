package com.diden.demo.domain.user.adepter;

import com.diden.demo.common.config.properties.SocialJwtProperties;
import com.diden.demo.common.error.exception.SocialProcessException;
import com.diden.demo.common.jwt.JwtSocialKakaoTokenUtils;
import com.diden.demo.common.jwt.JwtSocialTokenCheckInterface;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.diden.demo.domain.user.enums.GenderEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class KakaoSocialAdepterImpl implements SocialAdepter {
  private final JwtSocialTokenCheckInterface jwtSocialKakaoTokenUtils =
      new JwtSocialKakaoTokenUtils();

  @Override
  public boolean supports(String handler) {
    return (AccountTypeEnum.KAKAO.getAccountType().equals(handler));
  }

  @Override
  public UserVoRequest process(final String accessToken) throws IOException {
    // TODO :: 소셜 리프레쉬 토큰 작업
    final JsonObject response = jwtSocialKakaoTokenUtils.socialExecuteResponse(accessToken);
    if (response == null) {
      throw new SocialProcessException("카카오 소셜 토큰이 정상적이지 않습니다. 토큰을 확인해주세요.");
    }

    final JsonObject responseKakaoAccount =
        response.getAsJsonObject(SocialJwtProperties.RESPONSE_KAKAO_ACCOUNT);

    log.info(":: KakaoSocialAdepterImpl.process = {} ::", responseKakaoAccount.toString());

    return UserVoRequest.builder()
        .userPassword(accessToken)
        .userEmail(responseKakaoAccount.get("email").getAsString())
        .userNickname(responseKakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
        .userPrivacyConsent(PrivacyConsentEnum.AGREED)
        .userLoginType(AccountTypeEnum.KAKAO.getAccountType())
        .userAccessToken(accessToken)
        .userGender(
            StringUtils.equals(responseKakaoAccount.get("gender").getAsString(), "male")
                ? GenderEnum.MALE
                : GenderEnum.FEMALE)
        .build();
  }
}
