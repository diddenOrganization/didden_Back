package com.diden.demo.common.jwt;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface JwtSocialTokenCheckInterface {

  @Deprecated
  JsonObject socialAccessToken(String authorization) throws IOException;

  JsonObject socialRefreshToken();

  JsonObject socialExecuteResponse(String authorization) throws IOException;
}
