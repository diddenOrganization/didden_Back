package com.diden.demo.common.error.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ExceptionDtoResponse<T> {
  private final HttpStatus status;
  private String message;
  private final T data;

  @Builder
  public ExceptionDtoResponse(HttpStatus status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static <T> ExceptionDtoResponse<T> toResponse(HttpStatus status, String message) {
    return toResponse(status, message, null);
  }

  public static <T> ExceptionDtoResponse<T> toResponse(HttpStatus status, String message, T data) {
    return ExceptionDtoResponse.<T>builder().status(status).message(message).data(data).build();
  }
}
