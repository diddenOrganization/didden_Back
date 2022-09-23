package com.diden.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class UserVo {
  @NotBlank(message = "사용자 비밀번호가 존재하지 않습니다.")
  private final String userPassword;

  @NotBlank(message = "사용자 닉네임이 존재하지 않습니다.")
  private final String userNickname;

  @NotBlank(message = "사용자 생년월일이 존재하지 않습니다.")
  private final String userBirthday;

  @NotBlank(message = "사용자 성별이 존재하지 않습니다.")
  private final String userGender;

  @NotNull(message = "사용자 이메일이 존재하지 않습니다.")
  @Email(message = "이메일 형식이 아닙니다.")
  private final String userEmail;

  @NotBlank(message = "사용자 휴대폰 번호가 존재하지 않습니다.")
  private final String userPhoneNumber;

  private final String userCreateDate;
  private final String userUpdateDate;
  private final String userPrivacyConsent;
  private final String userSocialLoginType;
  private final String userAccessToken;
  private final String userRefreshToken;

  @Builder
  public UserVo(
      String userPassword,
      String userNickname,
      String userBirthday,
      String userGender,
      String userEmail,
      String userPhoneNumber,
      String userCreateDate,
      String userUpdateDate,
      String userPrivacyConsent,
      String userSocialLoginType,
      String userAccessToken,
      String userRefreshToken) {
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userBirthday = userBirthday;
    this.userGender = userGender;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.userCreateDate = userCreateDate;
    this.userUpdateDate = userUpdateDate;
    this.userPrivacyConsent = userPrivacyConsent;
    this.userSocialLoginType = userSocialLoginType;
    this.userAccessToken = userAccessToken;
    this.userRefreshToken = userRefreshToken;
  }
}
