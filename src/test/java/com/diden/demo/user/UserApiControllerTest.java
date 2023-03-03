package com.diden.demo.user;

import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {
  @Autowired MockMvc mockMvc;
  private final Gson gson = LazyHolderObject.getGson();
  private String authorization;

  @Rollback
  @BeforeEach
  void setup() throws Exception {
    final UserDto userVo =
        UserDto.builder().userEmail("bioman3238@gmail.com").userPassword("test").build();

    /*authorization =
    mockMvc
        .perform(
            post("/login").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(userVo)))
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse()
        .getHeader(JwtProperties.HEADER_STRING);*/
  }

  @Test
  @Rollback
  @DisplayName("이메일 중복체크 - 성공")
  void emailDuplicateCheck() throws Exception {
    mockMvc
        .perform(get("/user/email-check").param("userEmail", "test@test"))
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("이메일 중복체크 - 이미 존재하는 이메일 ")
  void emailDuplicateCheckFail() throws Exception {
    mockMvc
        .perform(get("/user/email-check").param("userEmail", "bioman3238@gmail.com"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("이미 가입된 이메일 입니다."))
        .andDo(print());
  }

  @Rollback
  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("이메일 중복체크 - 이메일 공백 ")
  void emailDuplicateCheckEmptySpace(String emptyOrNull) throws Exception {
    mockMvc
        .perform(get("/user/email-check").param("userEmail", emptyOrNull))
        .andExpect(status().is4xxClientError())
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("회원가입 - 성공")
  void userInsert() throws Exception {
    UserDto user =
        UserDto.builder()
            .userPassword("1234")
            .userNickname("테스터")
            .userBirthday("19930509")
            .userGender(UserDto.Gender.MALE)
            .userEmail("qwe@qwe.com")
            .userPhoneNumber("01010101010")
            .userPrivacyConsent(UserDto.PrivacyConsent.AGREED)
            .userLoginType(AccountTypeEnum.DEFAULT.getAccountType())
            .build();

    mockMvc
        .perform(
            post("/user").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("CREATED"))
        .andExpect(jsonPath("$.message").value("회원가입이 되었습니다."))
        .andDo(print());
  }

  @Rollback
  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("회원가입 - 로그인 타입 누락")
  void userInsertLoginTypeNullCheck(String args) throws Exception {
    UserDto user =
        UserDto.builder()
            .userPassword("1234")
            .userNickname("테스터")
            .userBirthday("19930509")
            .userGender(UserDto.Gender.MALE)
            .userEmail("qwe@qwe.com")
            .userPhoneNumber("01010101010")
            .userLoginType(args)
            .build();


    mockMvc
        .perform(
            post("/user").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("사용자 로그인 타입이 존재하지 않습니다."))
        .andDo(print());
  }

  @Rollback
  @ParameterizedTest
  @DisplayName("회원가입 - 개인정보")
  @EnumSource(value = UserDto.PrivacyConsent.class)
  void userInsertPrivacyConsentCheck(UserDto.PrivacyConsent args) throws Exception {
    UserDto user =
        UserDto.builder()
            .userPassword("1234")
            .userNickname("테스터")
            .userBirthday("19930509")
            .userGender(UserDto.Gender.MALE)
            .userEmail("qwe@qwe.com")
            .userPhoneNumber("01010101010")
            .userLoginType(AccountTypeEnum.DEFAULT.getAccountType())
            .userPrivacyConsent(args)
            .build();

    mockMvc
        .perform(
            post("/user").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
  }

  @Rollback
  @ParameterizedTest
  @DisplayName("회원가입 - 성별")
  @EnumSource(value = UserDto.Gender.class)
  void userInsertGenderCheck(UserDto.Gender args) throws Exception {
    UserDto user =
        UserDto.builder()
            .userPassword("1234")
            .userNickname("테스터")
            .userBirthday("19930509")
            .userEmail("qwe@qwe.com")
            .userPhoneNumber("01010101010")
            .userLoginType(AccountTypeEnum.DEFAULT.getAccountType())
            .userGender(args)
            .userPrivacyConsent(UserDto.PrivacyConsent.AGREED)
            .build();

    mockMvc
        .perform(
            post("/user").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("CREATED"))
        .andExpect(jsonPath("$.message").value("회원가입이 되었습니다."))
        .andDo(print());
  }

  @Rollback
  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("회원가입 - 성별 null check")
  void userInsertGenderNullCheck(String args) throws Exception {
    UserDto user =
        UserDto.builder()
            .userPassword("1234")
            .userNickname("테스터")
            .userBirthday("19930509")
            .userEmail("qwe@qwe.com")
            .userPhoneNumber("01010101010")
            .userLoginType(AccountTypeEnum.DEFAULT.getAccountType())
            .userPrivacyConsent(UserDto.PrivacyConsent.AGREED)
            .build();

    mockMvc
        .perform(
            post("/user").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("사용자 성별이 존재하지 않습니다."))
        .andDo(print());
  }
}
