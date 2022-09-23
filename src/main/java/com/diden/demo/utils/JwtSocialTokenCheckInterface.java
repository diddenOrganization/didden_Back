package com.diden.demo.utils;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface JwtSocialTokenCheckInterface {
  default JsonObject socialAccessToken(String authorization) throws IOException {
    return null;
  }

  default JsonObject socialRefreshToken() {
    return null;
  }
}
