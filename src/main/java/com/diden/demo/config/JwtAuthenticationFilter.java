package com.diden.demo.config;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import com.diden.demo.user.UserService;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.HttpResponse;
import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

      if (userService.userCheck(userVo) == 0) {
        throw new BadRequestException("계정이 존재하지 않습니다.");
      }

      return new UsernamePasswordAuthenticationToken(
          userVo.getUserEmail(), userVo.getUserPassword());
    } catch (IOException io) {
      io.printStackTrace();
      throw new RuntimeException("request.getInputStream() IO 에러");
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException {
    final String accessToken = jwtTokenUtil.createAccessToken(authResult);
    final String refreshToken = jwtTokenUtil.createRefreshToken(authResult);

    if (StringUtils.isBlank(accessToken) && StringUtils.isBlank(refreshToken)) {
      throw new TokenException("토큰이 생성되지 않았습니다.");
    }

    final UserVo userVo =
        UserVo.builder()
            .userEmail(authResult.getPrincipal().toString())
            .userRefreshToken(refreshToken)
            .userAccessToken(accessToken)
            .build();

    userService.userTokenUpdate(userVo);
    response.addHeader(JwtProperties.AUTHORIZATION, JwtProperties.TOKEN_PREFIX + accessToken);
    response.addHeader(JwtProperties.REFRESH_TOKEN, JwtProperties.TOKEN_PREFIX + refreshToken);
    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    HttpResponse<Void> httpResponse =
        HttpResponse.<Void>builder().status(HttpStatus.OK).message("로그인 성공").build();
    response.getWriter().write(LazyHolderObject.getGson().toJson(httpResponse));
  }
}
