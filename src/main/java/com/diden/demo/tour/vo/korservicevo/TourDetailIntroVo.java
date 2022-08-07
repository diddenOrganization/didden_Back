package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * description of class TourDetailIntroVo
 *
 * <p>numOfRows 한 페이지 결과 수 pageNo 페이지 번호 MobileOS OS 구분 - 필수값 MobileApp 서비스명 - 필수값 ServiceKey 인증키
 * (서비스키) - 필수값 contentId 콘텐츠ID - 필수값 contentTypeId 관광타입 ID - 필수값
 *
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Data
@Getter
@Setter
public class TourDetailIntroVo {
  private String numOfRows = "";
  private String pageNo = "";
  private String mobileOS = "";
  private String mobileApp = "";
  private String contentId = "";
  private String contentTypeId = "";

  public TourDetailIntroVo() {
    this.mobileApp = "AppTest";
    this.mobileOS = "ETC";
    this.numOfRows = "999";
  }
}
