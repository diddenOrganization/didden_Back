package com.diden.demo.tour.definition;

import lombok.Getter;

import java.util.Arrays;

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
  ;

  private final Integer code;
  private final String title;

  ServiceContentTypeCode(Integer code, String title) {
    this.code = code;
    this.title = title;
  }

  public String getName() {
    return name();
  }

  public static ServiceContentTypeCode codeToEnum(Integer code) {
    return Arrays.stream(ServiceContentTypeCode.values())
        .filter(v -> v.getCode().equals(code))
        .findFirst()
        .orElse(null);
  }
}
