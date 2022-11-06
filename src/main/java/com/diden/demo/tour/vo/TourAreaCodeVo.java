package com.diden.demo.tour.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TourAreaCodeVo {
  private Integer rnum;
  private String code;
  private String name;

  @Builder
  public TourAreaCodeVo(Integer rnum, String code, String name) {
    this.rnum = rnum;
    this.code = code;
    this.name = name;
  }
}
