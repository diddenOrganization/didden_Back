package com.diden.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * description of class TourAreaBasedListVo
 * 
 * numOfRows 한 페이지 결과 수
 * pageNo 페이지 번호
 * MobileOS OS 구분 - 필수값
 * MobileApp 서비스명 - 필수값
 * ServiceKey 인증키 (서비스키) - 필수값
 * listYN 목록 구분 - Y 목록 출력, N Total 카운트 값 출력
 * arrange 정렬 구분
 * contentTypeId 관광타입 ID
 * areaCode 지역코드
 * sigunguCode 시군구코드
 * cat1 대분류
 * cat2 중분류
 * cat3 소분류
 * modifiedtime 수정일
 *
 * 
 * @author oje-ung-ui-MacBookAir.local
 * @version
 */
@Data
@Getter
@Setter
public class TourAreaBasedListVo {
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
    private String modifiedtime = "";

    public TourAreaBasedListVo() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
        this.listYN = "Y";
    }
}
