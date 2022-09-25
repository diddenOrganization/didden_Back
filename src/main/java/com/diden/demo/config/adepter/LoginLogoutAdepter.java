package com.diden.demo.config.adepter;

import java.io.IOException;

public interface LoginLogoutAdepter {
  boolean supports(String handler);
  boolean loginProcess(String authorization) throws IOException;
  boolean logoutProcess(String authorization);
  String findUserEmail(String authorization);

}
