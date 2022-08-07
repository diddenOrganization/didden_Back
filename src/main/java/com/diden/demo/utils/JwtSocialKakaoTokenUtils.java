package com.diden.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.validation.constraints.NotNull;

@Component
@Slf4j
public class JwtSocialKakaoTokenUtils implements JwtSocialTokenCheckInterface {
  private final String base_url = "https://kapi.kakao.com";

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
        .baseUrl(new JwtSocialKakaoTokenUtils().base_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  @Override
  public JsonObject socialAccessToken(@NotNull String auth) {
    JsonObject body = null;
    Call<JsonObject> decodeToken =
        JwtSocialKakaoTokenUtils.getApiService()
            .getJwtKakaoAccessToken(
                new StringBuffer().append(JwtProperties.TOKEN_PREFIX).append(auth).toString());
    try {
      body = decodeToken.execute().body();
      log.info("{}", body);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return body;
  }

  @Override
  public JsonObject socialRefreshToken() {
    return JwtSocialTokenCheckInterface.super.socialRefreshToken();
  }
}
