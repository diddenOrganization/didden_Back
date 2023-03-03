package com.diden.demo.common.error;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ExceptionVo<T> {
  private final HttpStatus status;
  private String message;
  private final T data;

  @Builder
  public ExceptionVo(HttpStatus status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static <T> ExceptionVo<T> toResponse(HttpStatus status, String message) {
    return toResponse(status, message, null);
  }

  public static <T> ExceptionVo<T> toResponse(HttpStatus status, String message, T data) {
    return ExceptionVo.<T>builder().status(status).message(message).data(data).build();
  }
}
