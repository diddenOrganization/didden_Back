package com.diden.demo.error;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ErrorController {
  @ExceptionHandler({NullPointerException.class})
  public ErrorVo nullPointer(final NullPointerException e) {
    e.printStackTrace();
    return ErrorVo.builder().msg(e.getMessage()).build();
  }

  @ExceptionHandler({BadRequestException.class})
  public ErrorVo badRequest(final BadRequestException e) {
    e.printStackTrace();
    return ErrorVo.builder().msg(e.getMessage()).build();
  }

  @ExceptionHandler({TokenException.class})
  public ErrorVo tokenError(final TokenException e) {
    e.printStackTrace();
    return ErrorVo.builder().msg(e.getMessage()).build();
  }

  @Getter
  @Builder
  private static class ErrorVo {
    private String msg;
    private String statusCode;
  }
}
