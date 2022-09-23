package com.diden.demo.config;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TokenAdepterInterface {
  boolean tokenCheckMethod(HttpServletRequest request) throws IOException;
}
