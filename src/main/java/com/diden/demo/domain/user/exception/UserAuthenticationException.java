package com.diden.demo.domain.user.exception;

import org.springframework.security.authentication.InsufficientAuthenticationException;

public class UserAuthenticationException extends InsufficientAuthenticationException {
  public UserAuthenticationException(String msg) {
    super(msg);
  }
}
