package com.diden.demo.domain.tour.enums;

import com.diden.demo.common.utils.ValidatorUtils;
import com.diden.demo.domain.tour.vo.response.CommonCodeMapperType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter

public enum ServiceContentTypeCode implements CommonCodeMapperType {
  TOURISM_TYPE_A01(12, "관광지"),
  TOURISM_TYPE_A02(12, "관광지"),
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

  public static ServiceContentTypeCode codeToEnum(Integer code) {
    return Arrays.stream(ServiceContentTypeCode.values())
            .filter(v -> v.getCode().equals(code))
            .findFirst()
            .orElse(null);
  }


  public String getName() {
    return name();
  }

  @Override
  public String getCodeName() {
    return name();
  }

  public String getCodeTypeCasting() {
    return this.code.toString();
  }

  public boolean isPresentA02() {
    return Objects.equals(ServiceContentTypeCode.TOURISM_TYPE_A02, this);
  }

  public static boolean isNullOrEmpty(List<ServiceContentTypeCode> serviceContentTypeCodes) {
    return ValidatorUtils.isListNullOrEmpty(serviceContentTypeCodes);
  }

  public static boolean isNotNullOrEmpty(List<ServiceContentTypeCode> serviceContentTypeCodes) {
    return !ValidatorUtils.isListNullOrEmpty(serviceContentTypeCodes);
  }

  public static List<ServiceContentTypeCode> codeObjectsChangeByParameters(List<String> parameters) {
    if (Objects.isNull(parameters) || parameters.isEmpty()) {
      return null;
    }

    return parameters.stream()
            .map(o -> ServiceContentTypeCode.valueOf(o))
            .collect(Collectors.toList());
  }
}
