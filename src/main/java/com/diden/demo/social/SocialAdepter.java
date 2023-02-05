package com.diden.demo.social;

import com.diden.demo.user.UserVo;

import java.io.IOException;

public interface SocialAdepter {
  boolean supports(String handler);
  UserVo process(final String accessToken) throws IOException;
}
