package com.diden.demo.domain.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * description of class TourLocationBasedListVo
 *
 * <p>numOfRows 한 페이지 결과 수 pageNo 페이지 번호 MobileOS OS 구분 - 필수값 MobileApp 서비스명 - 필수값 ServiceKey 인증키
 * (서비스키) - 필수값 listYN 목록 구분 arrange 정렬 구분 contentTypeId 관광타입 ID mapX X좌표 - 필수값 mapY Y좌표 - 필수값
 * radius 거리 반경 - 필수값 modifiedtime 수정일
 *
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Data
@Getter
@Setter
public class TourLocationBasedListVo {
  private String numOfRows = "";
  private String pageNo = "";
  private String mobileOS = "";
  private String mobileApp = "";
  private String listYN = "";
  private String arrange = "";
  private String contentTypeId = "";
  private String mapX = "";
  private String mapY = "";
  private String radius = "";
  private String modifiedtime = "";

  public TourLocationBasedListVo() {
    this.mobileApp = "AppTest";
    this.mobileOS = "ETC";
    this.numOfRows = "999";
    this.listYN = "Y";
  }
}
