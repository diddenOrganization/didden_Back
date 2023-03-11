package com.diden.demo.common.jwt;

import com.diden.demo.common.config.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

import static com.diden.demo.common.config.properties.JwtProperties.*;

@Slf4j
public class JwtTokenUtil implements Serializable {

  public String createAccessToken(Authentication authResult) {
    return Jwts.builder()
        .setSubject("whySoSerious")
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .claim("type", JwtProperties.ACCESS_TOKEN)
        .claim(JwtProperties.USER_EMAIL, authResult.getPrincipal().toString())
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public String createRefreshToken(Authentication authResult) {
    return Jwts.builder()
        .setSubject("whySoSerious")
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 10L))
        .claim("type", JwtProperties.REFRESH_TOKEN)
        .claim(JwtProperties.USER_EMAIL, authResult.getPrincipal().toString())
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public static String replaceTokenPrefix(
      @NotBlank(message = "토큰이 존재하지 않습니다.") final String authorization) {
    return authorization.replace(TOKEN_PREFIX, "");
  }

  public static String tokenPrefixCheckAndReplace(final String accessToken) {
    if (accessToken.startsWith(TOKEN_PREFIX)) { // 있으면
      return accessToken.replace(TOKEN_PREFIX, "");
    }

    return accessToken;
  }

  public static String tokenPrefixCheckAndAdd(final String accessToken) {
    if (!accessToken.startsWith(TOKEN_PREFIX)) { // 없으면
      return TOKEN_PREFIX + accessToken;
    }

    return accessToken;
  }
}
