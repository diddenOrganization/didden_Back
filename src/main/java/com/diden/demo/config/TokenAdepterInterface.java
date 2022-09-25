package com.diden.demo.config;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TokenAdepterInterface {
  boolean loginTokenCheckMethod(HttpServletRequest request) throws IOException;
  boolean logoutTokenCheckMethod(HttpServletRequest request) throws IOException;
}
