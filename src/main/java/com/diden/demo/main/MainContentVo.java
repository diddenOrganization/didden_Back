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
  private final Long contentSize;
  private final String contentCreateDate;
  private final String contentUpdateDate;
  private final transient byte[] contentImageByte;
  private final String contentContent64;
  private final String contentExtension;
  private String contentUrl;
  private final transient LocalDateTime dataCreateTime;

  @Builder
  public MainContentVo(
      String contentId,
      String contentName,
      Long contentSize,
      String contentCreateDate,
      String contentUpdateDate,
      byte[] contentImageByte,
      String contentContent64,
      String contentExtension,
      String contentUrl,
      LocalDateTime dataCreateTime) {
    this.contentId = contentId;
    this.contentName = contentName;
    this.contentSize = contentSize;
    this.contentCreateDate = contentCreateDate;
    this.contentUpdateDate = contentUpdateDate;
    this.contentImageByte = contentImageByte;
    this.contentContent64 = contentContent64;
    this.contentExtension = contentExtension;
    this.contentUrl = contentUrl;
    this.dataCreateTime = dataCreateTime;
  }

  public void setContentUrl(final String contentUrl) {
    this.contentUrl = contentUrl;
  }
}
