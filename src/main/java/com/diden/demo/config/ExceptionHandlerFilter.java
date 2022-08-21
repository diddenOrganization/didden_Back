package com.diden.demo.config;

import com.diden.demo.error.ExceptionVo;
import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  private final Gson gson = LazyHolderObject.getGson();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      filterChain.doFilter(request, response);
    } catch (BadRequestException | TokenException | IllegalArgumentException e) {
      e.printStackTrace();
      log.debug(":: ExceptionHandlerFilter.doFilterInternal.BAD_REQUEST ::");
      setExceptionResponse(HttpStatus.BAD_REQUEST, response, e);
    } catch (RuntimeException e) {
      log.debug(":: ExceptionHandlerFilter.doFilterInternal.INTERNAL_SERVER_ERROR ::");
      setExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
    }
  }

  public void setExceptionResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
    response.setStatus(status.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    ExceptionVo exceptionVo = ExceptionVo.builder().status(status).message(ex.getMessage()).build();
    try {
      log.debug(":: ExceptionHandlerFilter.setExceptionResponse = {} ::", exceptionVo);
      response.getWriter().write(gson.toJson(exceptionVo));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
