package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourSearchFestivalVo {
    private String numOfRows = "";
    private String pageNo = "";
    private String mobileOS = "";
    private String mobileApp = "";
    private String listYN = "";
    private String arrange = "";
    private String areaCode = "";
    private String sigunguCode = "";
    private String eventStartDate = "";
    private String eventEndDate = "";
    private String modifiedtime = "";

    public TourSearchFestivalVo() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
    }
}
