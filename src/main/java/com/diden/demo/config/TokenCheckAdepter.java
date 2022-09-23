package com.diden.demo.config;

import com.diden.demo.config.adepter.LoginAdepter;
import com.diden.demo.user.UserService;
import com.diden.demo.utils.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.diden.demo.utils.JwtTokenUtil.tokenReplacePrefix;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenCheckAdepter implements TokenAdepterInterface {
  private final UserService userService;
  private final List<LoginAdepter> loginAdepterList;

  public boolean tokenCheckMethod(final HttpServletRequest request) throws IOException {
    final String authorization = request.getHeader(JwtProperties.HEADER_STRING);

    // 토큰이 없으면 pass
    if(StringUtils.isBlank(authorization)) {
      log.info(":: TokenCheckAdepter.tokenCheckMethod  ==  Authorization 존재하지 않아 TRUE 반환 ::");
      return true;
    }

    final String byLoginType = userService.findByLoginType(tokenReplacePrefix(authorization));
    for (LoginAdepter adepter : loginAdepterList) {
      if (adepter.supports(byLoginType)) {
        return adepter.process(authorization);
      }
    }

    throw new IllegalArgumentException("로그인 타입이 존재하지 않습니다.");
  }
}
