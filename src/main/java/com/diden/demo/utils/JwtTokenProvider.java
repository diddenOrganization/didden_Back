package com.diden.demo.utils;

import com.diden.demo.error.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import static com.diden.demo.utils.JwtProperties.SECRET;
import static com.diden.demo.utils.JwtProperties.TOKEN_PREFIX;

@Component
public final class JwtTokenProvider {

  private static final String ACCESS_KEY = "accessTokenKey";
  private static final String REFRESH_KEY = "refreshTokenKey";

  public static boolean checkAccessToken(String Authorization) {
    try {
      if (Authorization == null || !Authorization.startsWith(TOKEN_PREFIX)) {
        throw new TokenException("토큰이 잘못됐거나, 존재하지 않습니다.");
      }

      final String token = Authorization.replace(TOKEN_PREFIX, "");

      final Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
      System.out.println(body.getExpiration());
      return "whySoSerious".equals(body.getSubject());

    } catch (ExpiredJwtException exp) {
      exp.printStackTrace();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      throw new TokenException("토큰이 잘못됐거나, 존재하지 않습니다.");
    }
  }

  public static void checkRefreshToken(String accessToken) {

    Jwts.parser().setSigningKey(SECRET).parseClaimsJws(accessToken);
  }

  private void validationAuthorizationHeader(String header) {
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      throw new IllegalArgumentException();
    }
  }

  private String extractToken(String authorizationHeader) {
    return authorizationHeader.substring(TOKEN_PREFIX.length());
  }
}
