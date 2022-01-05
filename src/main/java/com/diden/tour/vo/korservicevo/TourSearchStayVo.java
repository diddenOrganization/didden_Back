package com.diden.tour.vo.korservicevo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourSearchStayVo {
    private String numOfRows = "";
    private String pageNo = "";
    private String mobileOS = "";
    private String mobileApp = "";
    private String listYN = "";
    private String arrange = "";
    private String areaCode = "";
    private String sigunguCode = "";
    private String hanOk = "";
    private String benikia = "";
    private String goodStay = "";
    private String modifiedtime = "";

    public TourSearchStayVo() {
        this.mobileApp = "AppTest";
        this.mobileOS = "ETC";
        this.numOfRows = "999";
    }
}
