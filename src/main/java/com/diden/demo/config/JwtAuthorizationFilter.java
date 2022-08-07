package com.diden.demo.config;

import com.diden.demo.error.exception.TokenException;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private final TokenAdepterInterface tokenAdepterInterface;

  @Override
  protected void doFilterInternal(
      final @NotNull HttpServletRequest request,
      final @NotNull HttpServletResponse response,
      final @NotNull FilterChain chain)
      throws ServletException, IOException {
    final JsonObject resultObject = this.tokenAdepterInterface.tokenCheckMethod(request);
    final boolean result = resultObject.get("result").getAsBoolean();
    if (result) {
      chain.doFilter(request, response);
    } else {
      response.sendError(SC_BAD_REQUEST, "토큰값이 잘못됐습니다.");
      throw new TokenException("토큰값이 잘못됐습니다.");
    }
  }
}
