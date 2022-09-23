package com.diden.demo.config;

import com.google.gson.JsonObject;
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
    final JsonObject resultObject = this.tokenAdepterInterface.tokenCheckMethod(request);

    if (resultObject.get("result").getAsBoolean()) {
      chain.doFilter(request, response);
    }
  }
}
