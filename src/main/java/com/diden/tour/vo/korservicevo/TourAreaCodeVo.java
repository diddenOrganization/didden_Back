package com.diden.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * description of class TourAreaCodeVo
 * 
 * numOfRows 한 페이지 결과 수
 * pageNo 페이지 번호
 * MobileOS OS 구분 - 필수값
 * MobileApp 서비스명 - 필수값
 * ServiceKey 인증키 (서비스키) - 필수값
 * areaCode 지역코드
 * 
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Data
@Getter
@Setter
public class TourAreaCodeVo {
    private String numOfRows = "";
    private String pageNo = "";
    private String mobileOS = "";
    private String mobileApp = "";
    private String areaCode = "";

    public TourAreaCodeVo() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
    }
}
