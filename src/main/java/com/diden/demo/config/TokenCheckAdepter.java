package com.diden.demo.config;

import com.diden.demo.config.adepter.LoginLogoutAdepter;
import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.DataNotProcessExceptions;
import com.diden.demo.user.UserService;
import com.diden.demo.user.UserVo;
import com.diden.demo.utils.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.diden.demo.utils.JwtTokenUtil.replaceTokenPrefix;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenCheckAdepter implements TokenAdepterInterface {
  private final UserService userService;
  private final List<LoginLogoutAdepter> loginLogoutAdepterList;

  public boolean loginTokenCheckMethod(final HttpServletRequest request) throws IOException {
    final String authorization = request.getHeader(JwtProperties.HEADER_STRING);

    // 토큰이 없으면 pass
    if (StringUtils.isBlank(authorization)) {
      log.debug(":: TokenCheckAdepter.loginTokenCheckMethod  ==  Authorization 존재하지 않아 TRUE 반환 ::");
      return true;
    }

    final String byLoginType = userService.findByLoginType(replaceTokenPrefix(authorization));
    if (StringUtils.isBlank(byLoginType)) {
      throw new BadRequestException("사용자가 존재하지 않습니다.");
    }

    for (LoginLogoutAdepter adepter : loginLogoutAdepterList) {
      if (adepter.supports(byLoginType)) {
        final boolean loginCheck = adepter.loginProcess(authorization);
        final Authentication authentication =
            new UsernamePasswordAuthenticationToken(
                "인가, 인증 토큰 강제 설정",
                null,
                Collections.singleton(new SimpleGrantedAuthority("YesMan")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return loginCheck;
      }
    }

    throw new IllegalArgumentException("로그인 타입이 존재하지 않습니다.");
  }

  @Override
  public boolean logoutTokenCheckMethod(HttpServletRequest request) throws IOException {
    final String authorization = request.getHeader(JwtProperties.HEADER_STRING);

    if (StringUtils.isBlank(authorization)) {
      throw new BadRequestException("토큰이 존재하지 않습니다.");
    }

    final String byLoginType = userService.findByLoginType(replaceTokenPrefix(authorization));
    for (LoginLogoutAdepter adepter : loginLogoutAdepterList) {
      if (adepter.supports(byLoginType)) {
        final String findUserEmail = adepter.findUserEmail(authorization);
        final UserVo logoutUserData =
            UserVo.builder()
                .userEmail(findUserEmail)
                .userAccessToken(null)
                .userRefreshToken(null)
                .build();

        if (userService.userTokenUpdate(logoutUserData) > 0) {
          return true;
        }

        throw new DataNotProcessExceptions("로그아웃이 처리되지 않았습니다.");
      }
    }
    throw new IllegalArgumentException("로그인 타입이 존재하지 않습니다.");
  }
}
