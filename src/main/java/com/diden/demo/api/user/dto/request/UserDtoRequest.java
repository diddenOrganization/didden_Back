package com.diden.demo.api.user.dto.request;

import com.diden.demo.domain.user.enums.GenderEnum;
import com.diden.demo.domain.user.enums.PrivacyConsentEnum;
import com.diden.demo.domain.user.vo.request.UserVoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class UserDtoRequest {
  @Schema(title = "비밀번호", description = "")
  @NotBlank(message = "사용자 비밀번호가 존재하지 않습니다.")
  private String userPassword;

  @Schema(title = "닉네임", description = "")
  @NotBlank(message = "사용자 닉네임이 존재하지 않습니다.")
  private String userNickname;

  @Schema(title = "생년월일", description = "YYYYMMDD 형식입니다.", example = "19930509")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
  @NotBlank(message = "사용자 생년월일이 존재하지 않습니다.")
  private String userBirthday;

  @Schema(title = "성별", description = "M, F 중 하나를 입력해야 합니다.")
  @NotNull(message = "사용자 성별이 존재하지 않습니다.")
  private GenderEnum userGenderEnum;

  @Schema(title = "이메일", description = "xxxxx@xxxx.com 형식입니다.", example = "test@naver.com")
  @NotNull(message = "사용자 이메일이 존재하지 않습니다.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String userEmail;

  @Schema(title = "휴대폰 번호", description = "01012345678 형식이며 - 는 들어가지 않습니다.", example = "01012345678")
  @NotBlank(message = "사용자 휴대폰 번호가 존재하지 않습니다.")
  private String userPhoneNumber;

  @Schema(title = "", description = "", hidden = true)
  private String userCreateDate;
  @Schema(title = "", description = "", hidden = true)
  private String userUpdateDate;

  @Schema(title = "개인정보동의", description = "개인정보 동의를 입력합니다.", example = "Y")
  @NotNull(message = "사용자 개인정보동의가 존재하지 않습니다.")
  private PrivacyConsentEnum userPrivacyConsentEnum;

  @Schema(title = "로그인 타입", description = "소셜로그인 또는 일반로그인 타입 중 하나입니다. (?)", example = "default")
  @NotBlank(message = "사용자 로그인 타입이 존재하지 않습니다.")
  private String userLoginType;

  @Schema(title = "", description = "", hidden = true)
  private String userAccessToken;
  @Schema(title = "", description = "", hidden = true)
  private String userRefreshToken;
  @Schema(title = "", description = "", hidden = true)
  private String nextData;

  public UserDtoRequest() {}

  @Builder
  public UserDtoRequest(
      String userPassword,
      String userNickname,
      String userBirthday,
      GenderEnum userGenderEnum,
      String userEmail,
      String userPhoneNumber,
      String userCreateDate,
      String userUpdateDate,
      PrivacyConsentEnum userPrivacyConsentEnum,
      String userLoginType,
      String userAccessToken,
      String userRefreshToken,
      String nextData) {
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userBirthday = userBirthday;
    this.userGenderEnum = userGenderEnum;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.userCreateDate = userCreateDate;
    this.userUpdateDate = userUpdateDate;
    this.userPrivacyConsentEnum = userPrivacyConsentEnum;
    this.userLoginType = userLoginType;
    this.userAccessToken = userAccessToken;
    this.userRefreshToken = userRefreshToken;
    this.nextData = nextData;
  }

  public static UserVoRequest transportDataByUserDtoRequest(UserDtoRequest userDtoRequest) {
    return UserVoRequest.builder()
        .userPassword(userDtoRequest.getUserPassword())
        .userNickname(userDtoRequest.getUserNickname())
        .userBirthday(userDtoRequest.getUserBirthday())
        .userGenderEnum(userDtoRequest.getUserGenderEnum())
        .userEmail(userDtoRequest.getUserEmail())
        .userPhoneNumber(userDtoRequest.getUserPhoneNumber())
        .userCreateDate(userDtoRequest.getUserCreateDate())
        .userUpdateDate(userDtoRequest.getUserUpdateDate())
        .userPrivacyConsentEnum(userDtoRequest.getUserPrivacyConsentEnum())
        .userLoginType(userDtoRequest.getUserLoginType())
        .userAccessToken(userDtoRequest.getUserAccessToken())
        .userRefreshToken(userDtoRequest.getUserRefreshToken())
        .nextData(userDtoRequest.getNextData())
        .build();
  }
}
