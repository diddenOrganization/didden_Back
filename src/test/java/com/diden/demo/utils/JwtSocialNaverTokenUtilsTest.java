package com.diden.demo.utils;

import com.diden.demo.TestStartConfig;
import com.diden.demo.user.UserService;
import com.diden.demo.user.UserVo;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
class JwtSocialNaverTokenUtilsTest extends TestStartConfig {
  @Autowired UserService userService;
  private final JwtSocialTokenCheckInterface jwtSocialNaverTokenUtils =
      new JwtSocialNaverTokenUtils();

  //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=7wEVAYsChBxD4sQAPzzJ&state=130&redirect_uri=http://localhost:8080/naver-redirect


  /**
   * 네이버 엑세스 토큰
   *
   * <p>https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=7wEVAYsChBxD4sQAPzzJ&redirect_uri=http://localhost:8080/info/naver/redirect&state=130
   */
  private final String naverAccessToken =
      "AAAAOumpWX76h1R2gXT0kmj5QDDVTEmzjPgNIQLAKXWyCDHZhs-HhIyADsKgAnorOuTlsWkjHQ9_JED6s8KZ8wLCW5A";

  @Test
  @Rollback
  @DisplayName("소셜 회원가입 - 네이버 회원가입")
  void socialNaverAccessToken() throws IOException {
    final JsonObject response =
        jwtSocialNaverTokenUtils.socialAccessToken(naverAccessToken);
    final JsonObject responseNaverAccount =
        response.getAsJsonObject(SocialJwtProperties.RESPONSE_NAVER_ACCOUNT);

    log.info(":: 카카오 소셜 인증 == {} ::", response);
    log.info(":: 카카오 소셜 어카운트 == {} ::", responseNaverAccount);

    final UserVo userVo =
        UserVo.builder()
            .userPassword(naverAccessToken)
            .userEmail(responseNaverAccount.get("email").getAsString())
            .userNickname(responseNaverAccount.get("name").getAsString())
            .userPrivacyConsent(UserVo.PrivacyConsent.AGREED)
            .userLoginType(AccountTypeEnum.NAVER.getAccountType())
            .userAccessToken(naverAccessToken)
            .build();

    log.info(":: result == {} ::", userVo);

    userService.userInsert(userVo);
    final UserVo findUser = userService.userInfo(userVo);

    assertThat(findUser.getUserNickname()).isNotBlank().isEqualTo(userVo.getUserNickname());
  }

  @Test
  void socialRefreshToken() {}
}
