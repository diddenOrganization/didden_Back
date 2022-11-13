package com.diden.demo.user;

import com.diden.demo.TestStartConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class KakaoSocialServiceImplTest extends TestStartConfig {

  @Autowired MockMvc mockMvc;
  @Autowired ResourceLoader resourceLoader;

  private final String kakaoAccessToken =
      "j4PNAmYC5U_YMXCe7WBngiruTaPzSmeZxoD40-hYCj1y6wAAAYQo7yaY";
  private final String loginEmail = "ohwig1@kakao.com";

  @Test
  @Rollback
  @DisplayName("소셜 - 카카오 로그인")
  void socialKakaoLogin() throws Exception {

    mockMvc.perform(post("/login")).andExpect(status().is2xxSuccessful()).andDo(print());
  }
}
