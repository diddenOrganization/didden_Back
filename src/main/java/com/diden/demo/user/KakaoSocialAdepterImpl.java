package com.diden.demo.user;

import com.diden.demo.error.exception.SocialProcessException;
import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtSocialKakaoTokenUtils;
import com.diden.demo.utils.JwtSocialTokenCheckInterface;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import static com.diden.demo.utils.AccountTypeEnum.KAKAO;

@Service
public class KakaoSocialAdepterImpl implements SocialAdepter {
  private final JwtSocialTokenCheckInterface jwtSocialKakaoTokenUtils =
      new JwtSocialKakaoTokenUtils();

  @Override
  public boolean supports(String handler) {
    return (KAKAO.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(final JsonObject param) {
    try {
      final String accessToken = param.get(JwtProperties.ACCESS_TOKEN).getAsString();
      final String refreshToken = param.get(JwtProperties.REFRESH_TOKEN).getAsString();

      final JsonObject jsonObject = jwtSocialKakaoTokenUtils.socialAccessToken(accessToken);
      final JsonObject kakaoAccount = jsonObject.getAsJsonObject("kakao_account");

      return UserVo.builder()
          .userId(param.get("id").getAsString())
          .userPassword(accessToken)
          .userEmail(kakaoAccount.get("email").getAsString())
          .userName(kakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
          .userNickname(kakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
          .userPrivacyConsent("Y")
          .userSocialLoginType("kakao")
          .userRefreshToken(refreshToken)
          .userAccessToken(accessToken)
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw new SocialProcessException("카카오 소셜 회원가입 에러 발생");
    }
  }
}
