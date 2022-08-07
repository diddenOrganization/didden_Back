package com.diden.demo.config;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;

public interface TokenAdepterInterface {
  JsonObject tokenCheckMethod(HttpServletRequest request);
}
