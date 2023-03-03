package com.diden.demo.anno;

import com.diden.demo.common.config.properties.JwtProperties;
import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.anno.vo.response.AnnoVo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:/application-dev.yml")
class AnnoControllerTest {

  @Autowired MockMvc mockMvc;

  String accessToken;
  Gson gson = LazyHolderObject.getGson();

  @Commit
  @BeforeEach
  void setup() throws Exception {
    UserDto userVo = UserDto.builder().userEmail("bioman3238@gmail.com").userPassword("test").build();

    accessToken =
        mockMvc
            .perform(
                post("/login").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(userVo)))
            .andExpect(status().is2xxSuccessful())
            .andDo(print())
            .andReturn()
            .getResponse()
            .getHeader(JwtProperties.HEADER_STRING);
  }

  @Test
  @Commit
  @DisplayName("공지사항 전체 조회 - 목록 전체 조회")
  void findAll() throws Exception {
    mockMvc
        .perform(get("/anno")
                //.header(JwtProperties.HEADER_STRING, authorization)
        )
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항 목록을 호출합니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 전체 조회 - 토큰 없음")
  void findAll_not_token() throws Exception {
    mockMvc
        .perform(get("/anno"))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("토큰이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 1건 조회 - 목록 1건 조회")
  void findOne() throws Exception {
    mockMvc
        .perform(get("/anno/1").header(JwtProperties.HEADER_STRING, accessToken))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항 1건 호출합니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 1건 조회 - 토큰 없음 ")
  void findOne_not_token() throws Exception {
    mockMvc
        .perform(get("/anno/1"))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("토큰이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 1건 조회 - path 공백")
  void findOne_empty_path_var() throws Exception {
    mockMvc
        .perform(get("/anno/ ").header(JwtProperties.HEADER_STRING, accessToken))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 아이디가 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 1건 조회 - 공지사항 데이터 없음")
  void findOne_not_data() throws Exception {
    mockMvc
        .perform(get("/anno/30").header(JwtProperties.HEADER_STRING, accessToken))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @DisplayName("공지사항 1건 조회 - path 문자")
  void findOne_not_number() throws Exception {
    mockMvc
        .perform(get("/anno/aa").header(JwtProperties.HEADER_STRING, accessToken))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("숫자만 입력할 수 있습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 정상")
  void save() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("CREATED"))
        .andExpect(jsonPath("$.message").value("공지사항이 생성되었습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 제목 없음")
  void save_not_title() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoContent("내용").build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 제목 빈값")
  void save_empty_title() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("").annoContent("내용").build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 내용 없음")
  void save_not_content() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 내용이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 내용 빈값")
  void save_empty_content() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("").build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 내용이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 공지사항 빈값")
  void save_empty_anno() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().build();
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 등록 - 공지사항 없음")
  void save_not_anno() throws Exception {
    mockMvc
        .perform(
            post("/anno")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 정상")
  void update() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항이 수정되었습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 토큰 없음")
  void update_not_token() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("토큰이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 존재하지 않는 공지사항")
  void update_garbage_id() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/99")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - id 없음")
  void update_not_anno_id() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/ ")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 아이디가 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - path 문자")
  void update_path_string_value() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/asd ")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("숫자만 입력할 수 있습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 제목 없음")
  void update_not_anno_title() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 제목 공백")
  void update_empty_anno_title() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("").annoContent("내용").build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 내용 없음")
  void update_not_anno_contents() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 내용이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 내용 공백")
  void update_empty_anno_contents() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().annoTitle("제목").annoContent("").build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 내용이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 객체 공백")
  void update_empty_anno() throws Exception {
    AnnoVo annoVo = AnnoVo.builder().build();
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(annoVo)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 제목이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 수정 - 객체 없음")
  void update_not_anno() throws Exception {
    mockMvc
        .perform(
            patch("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 삭제 - 정상")
  void anno_delete() throws Exception {
    mockMvc
        .perform(
            delete("/anno/1")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항이 삭제되었습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 삭제 - 존재하지 않는 공지사항 글")
  void anno_delete_garbage_id() throws Exception {
    mockMvc
        .perform(
            delete("/anno/99")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("공지사항이 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 삭제 - id 없음")
  void anno_none_id() throws Exception {
    mockMvc
        .perform(
            delete("/anno/ ")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("공지사항 아이디가 존재하지 않습니다."))
        .andDo(print());
  }

  @Test
  @Rollback
  @DisplayName("공지사항 삭제 - path 문자열")
  void anno_id_string_value() throws Exception {
    mockMvc
        .perform(
            delete("/anno/asdsd")
                .header(JwtProperties.HEADER_STRING, accessToken)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("숫자만 입력할 수 있습니다."))
        .andDo(print());
  }
}
