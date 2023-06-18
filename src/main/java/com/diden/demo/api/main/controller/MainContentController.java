package com.diden.demo.api.main.controller;

import com.diden.demo.api.main.dto.response.MainContentDtoResponse;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.main.service.MainContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "메인 API", description = "메인 관련된 API 목록입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/main", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainContentController {
  private final MainContentService mainContentService;

  @Operation(summary = "메인 이미지 조회", description = "메인 이미지를 조회하는 기능입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @GetMapping(value = "/content/images")
  public HttpResponse<List<MainContentDtoResponse>> imageAll() {

    final List<MainContentDtoResponse> mainContentImageAll = mainContentService.findMainContentImageAll().stream().map(MainContentDtoResponse::transportDataByMainContentVo).collect(Collectors.toList());

    return HttpResponse.toResponse(HttpStatus.OK, "메인 이미지 전체 출력.", mainContentImageAll);
  }
}
