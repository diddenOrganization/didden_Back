package com.diden.demo.config;

import com.diden.demo.error.exception.TokenException;
import com.diden.demo.user.UserService;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      final UserVo userVo =
          LazyHolderObject.getGson()
              .fromJson(new InputStreamReader(request.getInputStream()), UserVo.class);

      if (userService.existsUserEmail(userVo.getUserEmail())) {
        return new UsernamePasswordAuthenticationToken(
            userVo.getUserEmail(), userVo.getUserPassword());
      } else {
        throw new TokenException("계정이 존재하지 않습니다.");
      }

    } catch (IOException io) {
      io.printStackTrace();
    }
    throw new TokenException("로그인이 실패하였습니다.");
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) {
    final String accessToken = jwtTokenUtil.createAccessToken(authResult);
    final String refreshToken = jwtTokenUtil.createRefreshToken(authResult);

    final UserVo userVo =
        UserVo.builder()
            .userEmail(authResult.getPrincipal().toString())
            .userRefreshToken(refreshToken)
            .build();

    userService.userRefTokenUpdate(userVo);
    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
  }
}
