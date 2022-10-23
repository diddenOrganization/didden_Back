package com.diden.demo.config;

import com.diden.demo.user.UserVo;
import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class JwtAuthorizationFilterTest {
  @Autowired MockMvc mockMvc;

  @Test
  @DisplayName("JWT 토큰인가 - 정상적으로 토큰 인가 확인")
  void doFilterInternal() throws Exception {
    UserVo userVo = UserVo.builder().userEmail("test@test").userPassword("1234").build();
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(userVo.getUserEmail(), userVo.getUserPassword());

    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    String accessToken = jwtTokenUtil.createAccessToken(authentication);
    log.info(accessToken);

    mockMvc
        .perform(
            get("/user/list")
                .characterEncoding("utf-8")
                .header(JwtProperties.LOGIN_TYPE, "default")
                .header(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken))
        .andExpect(status().isOk())
        .andDo(print());
  }
}
