package com.diden.demo.domain.tour._vo;

import com.diden.demo.domain.tour.definition.ServiceHighCode;
import com.diden.demo.domain.tour.definition.ServiceMiddleCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonTourInfoVo {
  private ServiceHighCode serviceHighCode;
  private ServiceMiddleCode serviceMiddleCode;
  private String serviceLowCode;
  private String keyword;

  @Builder
  public CommonTourInfoVo(
      ServiceHighCode serviceHighCode,
      ServiceMiddleCode serviceMiddleCode,
      String serviceLowCode,
      String keyword) {
    this.serviceHighCode = serviceHighCode;
    this.serviceMiddleCode = serviceMiddleCode;
    this.serviceLowCode = serviceLowCode;
    this.keyword = keyword;
  }
}
