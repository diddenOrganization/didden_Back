package com.diden.demo.config;

import com.diden.demo.config.adepter.LoginAdepter;
import com.diden.demo.utils.JwtProperties;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenCheckAdepter implements TokenAdepterInterface {
  private final List<LoginAdepter> loginAdepterList;

  public JsonObject tokenCheckMethod(final HttpServletRequest request) {
    final String loginType = request.getHeader(JwtProperties.LOGIN_TYPE);
    final String Authorization = request.getHeader(JwtProperties.HEADER_STRING);

    if (isNoTokenCheckPath(request.getRequestURI())) {
      log.debug(":: TokenCheckAdepter.tokenCheckMethod = {} ::", request.getRequestURI());
      return jsonObjectResult(true);
    }

    for (LoginAdepter adepter : loginAdepterList) {
      if (adepter.supports(loginType)) {
        final boolean result = adepter.process(Authorization);
        return jsonObjectResult(result);
      }
    }

    throw new IllegalArgumentException("로그인 타입을 설정하지 않았거나 잘못 설정하였습니다. 다시 설정해주세요.");
  }

  private boolean isNoTokenCheckPath(final String path) {
    return path.startsWith("/info")
        || path.startsWith("/img")
        || path.startsWith("/user/email-check")
        || path.startsWith("/main/content")
        || path.startsWith("/main/content/images")
        || path.startsWith("/send")
        || path.startsWith("/user");
  }

  private JsonObject jsonObjectResult(final boolean result) {
    JsonObject obj = new JsonObject();
    obj.addProperty("result", result);
    return obj;
  }
}
