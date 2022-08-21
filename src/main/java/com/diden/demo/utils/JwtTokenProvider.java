package com.diden.demo.utils;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.diden.demo.utils.JwtProperties.SECRET;
import static com.diden.demo.utils.JwtProperties.TOKEN_PREFIX;

@Slf4j
@Component
public final class JwtTokenProvider {

  private static final String ACCESS_KEY = "accessTokenKey";
  private static final String REFRESH_KEY = "refreshTokenKey";

  public static boolean checkAccessToken(String Authorization) {
    try {
      log.debug(":: JwtTokenProvider.checkAccessToken = {} ::", Authorization);

      if (StringUtils.isBlank(Authorization)) {
        throw new BadRequestException("토큰이 존재하지 않습니다.");
      }

      if (!Authorization.startsWith(TOKEN_PREFIX)) {
        throw new TokenException("토큰 형식이 맞지 않습니다.");
      }

      final String token = Authorization.replace(TOKEN_PREFIX, "");

      final Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
      return "whySoSerious".equals(body.getSubject());

    } catch (ExpiredJwtException exp) {
      exp.printStackTrace();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      throw new TokenException("토큰이 잘못됐거나, 존재하지 않습니다.");
    }
  }

  public static boolean checkRefreshToken(String accessToken) {
    log.debug(":: JwtTokenProvider.checkRefreshToken = {} ::", accessToken);

    final String token = accessToken.replace(TOKEN_PREFIX, "");
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new TokenException("리프레쉬 토큰이 잘못됐습니다.");
    }
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
