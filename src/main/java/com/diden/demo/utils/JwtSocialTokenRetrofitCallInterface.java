package com.diden.demo.utils;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import javax.validation.constraints.NotNull;

public interface JwtSocialTokenRetrofitCallInterface {

  @GET("/v2/user/me")
  Call<JsonObject> getJwtKakaoAccessToken(
      @NotNull @Header(JwtProperties.HEADER_STRING) String auth);
}
