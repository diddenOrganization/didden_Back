package com.diden.demo.utils;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;

@Getter
public final class HttpResponse<T> {
  @NotBlank(message = "응답상태가 존재하지 않습니다.")
  private final HttpStatus status;

  @NotBlank(message = "메세지가 존재하지 않습니다.")
  private final String message;

  private final Integer nextSeq;
  private final T data;

  @Builder
  HttpResponse(HttpStatus status, String message, T data, Integer nextSeq) {
    this.status = status;
    this.message = message;
    this.nextSeq = nextSeq;
    this.data = data;
  }

  public static <T> HttpResponse<T> toPageResponse(
      HttpStatus status, String message, T data, Integer nextSeq) {
    return toResponse(status, message, data, nextSeq);
  }

  public static <T> HttpResponse<T> toResponse(HttpStatus status, String message) {
    return toResponse(status, message, null, null);
  }

  public static <T> HttpResponse<T> toResponse(HttpStatus status, String message, T data) {
    return toResponse(status, message, data, null);
  }

  public static <T> HttpResponse<T> toResponse(
      HttpStatus status, String message, T data, Integer nextSeq) {
    return HttpResponse.<T>builder()
        .status(status)
        .message(message)
        .data(data)
        .nextSeq(nextSeq)
        .build();
  }
}
