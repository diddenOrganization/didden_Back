package com.diden.demo.tour;

import lombok.Getter;

@Getter
public enum ServiceContentTypeCode {
  TOURISM_TYPE(12, "관광지"),
  CULTURAL_FACILITIES_TYPE(14, "문화시설"),
  FESTIVAL_TYPE(15, "행사/공연/축제"),
  TRAVEL_TYPE(25, "여행코스"),
  LEPORTS_TYPE(28, "레포츠"),
  HOTEL_TYPE(32, "숙박"),
  SHOPPING_TYPE(38, "쇼핑"),
  FOOD_TYPE(39, "음식점"),
  NONE(null, null),
  ;

  private final Integer code;
  private final String name;

  ServiceContentTypeCode(Integer code, String name) {
    this.code = code;
    this.name = name;
  }
}
