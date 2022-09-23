package com.diden.demo.utils;

public final class JwtProperties {
  public static final String SECRET = "didden";
  public static final int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String ACCESS_TOKEN = "accessToken";
  public static final String REFRESH_TOKEN = "refreshToken";
  public static final String LOGIN_TYPE = "login_type";
  public static final String USER_EMAIL = "userEmail";
}
