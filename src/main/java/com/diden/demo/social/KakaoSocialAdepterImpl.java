package com.diden.demo.social;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.diden.demo.utils.SocialJwtProperties;
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
  public UserVo process(final String accessToken) throws IOException {
    // TODO :: 소셜 리프레쉬 토큰 작업
    final JsonObject response = jwtSocialKakaoTokenUtils.socialExecuteResponse(accessToken);
    if (response == null) {
      throw new SocialProcessException("카카오 소셜 토큰이 정상적이지 않습니다. 토큰을 확인해주세요.");
    }

    final JsonObject responseKakaoAccount =
        response.getAsJsonObject(SocialJwtProperties.RESPONSE_KAKAO_ACCOUNT);

    log.info(":: KakaoSocialAdepterImpl.process = {} ::", responseKakaoAccount.toString());

    return UserVo.builder()
        .userPassword(accessToken)
        .userEmail(responseKakaoAccount.get("email").getAsString())
        .userNickname(responseKakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
        .userPrivacyConsent(UserVo.PrivacyConsent.AGREED)
        .userLoginType(AccountTypeEnum.KAKAO.getAccountType())
        .userAccessToken(accessToken)
        .userGender(
            StringUtils.equals(responseKakaoAccount.get("gender").getAsString(), "male")
                ? UserVo.Gender.MALE
                : UserVo.Gender.FEMALE)
        .build();
  }
}
