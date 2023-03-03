package com.diden.demo.common.adepter;

import com.diden.demo.common.config.properties.JwtProperties;
import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.diden.demo.domain.user.service.UserService;
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
import java.util.Map;

import static com.diden.demo.common.jwt.JwtTokenUtil.replaceTokenPrefix;
import static com.diden.demo.common.jwt.JwtTokenUtil.tokenPrefixCheckAndReplace;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenCheckAdepter implements TokenAdepterInterface {
  private final UserService userService;
  private final Map<AccountTypeEnum, LoginLogoutAdepter> loginLogoutAdepterMap;

  public boolean loginTokenCheckMethod(final HttpServletRequest request) throws IOException {
    final String authorization = request.getHeader(JwtProperties.AUTHORIZATION);

    if (StringUtils.isBlank(authorization)) { // 토큰이 없으면 pass
      log.debug(":: TokenCheckAdepter.loginTokenCheckMethod  ==  Authorization 존재하지 않아 TRUE 반환 ::");
      return true;
    }

    final String byLoginType =
        userService.findByLoginType(tokenPrefixCheckAndReplace(authorization)); // 로그인 타입 조회
    if (StringUtils.isBlank(byLoginType)) {
      throw new BadRequestException("사용자가 존재하지 않습니다.");
    }

    final LoginLogoutAdepter loginAdepterHandler =
        loginLogoutAdepterMap.get(AccountTypeEnum.getAccountEnumType(byLoginType));
    final boolean process = loginAdepterHandler.loginProcess(authorization); // 토큰 검증

    if (process) { // 인가, 인증 토큰 강제 설정
      final Authentication authentication =
          new UsernamePasswordAuthenticationToken(
              "인가, 인증 토큰 강제 설정", null, Collections.singleton(new SimpleGrantedAuthority("YesMan")));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      return true;
    } else {
      throw new BadRequestException("로그인을 하지 않았거나 토큰이 정상적이지 않습니다.");
    }
  }

  @Override
  public boolean logoutTokenCheckMethod(HttpServletRequest request) throws IOException {
    final String authorization = request.getHeader(JwtProperties.AUTHORIZATION);

    if (StringUtils.isBlank(authorization)) {
      throw new BadRequestException("토큰이 존재하지 않습니다.");
    }

    final String byLoginType = userService.findByLoginType(replaceTokenPrefix(authorization));
    final LoginLogoutAdepter loginAdepterHandler =
        loginLogoutAdepterMap.get(AccountTypeEnum.getAccountEnumType(byLoginType));
    final boolean process = loginAdepterHandler.logoutProcess(authorization); // 토큰 검증

    throw new IllegalArgumentException("로그인 타입이 존재하지 않습니다.");
  }
}
