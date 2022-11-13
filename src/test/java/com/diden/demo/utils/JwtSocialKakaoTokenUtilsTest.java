package com.diden.demo.utils;

import com.diden.demo.TestStartConfig;
import com.diden.demo.config.LazyHolderObject;
import com.diden.demo.user.UserService;
import com.diden.demo.user.UserVo;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
class JwtSocialKakaoTokenUtilsTest extends TestStartConfig {
  @Autowired ResourceLoader resourceLoader;
  @Autowired UserService userService;
  final JwtSocialTokenCheckInterface jwtSocialKakaoTokenUtils = new JwtSocialKakaoTokenUtils();

  /** https://developers.kakao.com/tool/rest-api/open/get/v2-user-me 카카오 토큰 발급은 여기서 */
  private final String kakaoAccessToken =
      "GO6ImMJKpQxSUB6XNR_7Bed2yoyXSFx1t1081cxVCilwngAAAYQzXjAH";
  private final String loginEmail = "ohwig1@kakao.com";

  @Test
  @Rollback
  @DisplayName("소셜 - 카카오 로그인")
  void socialKakaoLogin(){


  }

  @Test
  @Rollback
  @DisplayName("소셜 - 카카오 소셜 회원가입")
  void socialKakaoAccessToken() throws IOException {
    final JsonObject response = jwtSocialKakaoTokenUtils.socialAccessToken(kakaoAccessToken);
    final JsonObject responseKakaoAccount =
        response.getAsJsonObject(SocialJwtProperties.RESPONSE_KAKAO_ACCOUNT);

    log.info(":: 카카오 소셜 인증 == {} ::", response);
    log.info(":: 카카오 소셜 어카운트 == {} ::", responseKakaoAccount);

    final UserVo userVo =
        UserVo.builder()
            .userPassword(kakaoAccessToken)
            .userEmail(responseKakaoAccount.get("email").getAsString())
            .userNickname(
                responseKakaoAccount.getAsJsonObject("profile").get("nickname").getAsString())
            .userPrivacyConsent(UserVo.PrivacyConsent.AGREED)
            .userLoginType(AccountTypeEnum.KAKAO.getAccountType())
            .userAccessToken(kakaoAccessToken)
            .build();

    log.info(":: result == {} ::", userVo);
    log.info(":: gson == {} ::", LazyHolderObject.getGson().toJson(userVo));

    userService.userInsert(userVo);
    final UserVo findUser = userService.userInfo(userVo);

    log.info(":: result == {} ::", findUser);

    assertThat(findUser.getUserNickname()).isNotBlank().isEqualTo(userVo.getUserNickname());
  }
}
