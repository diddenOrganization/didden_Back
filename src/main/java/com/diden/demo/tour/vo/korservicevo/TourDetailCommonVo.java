package com.diden.demo.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourDetailCommonVo {
    private String numOfRows = "";
    private String pageNo = "";
    private String mobileOS = "";
    private String mobileApp = "";
    private String contentId = "";
    private String contentTypeId = "";
    private String defaultYN = "";
    private String firstImageYN = "";
    private String areacodeYN = "";
    private String catcodeYN = "";
    private String addrinfoYN = "";
    private String mapinfoYN = "";
    private String overviewYN = "";

    public TourDetailCommonVo() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
    }
}
