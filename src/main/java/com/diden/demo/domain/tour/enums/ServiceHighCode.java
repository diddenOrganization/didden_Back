package com.diden.demo.domain.tour.enums;

import com.diden.demo.common.utils.ValidatorUtils;
import com.diden.demo.domain.tour.vo.response.HighCodeMapperType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.diden.demo.domain.tour.enums.ServiceContentTypeCode.*;

public enum ServiceHighCode implements HighCodeMapperType {
  NATURE("A01", "자연", TOURISM_TYPE_A01),
  TOURIST_LIBERAL_ARTS("A02", "관광지_인문(문화/예술/역사)", TOURISM_TYPE_A02),
  CULTURAL_LIBERAL_ARTS("A02", "문화시설_인문(문화/예술/역사)", CULTURAL_FACILITIES_TYPE),
  EVENT_LIBERAL_ARTS("A02", "행사_인문(문화/예술/역사)", FESTIVAL_TYPE),
  RECOMMENDED_COURSE("C01", "추천코스", TRAVEL_TYPE),
  LEPORTS("A03", "레포츠", LEPORTS_TYPE),
  ACCOMMODATION("B02", "숙박", HOTEL_TYPE),
  SHOPPING("A04", "쇼핑", SHOPPING_TYPE),
  FOOD("A05", "음식", FOOD_TYPE),
  NONE("", null, null),
  ;

  private String code;
  private String title;
  private ServiceContentTypeCode contentType;

  ServiceHighCode(String code, String title, ServiceContentTypeCode contentType) {
    this.code = code;
    this.title = title;
    this.contentType = contentType;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public ServiceContentTypeCode getContentType() {
    return contentType;
  }

  @Override
  public String getCodeName() {
    return name();
  }

  public static ServiceHighCode codeToEnum(String code) {
    return Arrays.stream(ServiceHighCode.values())
        .filter(v -> v.getCode().equals(code))
        .findFirst()
        .orElse(ServiceHighCode.NONE);
  }

  public static boolean isNullOrEmpty(List<ServiceHighCode> serviceHighCodes) {
    return ValidatorUtils.isListNullOrEmpty(serviceHighCodes);
  }

  public boolean isEqualsTitle(final String title) {
    return StringUtils.equals(this.getTitle(), title);
  }
}
