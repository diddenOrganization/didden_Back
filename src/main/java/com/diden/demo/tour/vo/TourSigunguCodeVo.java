package com.diden.demo.tour.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TourSigunguCodeVo {
  private Integer code;
  private String name;
  private String use;
  private Integer areaCode;

  @Builder
  public TourSigunguCodeVo(Integer code, String name, String use, Integer areaCode) {
    this.code = code;
    this.name = name;
    this.use = use;
    this.areaCode = areaCode;
  }
}
