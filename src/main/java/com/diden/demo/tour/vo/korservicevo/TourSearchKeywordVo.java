package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourSearchKeywordVo {
  private String numOfRows = "";
  private String pageNo = "";
  private String mobileOS = "";
  private String mobileApp = "";
  private String listYN = "";
  private String arrange = "";
  private String contentTypeId = "";
  private String areaCode = "";
  private String sigunguCode = "";
  private String cat1 = "";
  private String cat2 = "";
  private String cat3 = "";
  private String keyword = "";

  public TourSearchKeywordVo() {
    this.mobileApp = "AppTest";
    this.mobileOS = "ETC";
    this.numOfRows = "999";
    this.listYN = "Y";
  }
}
