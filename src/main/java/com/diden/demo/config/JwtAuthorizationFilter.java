package com.diden.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private final TokenAdepterInterface tokenAdepterInterface;

  @Override
  protected void doFilterInternal(
      final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
      throws ServletException, IOException {
    // 토큰 검증
    log.info(":: JwtAuthorizationFilter.doFilterInternal.loginTokenCheckMethod  ==  토큰 검증 시작 ::");
    if (this.tokenAdepterInterface.loginTokenCheckMethod(request)) {
      chain.doFilter(request, response);
    }
  }
}
