package com.diden.demo.main;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class MainContentVo {
  private final String contentId;
  private final String contentName;
  private final String contentCreateDate;
  private final String contentUpdateDate;
  private final String contentExtension;
  private final String contentImageUri;

  @Builder
  public MainContentVo(
      String contentId,
      String contentName,
      String contentCreateDate,
      String contentUpdateDate,
      String contentExtension,
      String contentImageUri) {
    this.contentId = contentId;
    this.contentName = contentName;
    this.contentCreateDate = contentCreateDate;
    this.contentUpdateDate = contentUpdateDate;
    this.contentExtension = contentExtension;
    this.contentImageUri = contentImageUri;
  }
}
