package com.diden.demo.domain.tour.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TourAreaCodeVo {
  private Integer code;
  private String name;
  private String use;

  @Builder
  public TourAreaCodeVo(Integer code, String name, String use) {
    this.code = code;
    this.name = name;
    this.use = use;
  }
}
