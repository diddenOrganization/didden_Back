package com.diden.demo.user;

import com.google.gson.JsonObject;

public interface SocialAdepter {
  boolean supports(String handler);

  UserVo process(JsonObject param);
}
