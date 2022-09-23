package com.diden.demo.user;

import com.google.gson.JsonObject;

import static com.diden.demo.utils.AccountTypeEnum.APPLE;

public class AppleSocialAdepterImpl implements SocialAdepter {
  @Override
  public boolean supports(String handler) {
    return (APPLE.getAccountType().equals(handler));
  }

  @Override
  public UserVo process(JsonObject param) {
    return null;
  }
}
