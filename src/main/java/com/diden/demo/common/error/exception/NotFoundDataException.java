package com.diden.demo.common.error.exception;

public class NotFoundDataException extends RuntimeException {
  public NotFoundDataException(String message) {
    super(message);
  }

  public NotFoundDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
