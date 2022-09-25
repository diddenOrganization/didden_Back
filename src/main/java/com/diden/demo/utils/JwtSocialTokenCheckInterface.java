package com.diden.demo.utils;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface JwtSocialTokenCheckInterface {
  JsonObject socialAccessToken(String authorization) throws IOException;

  JsonObject socialRefreshToken();
}
