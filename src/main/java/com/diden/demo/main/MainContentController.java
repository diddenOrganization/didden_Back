package com.diden.demo.main;

import com.diden.demo.config.LazyHolderObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/main", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainContentController {
  private final MainContentService mainContentService;

  @GetMapping(value = "/content/images")
  public ResponseEntity<String> imageAll() {
    return ResponseEntity.ok(
        LazyHolderObject.getGson().toJson(mainContentService.findMainContentImageAll()));
  }
}
