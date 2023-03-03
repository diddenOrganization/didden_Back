package com.diden.demo.domain.user.adepter;

import com.diden.demo.domain.user.vo.request.UserVoRequest;

import java.io.IOException;

public interface SocialAdepter {
  boolean supports(String handler);
  UserVoRequest process(final String accessToken) throws IOException;
}
