package com.diden.demo.main;

import com.diden.demo.utils.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/main", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainContentController {
  private final MainContentService mainContentService;

  @GetMapping(value = "/content/images")
  public HttpResponse<List<MainContentVo>> imageAll() {

    return HttpResponse.toResponse(
        HttpStatus.OK, "메인 이미지 전체 출력.", mainContentService.findMainContentImageAll());
  }
}
