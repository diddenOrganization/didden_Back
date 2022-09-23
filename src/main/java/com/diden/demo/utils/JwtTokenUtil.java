package com.diden.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Date;

import static com.diden.demo.utils.JwtProperties.EXPIRATION_TIME;
import static com.diden.demo.utils.JwtProperties.SECRET;

public class JwtTokenUtil implements Serializable {

  public String createAccessToken(Authentication authResult) {

    return Jwts.builder()
        .setSubject("whySoSerious")
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .claim("type", JwtProperties.ACCESS_TOKEN)
        .claim("userEmail", authResult.getPrincipal().toString())
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public String createRefreshToken(Authentication authResult) {

    return Jwts.builder()
        .setSubject("whySoSerious")
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 10L))
        .claim("type", JwtProperties.REFRESH_TOKEN)
        .claim("userEmail", authResult.getPrincipal().toString())
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  /*public TokenVo makeJwtAccToken(UserVo userVo) {
      TokenVo tokenVo = new TokenVo();
      String accessToken = createToken(ACCESS_KEY, userVo, 2L);

      tokenVo.setAccessJwsToken(accessToken);
      return tokenVo;
  }

  public TokenVo makeJwtRefToken(UserVo userVo) {
      TokenVo tokenVo = new TokenVo();
      String refreshToken = createToken(REFRESH_KEY, userVo, 168L);

      tokenVo.setRefreshJwsToken(refreshToken);
      return tokenVo;
  }

  private String createToken(String tokenKey, UserVo userVo, Long expHours) {
      Date now = new Date();
      Date ext = new Date(); // 토큰 만료 시간
      Long expiredTime = 1000 * 60L * 60L * expHours;
      String issuer = "";

      if (ACCESS_KEY.equals(tokenKey)) issuer = "acc";
      else if (REFRESH_KEY.equals(tokenKey)) issuer = "ref";

      ext.setTime(ext.getTime() + expiredTime);

      return Jwts.builder()
              .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
              .setIssuer(issuer)
              .setSubject("TEST")
              .claim("result", true)
              .claim("userVo", userVo)
              .setIssuedAt(now)
              .setExpiration(ext)
              .signWith(SignatureAlgorithm.HS512, tokenKey).compact();
  }*/
}
