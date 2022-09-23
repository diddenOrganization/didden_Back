package com.diden.demo.user;

import com.google.gson.JsonObject;

public interface UserSocialService {
  boolean process(JsonObject param);
}
