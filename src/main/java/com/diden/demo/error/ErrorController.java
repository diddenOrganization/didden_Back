package com.diden.demo.error;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.error.exception.TokenException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ErrorController {
  @ExceptionHandler({NullPointerException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorVo nullPointer(final NullPointerException e) {
    e.printStackTrace();
    return ErrorVo.builder().msg(e.getMessage()).build();
  }

  @ExceptionHandler({BadRequestException.class, TokenException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorVo badRequest(final RuntimeException e) {
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
