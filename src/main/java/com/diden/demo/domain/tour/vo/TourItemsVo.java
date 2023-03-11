package com.diden.demo.domain.tour.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class TourItemsVo {
  @SerializedName("item")
  private List<TourItemVo> listTourItemVo = new ArrayList<TourItemVo>();
}
