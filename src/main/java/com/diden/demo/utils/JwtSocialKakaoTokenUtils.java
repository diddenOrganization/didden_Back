package com.diden.demo.utils;

import com.diden.demo.config.LazyHolderObject;
import com.diden.demo.error.exception.SocialProcessException;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

import static com.diden.demo.utils.JwtTokenUtil.tokenPrefixCheckAndAdd;
import static com.diden.demo.utils.RetrofitCallUtils.getApiService;

@Slf4j
public class JwtSocialKakaoTokenUtils implements JwtSocialTokenCheckInterface {
  @Deprecated
  @Override
  public JsonObject socialAccessToken(
      @NotBlank(message = "소셜 토큰 값이 존재하지 않습니다.") final String authorization) throws IOException {
    return getApiService(SocialJwtProperties.KAKAO_BASE_URI)
        .getJwtKakaoAccessToken(tokenPrefixCheckAndAdd(authorization))
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
        getApiService(SocialJwtProperties.KAKAO_BASE_URI)
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
            responseErrorBody.get("msg").getAsString(), responseErrorBody.get("code")));
  }
}
