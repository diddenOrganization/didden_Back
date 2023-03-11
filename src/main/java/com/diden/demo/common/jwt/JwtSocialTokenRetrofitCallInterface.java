package com.diden.demo.common.jwt;

import com.diden.demo.common.config.properties.JwtProperties;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import javax.validation.constraints.NotNull;

public interface JwtSocialTokenRetrofitCallInterface {
  @GET("/v2/user/me")
  Call<JsonObject> getJwtKakaoAccessToken(
      @NotNull(message = "인증 토큰이 존재하지 않습니다.") @Header(JwtProperties.AUTHORIZATION)
          String authorization);

  @GET("/v1/nid/me")
  Call<JsonObject> getJwtNaverAccessToken(
      @NotNull(message = "인증 토큰이 존재하지 않습니다.") @Header(JwtProperties.AUTHORIZATION)
          String authorization);
}
