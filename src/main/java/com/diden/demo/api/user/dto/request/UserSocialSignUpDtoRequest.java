package com.diden.demo.api.user.dto.request;

import com.diden.demo.domain.user.enums.AccountTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class UserSocialSignUpDtoRequest {
  @NotBlank(message = "로그인 타입이 존재하지 않습니다.")
  private AccountTypeEnum loginType;

  @NotBlank(message = "소셜 엑세스 토큰이 존재하지 않습니다.")
  private String accessToken;

  @NotBlank(message = "소셜 리프레쉬 토큰이 존재하지 않습니다.")
  private String refreshToken;

  public UserSocialSignUpDtoRequest() {
  }

  @Builder
  public UserSocialSignUpDtoRequest(AccountTypeEnum loginType, String accessToken, String refreshToken) {
    this.loginType = loginType;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
