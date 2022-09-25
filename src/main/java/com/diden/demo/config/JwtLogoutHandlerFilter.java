package com.diden.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtLogoutHandlerFilter implements LogoutHandler {

  private final TokenCheckAdepter tokenCheckAdepter;

  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    try {
      tokenCheckAdepter.logoutTokenCheckMethod(request);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
