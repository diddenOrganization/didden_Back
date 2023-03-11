package com.diden.demo.api.user.controller;

import com.diden.demo.api.user.dto.request.UserSocialSignUpDtoRequest;
import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.user.service.SocialService;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/social", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SocialApiController {
  private final SocialService socialService;

  @PostMapping("/login")
  public HttpResponse<Void> userSocialLogin(
      @RequestBody @NotNull(message = "로그인 정보가 존재하지 않습니다.") final UserSocialSignUpDtoRequest userSocialSignUpDtoRequest) throws IOException {

    if (userSocialSignUpDtoRequest.getLoginType() == null) {
      throw new BadRequestException("로그인 타입이 존재하지 않습니다.");
    }

    if (userSocialSignUpDtoRequest.getLoginType() == AccountTypeEnum.DEFAULT) {
      throw new BadRequestException("일반회원 가입은 대상항목이 아닙니다.");
    }

    if (StringUtils.isBlank(userSocialSignUpDtoRequest.getAccessToken())) {
      throw new BadRequestException("소셜 엑세스 토큰이 존재하지 않습니다.");
    }

    socialService.socialLoginAndSignupProcess(userSocialSignUpDtoRequest.getLoginType(), userSocialSignUpDtoRequest.getAccessToken());
    return HttpResponse.toResponse(HttpStatus.OK, "소셜 로그인 성공");
  }
}
