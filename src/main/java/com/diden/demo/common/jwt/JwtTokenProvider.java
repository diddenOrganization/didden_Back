package com.diden.demo.common.jwt;

import com.diden.demo.common.config.properties.JwtProperties;
import com.diden.demo.common.error.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Slf4j
@Component
public final class JwtTokenProvider {

  public static boolean checkAccessToken(
      @NotBlank(message = "토큰이 존재하지 않습니다.") final String authorization) {
    log.debug(":: JwtTokenProvider.checkAccessToken = {} ::", authorization);

    if (!authorization.startsWith(JwtProperties.TOKEN_PREFIX)) {
      throw new TokenException("토큰 형식이 맞지 않습니다.");
    }

    final String token = authorization.replace(JwtProperties.TOKEN_PREFIX, "");
    final Claims body = Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token).getBody();
    return "whySoSerious".equals(body.getSubject());
  }

  public static boolean checkRefreshToken(
      @NotBlank(message = "토큰이 존재하지 않습니다.") String accessToken) {
    log.debug(":: JwtTokenProvider.checkRefreshToken = {} ::", accessToken);

    final String token = accessToken.replace(JwtProperties.TOKEN_PREFIX, "");
    try {
      Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new TokenException("리프레쉬 토큰이 잘못됐습니다.");
    }
  }

  public static String releaseTokenGetClaims(
      @NotBlank(message = "토큰이 정상적이지 않습니다.") String authorization,
      @NotBlank(message = "토큰이 정상적이지 않습니다.") String claim) {
    log.debug(":: JwtTokenProvider.releaseTokenEmail = {} ::", authorization);

    return Jwts.parser()
        .setSigningKey(JwtProperties.SECRET)
        .parseClaimsJws(authorization.replace(JwtProperties.TOKEN_PREFIX, ""))
        .getBody()
        .get(claim)
        .toString();
  }
}
