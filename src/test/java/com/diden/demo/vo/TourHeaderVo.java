package com.diden.demo.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourHeaderVo {
    @SerializedName("resultCode")
    private String resultCode = null;
    @SerializedName("resultMsg")
    private String resultMsg = null;
}
