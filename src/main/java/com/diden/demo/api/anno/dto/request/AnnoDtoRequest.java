package com.diden.demo.api.anno.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnnoDtoRequest {
  private final String annoId;
  @NotBlank(message = "공지사항 제목이 존재하지 않습니다.")
  private final String annoTitle;
  @NotBlank(message = "공지사항 내용이 존재하지 않습니다.")
  private final String annoContent;
  private final String annoCreateDate;

  @Builder
  public AnnoDtoRequest(String annoId, String annoTitle, String annoContent, String annoCreateDate) {
    this.annoId = annoId;
    this.annoTitle = annoTitle;
    this.annoContent = annoContent;
    this.annoCreateDate = annoCreateDate;
  }
}
