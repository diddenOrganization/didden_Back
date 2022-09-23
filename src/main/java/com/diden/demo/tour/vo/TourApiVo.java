package com.diden.demo.tour.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourApiVo {
  @SerializedName("numOfRows")
  private String numOfRows = null;

  @SerializedName("pageNo")
  private String pageNo = null;

  @SerializedName("MobileOS")
  private String MobileOS = null;

  @SerializedName("MobileApp")
  private String MobileApp = null;

  @SerializedName("contentId")
  private String contentId = null;

  @SerializedName("contentTypeId")
  private String contentTypeId = null;

  @SerializedName("defaultYN")
  private String defaultYN = null;

  @SerializedName("firstImageYN")
  private String firstImageYN = null;

  @SerializedName("areacodeYN")
  private String areacodeYN = null;

  @SerializedName("catcodeYN")
  private String catcodeYN = null;

  @SerializedName("addrinfoYN")
  private String addrinfoYN = null;

  @SerializedName("overviewYN")
  private String overviewYN = null;

  @SerializedName("mapinfoYN")
  private String mapinfoYN = null;
}
