package com.diden.demo.error.exception;

import org.springframework.security.authentication.InsufficientAuthenticationException;

public class UserAuthenticationException extends InsufficientAuthenticationException {
  public UserAuthenticationException(String msg) {
    super(msg);
  }
}
