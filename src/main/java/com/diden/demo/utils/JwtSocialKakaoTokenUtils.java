package com.diden.demo.utils;

import com.diden.demo.error.exception.TokenException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

@Slf4j
@Component
public class JwtSocialKakaoTokenUtils implements JwtSocialTokenCheckInterface {

  /**
   * 리팩토링 필요
   *
   * @return JwtSocialTokenRetrofitCallInterface
   */
  public static JwtSocialTokenRetrofitCallInterface getApiService() {
    return getInstance().create(JwtSocialTokenRetrofitCallInterface.class);
  }

  /**
   * 리팩토링 필요
   *
   * @return Retrofit
   */
  private static Retrofit getInstance() {
    Gson gson = new GsonBuilder().setLenient().create();

    return new Retrofit.Builder()
        .baseUrl(SocialJwtProperties.KAKAO_BASE_URI)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  @Override
  public JsonObject socialAccessToken(
      @NotBlank(message = "소셜 토큰 값이 존재하지 않습니다.") final String authorization) {
    try {
      final JsonObject resultJsonObject = JwtSocialKakaoTokenUtils.getApiService()
              .getJwtKakaoAccessToken(JwtProperties.TOKEN_PREFIX + authorization)
              .execute()
              .body();

      if(resultJsonObject == null) {
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
    return JwtSocialTokenCheckInterface.super.socialRefreshToken();
  }
}
