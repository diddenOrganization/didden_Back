package com.diden.demo.domain.tour.vo.korservicevo;

import com.diden.demo.domain.tour.TourProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 *
 * <h1>description of class TourAreaCodeVo</h1>
 *
 * <p>numOfRows 한 페이지 결과 수
 *
 * <p>pageNo 페이지 번호
 *
 * <p>MobileOS OS 구분 - 필수값
 *
 * <p>MobileApp 서비스명 - 필수값
 *
 * <p>ServiceKey 인증키
 *
 * <p>areaCode 지역코드
 *
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Getter
@Setter
public class TourAreaCodeVo {
  @SerializedName("numOfRows")
  private String numOfRows;

  @SerializedName("pageNo")
  private String pageNo;

  @SerializedName("mobileOS")
  private String mobileOS;

  @SerializedName("mobileApp")
  private String mobileApp;

  @SerializedName("areaCode")
  private String areaCode;

  @Builder
  public TourAreaCodeVo(
      String numOfRows, String pageNo, String mobileOS, String mobileApp, String areaCode) {
    this.numOfRows = numOfRows;
    this.pageNo = pageNo;
    this.mobileOS = mobileOS;
    this.mobileApp = mobileApp;
    this.areaCode = areaCode;
  }

  public static TourAreaCodeVo setRequiredTourAreaCode(final String pageNo, final String areaCode) {
    return toTourAreaCodeVo(
        pageNo,
        areaCode,
        TourProperties.DEFAULT_NUM_OF_ROWS,
        TourProperties.DEFAULT_MOBILE_OS,
        TourProperties.DEFAULT_MOBILE_APP);
  }

  public static TourAreaCodeVo toTourAreaCodeVo(
      final String pageNo,
      final String areaCode,
      final String numOfRows,
      final String mobileOS,
      final String mobileApp) {
    return TourAreaCodeVo.builder()
        .pageNo(pageNo)
        .areaCode(areaCode)
        .numOfRows(numOfRows)
        .mobileOS(mobileOS)
        .mobileApp(mobileApp)
        .build();
  }
}
