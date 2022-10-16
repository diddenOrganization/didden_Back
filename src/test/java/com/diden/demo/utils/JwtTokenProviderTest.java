package com.diden.demo.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JwtTokenProviderTest {

  private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
  String accessToken;

  @BeforeEach
  void setup() {
    final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken("test@test", "test@test.default");
    final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    accessToken =
        JwtProperties.TOKEN_PREFIX
            + jwtTokenUtil.createAccessToken(usernamePasswordAuthenticationToken);
  }

  @Test
  void checkAccessToken() {

    String auth =
        JwtProperties.TOKEN_PREFIX
            + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3aHlTb1NlcmlvdXMiLCJleHAiOjE2NjE5MjE0MDQsInR5cGUiOiJhY2Nlc3NUb2tlbiIsInVzZXJFbWFpbCI6ImJpb21hbjMyMzhAZ21haWwuY29tIn0.xccI_8e-d3ANe7TvI4qZPEk7ZQxZaYQaT6nE8zULP6201LRCjxOYJAm7CFaWv_oJ37WsvYt65WtMBKvs8OqokA";
    boolean b = JwtTokenProvider.checkAccessToken(auth);
    assertThat(b).isTrue();
  }

  @Test
  void checkRefreshToken() {}

  @Test
  void releaseTokenGetClaims() {
    assertThat("test@test")
        .isEqualTo(JwtTokenProvider.releaseTokenGetClaims(accessToken, JwtProperties.USER_EMAIL));
  }
}
