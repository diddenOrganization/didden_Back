package com.diden.demo.anno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnoVo {
  private String annoId;
  private String annoTitle;
  private String annoContent;
  private String annoCreateDate;
}
