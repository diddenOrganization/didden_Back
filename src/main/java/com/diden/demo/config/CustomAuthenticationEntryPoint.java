package com.diden.demo.config;

import com.diden.demo.error.ExceptionVo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private final Gson gson = LazyHolderObject.getGson();

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    final ExceptionVo<Object> exceptionVo =
        ExceptionVo.builder()
            .status(HttpStatus.UNAUTHORIZED)
            .message(authException.getLocalizedMessage())
            .build();

    if (authException instanceof InsufficientAuthenticationException) {
      exceptionVo.setMessage("로그인을 하지 않았거나 토큰이 정상적이지 않습니다.");
    }

    try {
      log.error(authException.toString());
      log.debug(":: CustomAuthenticationEntryPoint.commence = {} ::", exceptionVo);
      response.getWriter().write(gson.toJson(exceptionVo));
    } catch (IOException e) {
      log.error("{}", e.toString());
      e.printStackTrace();
    }
  }
}
