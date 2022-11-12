package com.diden.demo.utils;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

import static com.diden.demo.utils.RetrofitCallUtils.getApiService;

@Slf4j
@Component
public class JwtSocialKakaoTokenUtils implements JwtSocialTokenCheckInterface {
  @Override
  public JsonObject socialAccessToken(
      @NotBlank(message = "소셜 토큰 값이 존재하지 않습니다.") final String authorization) throws IOException {
    return getApiService(SocialJwtProperties.KAKAO_BASE_URI)
        .getJwtKakaoAccessToken(JwtProperties.TOKEN_PREFIX + authorization)
        .execute()
        .body();
  }

  @Override
  public JsonObject socialRefreshToken() {
    return null;
  }
}
