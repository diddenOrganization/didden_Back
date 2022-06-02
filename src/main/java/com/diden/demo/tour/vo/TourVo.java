package com.diden.demo.tour.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourVo {
    @SerializedName("header")
    private TourHeaderVo tourHeaderVo = new TourHeaderVo();
    @SerializedName("body")
    private TourBodyVo tourBodyVo = new TourBodyVo();
}
