package com.diden.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@Transactional
class KakaoSocialServiceImplTest {

  @Autowired MockMvc mockMvc;
  @Autowired ResourceLoader resourceLoader;

  @Test
  @Rollback
  void signup() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:/social.json");
    Path path = Paths.get(resource.getURI());
    String str = Files.readString(path);

    this.mockMvc
        .perform(post("/user/social/login").contentType(MediaType.APPLICATION_JSON).content(str))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
