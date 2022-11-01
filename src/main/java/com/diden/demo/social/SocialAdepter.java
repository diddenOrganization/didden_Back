package com.diden.demo.social;

import com.diden.demo.user.UserVo;

public interface SocialAdepter {
  boolean supports(String handler);
  UserVo process(final String accessToken);
}
