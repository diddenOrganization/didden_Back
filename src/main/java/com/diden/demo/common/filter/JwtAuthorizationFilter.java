package com.diden.demo.common.filter;

import com.diden.demo.common.adepter.TokenAdepterInterface;
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

    log.info(
        ":::::::::::::::::::::::::::::::::::::: Request Start ::::::::::::::::::::::::::::::::::::::");
    log.info(
        "::::::::: request URI ==> [{}] {} :::::::::",
        request.getMethod(),
        request.getRequestURI());

    log.info(":: JwtAuthorizationFilter.doFilterInternal.loginTokenCheckMethod  ==  토큰 검증 시작 ::");
    if (this.tokenAdepterInterface.loginTokenCheckMethod(request)) { // 토큰 검증
      chain.doFilter(request, response);
    }

    log.info(
        ":::::::::::::::::::::::::::::::::::::: Request End ::::::::::::::::::::::::::::::::::::::");
  }
}
