package com.diden.demo.tour.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TourItemsVo {
    @SerializedName("item")
    private List<TourItemVo> listTourItemVo = new ArrayList<TourItemVo>();
}
