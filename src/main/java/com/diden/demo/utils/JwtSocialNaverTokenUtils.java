package com.diden.demo.utils;

import com.diden.demo.error.exception.TokenException;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

import static com.diden.demo.utils.RetrofitCallUtils.getApiService;

@Slf4j
public class JwtSocialNaverTokenUtils implements JwtSocialTokenCheckInterface {
  @Override
  public JsonObject socialAccessToken(
      @NotBlank(message = "소셜 토큰 값이 존재하지 않습니다.") final String authorization) {
    try {
      final JsonObject resultJsonObject =
          getApiService(SocialJwtProperties.NAVER_BASE_URI)
              .getJwtNaverAccessToken(JwtProperties.TOKEN_PREFIX + authorization)
              .execute()
              .body();

      if (resultJsonObject == null) {
        throw new TokenException("소셜 토큰이 정상적이지 않습니다. 인증앱을 확인해주세요.");
      }

      return resultJsonObject;
    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new TokenException("소셜 토큰에서 알 수 없는 오류가 발생했습니다.");
  }

  @Override
  public JsonObject socialRefreshToken() {
    return null;
  }
}
