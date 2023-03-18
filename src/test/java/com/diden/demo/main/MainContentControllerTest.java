package com.diden.demo.main;

import com.diden.demo.TestStartConfig;
import com.diden.demo.common.config.properties.JwtProperties;
import com.diden.demo.common.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class MainContentControllerTest extends TestStartConfig {

  @Autowired MockMvc mockMvc;
  String accessToken;

  @BeforeEach
  void setup() {
    
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken("test@test", "test");
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    accessToken =
        JwtProperties.TOKEN_PREFIX
            + jwtTokenUtil.createAccessToken(usernamePasswordAuthenticationToken);
  }

  @Test
  @DisplayName("메인이미지컨텐츠 - 메인화면 이미지 호출")
  void imageAll() throws Exception {
    mockMvc
        .perform(get("/main/content/images").header(JwtProperties.AUTHORIZATION, accessToken))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andDo(print());
  }

  @Test
  @DisplayName("메인이미지컨텐츠 - 토큰 없음")
  void imageAll_null_token() throws Exception {
    mockMvc
        .perform(get("/main/content/images"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andDo(print());
  }


}
