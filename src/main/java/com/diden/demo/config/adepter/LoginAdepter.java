package com.diden.demo.config.adepter;

import java.io.IOException;

public interface LoginAdepter {
  boolean supports(String handler);
  boolean process(String authorization) throws IOException;
}
