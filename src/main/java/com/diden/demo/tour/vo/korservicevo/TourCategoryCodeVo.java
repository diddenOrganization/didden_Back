package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourCategoryCodeVo {
  private String numOfRows = "";
  private String pageNo = "";
  private String mobileOS = "";
  private String mobileApp = "";
  private String contentTypeId = "";
  private String cat1 = "";
  private String cat2 = "";
  private String cat3 = "";

  public TourCategoryCodeVo() {
    this.mobileApp = "AppTest";
    this.mobileOS = "ETC";
    this.numOfRows = "999";
  }
}
