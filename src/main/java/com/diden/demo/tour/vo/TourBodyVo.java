package com.diden.demo.tour.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourBodyVo {
  @SerializedName("items")
  private TourItemsVo tourItemsVo = new TourItemsVo();

  @SerializedName("numOfRows")
  private String numOfRows = null;

  @SerializedName("pageNo")
  private String pageNo = null;

  @SerializedName("totalCount")
  private String totalCount = null;
}
