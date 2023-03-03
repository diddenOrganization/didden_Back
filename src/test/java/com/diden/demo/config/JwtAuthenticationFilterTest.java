package com.diden.demo.config;

import com.diden.demo.domain.user.enums.AccountTypeEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
class JwtAuthenticationFilterTest {
  @Autowired MockMvc mockMvc;

  @Test
  @Rollback
  @DisplayName("JWT 인증필터 - 로그인 성공")
  void login() throws Exception {
    UserDto user =
        UserDto.builder()
            .userEmail("bioman3238@gmail.com")
            .userPassword("test")
            .userLoginType(AccountTypeEnum.DEFAULT.getAccountType())
            .build();
    Gson gson = new Gson();

    mockMvc
        .perform(
            post("/login").content(gson.toJson(user)).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andDo(print());
  }
}
