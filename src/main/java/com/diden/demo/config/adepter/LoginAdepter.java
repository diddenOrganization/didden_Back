package com.diden.demo.config.adepter;

public interface LoginAdepter {
  boolean supports(String handler);

  boolean process(String Authorization);
}
