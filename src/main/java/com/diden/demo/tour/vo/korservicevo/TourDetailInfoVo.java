package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * description of class TourDetailInfoVo
 *
 * <p>numOfRows 한 페이지 결과 수 pageNo 페이지 번호 MobileOS OS 구분 MobileApp 서비스명 ServiceKey 인증키 (서비스키)
 * areaCode 지역코드
 *
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Data
@Getter
@Setter
public class TourDetailInfoVo {
  private String numOfRows = "";
  private String pageNo = "";
  private String mobileOS = "";
  private String mobileApp = "";
  private String contentId = "";
  private String contentTypeId = "";

  public TourDetailInfoVo() {
    this.mobileApp = "AppTest";
    this.mobileOS = "ETC";
    this.numOfRows = "999";
  }
}
