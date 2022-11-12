package com.diden.demo.social;

import com.diden.demo.error.exception.BadRequestException;
import com.diden.demo.utils.AccountTypeEnum;
import com.diden.demo.utils.HttpResponse;
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

  @PostMapping
  public HttpResponse<Void> userSocialInsert(
      @RequestBody @NotNull(message = "소셜 정보가 존재하지 않습니다.")
          final UserSocialSignUpDto userSocialSignUpDto) throws IOException {
    // TODO :: 소셜 리프레쉬 토큰 저장 기능 구현해야 함.
    if (userSocialSignUpDto.getLoginType() == null) {
      throw new BadRequestException("로그인 타입이 존재하지 않습니다.");
    }
    if (StringUtils.isBlank(userSocialSignUpDto.getAccessToken())) {
      throw new BadRequestException("소셜 엑세스 토큰이 존재하지 않습니다.");
    }
    if (userSocialSignUpDto.getLoginType() == AccountTypeEnum.DEFAULT) {
      throw new BadRequestException("일반회원 가입은 대상항목이 아닙니다.");
    }

    socialService.socialSignup(
        userSocialSignUpDto.getLoginType().getAccountType(), userSocialSignUpDto.getAccessToken());
    return HttpResponse.toResponse(HttpStatus.CREATED, "회원가입이 되었습니다.");
  }

  @PostMapping("/login")
  public HttpResponse<Void> userSocialLogin(
      @RequestBody @NotNull(message = "로그인 정보가 존재하지 않습니다.")
          final UserSocialSignUpDto userSocialSignUpDto)
      throws IOException {

    if (userSocialSignUpDto.getLoginType() == null) {
      throw new BadRequestException("로그인 타입이 존재하지 않습니다.");
    }

    if (StringUtils.isBlank(userSocialSignUpDto.getAccessToken())) {
      throw new BadRequestException("소셜 엑세스 토큰이 존재하지 않습니다.");
    }

    socialService.socialLoginAndSignupProcess(userSocialSignUpDto.getLoginType(), userSocialSignUpDto.getAccessToken());

    return HttpResponse.toResponse(HttpStatus.OK, "소셜 로그인 성공");
  }
}
