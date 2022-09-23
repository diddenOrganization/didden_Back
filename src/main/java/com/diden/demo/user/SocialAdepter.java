package com.diden.demo.user;

public interface SocialAdepter {
  boolean supports(String handler);

  UserVo process(final String accessToken);
}
