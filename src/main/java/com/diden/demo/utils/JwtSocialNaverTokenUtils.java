package com.diden.demo.utils;

import com.diden.demo.config.LazyHolderObject;
import com.diden.demo.error.exception.SocialProcessException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okio.BufferedSource;
import retrofit2.Response;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

import static com.diden.demo.utils.JwtTokenUtil.tokenPrefixCheckAndAdd;
import static com.diden.demo.utils.RetrofitCallUtils.getApiService;

@Slf4j
public class JwtSocialNaverTokenUtils implements JwtSocialTokenCheckInterface {
  @Deprecated
  @Override
  public JsonObject socialAccessToken(
      @NotBlank(message = "소셜 토큰 값이 존재하지 않습니다.") final String authorization) throws IOException {
    Response<JsonObject> executeResponse =
        getApiService(SocialJwtProperties.NAVER_BASE_URI)
            .getJwtNaverAccessToken(tokenPrefixCheckAndAdd(authorization))
            .execute();
    BufferedSource source =
        Response.error(executeResponse.code(), executeResponse.errorBody()).errorBody().source();

    // String test = new String(source.readByteArray(), StandardCharsets.UTF_8);
    JsonObject jsonObject = new Gson().fromJson(source.readUtf8(), JsonObject.class);

    System.out.println(jsonObject);

    return getApiService(SocialJwtProperties.NAVER_BASE_URI)
        .getJwtNaverAccessToken(tokenPrefixCheckAndAdd(authorization))
        .execute()
        .body();
  }

  @Override
  public JsonObject socialRefreshToken() {
    return null;
  }

  @Override
  public JsonObject socialExecuteResponse(String authorization) throws IOException {
    final Response<JsonObject> objectResponse =
        getApiService(SocialJwtProperties.NAVER_BASE_URI)
            .getJwtKakaoAccessToken(tokenPrefixCheckAndAdd(authorization))
            .execute();

    if (objectResponse.isSuccessful()) {
      return objectResponse.body();
    }

    final JsonObject responseErrorBody =
        LazyHolderObject.getGson()
            .fromJson(objectResponse.errorBody().source().readUtf8(), JsonObject.class);

    throw new SocialProcessException(
        String.format(
            "message : %s, code : %s",
            responseErrorBody.get("message").getAsString(), responseErrorBody.get("resultcode")));
  }
}
