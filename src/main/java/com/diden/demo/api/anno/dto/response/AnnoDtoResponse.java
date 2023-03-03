package com.diden.demo.api.anno.dto.response;

import com.diden.demo.domain.anno.vo.response.AnnoVo;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnnoDtoResponse {
  private final String annoId;
  private final String annoTitle;
  private final String annoContent;
  private final String annoCreateDate;

  @Builder
  public AnnoDtoResponse(String annoId, String annoTitle, String annoContent, String annoCreateDate) {
    this.annoId = annoId;
    this.annoTitle = annoTitle;
    this.annoContent = annoContent;
    this.annoCreateDate = annoCreateDate;
  }

  public static AnnoDtoResponse transformDataByAnnoVo(AnnoVo annoVo) {
    return AnnoDtoResponse.builder()
            .annoId(annoVo.getAnnoId())
            .annoTitle(annoVo.getAnnoTitle())
            .annoContent(annoVo.getAnnoContent())
            .annoCreateDate(annoVo.getAnnoCreateDate())
            .build();

  }
}
