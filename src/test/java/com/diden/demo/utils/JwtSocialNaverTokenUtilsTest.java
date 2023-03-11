package com.diden.demo.utils;

import com.diden.demo.TestStartConfig;
import com.diden.demo.api.user.dto.request.UserDtoRequest;
import com.diden.demo.common.config.properties.SocialJwtProperties;
import com.diden.demo.common.jwt.JwtSocialNaverTokenUtils;
import com.diden.demo.common.jwt.JwtSocialTokenCheckInterface;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.service.UserService;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.diden.demo.domain.user.vo.response.UserVo;
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

    final UserDtoRequest userDtoRequest =
            UserDtoRequest.builder()
            .userPassword(naverAccessToken)
            .userEmail(responseNaverAccount.get("email").getAsString())
            .userNickname(responseNaverAccount.get("name").getAsString())
            .userPrivacyConsent(PrivacyConsentEnum.AGREED)
            .userLoginType(AccountTypeEnum.NAVER.getAccountType())
            .userAccessToken(naverAccessToken)
            .build();

    UserVoRequest userVoRequest = UserDtoRequest.transportDataByUserDtoRequest(userDtoRequest);
    log.info(":: result == {} ::", userDtoRequest);

    userService.userInsert(userVoRequest);
    final UserVo findUser = userService.userInfo(userVoRequest);

    assertThat(findUser.getUserNickname()).isNotBlank().isEqualTo(userVoRequest.getUserNickname());
  }

  @Test
  void socialRefreshToken() {}
}
