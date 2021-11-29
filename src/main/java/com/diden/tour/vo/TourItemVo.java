package com.diden.tour.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourItemVo {
    @SerializedName("galContentId")
    private String galContentId = null;
    @SerializedName("galContentTypeId")
    private String galContentTypeId = null;
    @SerializedName("galCreatedtime")
    private String galCreatedtime = null;
    @SerializedName("galModifiedtime")
    private String galModifiedtime = null;
    @SerializedName("galPhotographer")
    private String galPhotographer = null;
    @SerializedName("galPhotographyLocation")
    private String galPhotographyLocation = null;
    @SerializedName("galPhotographyMonth")
    private String galPhotographyMonth = null;
    @SerializedName("galSearchKeyword")
    private String galSearchKeyword = null;
    @SerializedName("galTitle")
    private String galTitle = null;
    @SerializedName("galViewCount")
    private String galViewCount = null;
    @SerializedName("galWebImageUrl")
    private String galWebImageUrl = null;
}
