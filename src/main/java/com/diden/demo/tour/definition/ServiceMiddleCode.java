package com.diden.demo.tour.definition;

import static com.diden.demo.tour.definition.ServiceContentTypeCode.*;

public enum ServiceMiddleCode implements MiddleCodeMapperType {
  NATURAL_TOURIST(ServiceHighCode.NATURE, "A0101", "자연관광지", TOURISM_TYPE),
  TOURISM_RESOURCES(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0102", "관광자원", TOURISM_TYPE),
  HISTORICAL_TOURIST(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0201", "역사관광지", TOURISM_TYPE),
  RECREATIONAL_RESORT(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0202", "휴양관광지", TOURISM_TYPE),
  EXPERIENCE_TOURIST(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0203", "체험관광지", TOURISM_TYPE),
  INDUSTRIAL_TOURIST(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0204", "산업관광지", TOURISM_TYPE),
  ARCHITECTURE_SCULPTURES(ServiceHighCode.TOURIST_LIBERAL_ARTS, "A0205", "건축/조형물", TOURISM_TYPE),
  CULTURAL_FACILITIES(
      ServiceHighCode.CULTURAL_LIBERAL_ARTS, "A0206", "문화시설", CULTURAL_FACILITIES_TYPE),
  FESTIVAL(ServiceHighCode.EVENT_LIBERAL_ARTS, "A0207", "축제", FESTIVAL_TYPE),
  PERFORMANCE_EVENT(ServiceHighCode.EVENT_LIBERAL_ARTS, "A0208", "공연/행사", FESTIVAL_TYPE),
  FAMILY_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0112", "가족코스", TRAVEL_TYPE),
  SOLO_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0113", "나홀로코스", TRAVEL_TYPE),
  HEALING_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0114", "힐링코스", TRAVEL_TYPE),
  WALKING_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0115", "도보코스", TRAVEL_TYPE),
  CAMPING_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0116", "캠핑코스", TRAVEL_TYPE),
  TASTE_COURSE(ServiceHighCode.RECOMMENDED_COURSE, "C0117", "맛코스", TRAVEL_TYPE),
  LEPORTS_COLLECTION(ServiceHighCode.LEPORTS, "A0301", "레포츠소개", LEPORTS_TYPE),
  ATHLETICS_LEPORTS(ServiceHighCode.LEPORTS, "A0302", "육상 레포츠", LEPORTS_TYPE),
  WATER_LEPORTS(ServiceHighCode.LEPORTS, "A0303", "수상 레포츠", LEPORTS_TYPE),
  AIR_SPORTS(ServiceHighCode.LEPORTS, "A0304", "항공 레포츠", LEPORTS_TYPE),
  COMBINED_LEPORTS(ServiceHighCode.LEPORTS, "A0305", "복합 레포츠", LEPORTS_TYPE),
  ACCOMMODATION(ServiceHighCode.ACCOMMODATION, "B0201", "숙박시설", HOTEL_TYPE),
  SHOPPING(ServiceHighCode.SHOPPING, "A0401", "쇼핑", SHOPPING_TYPE),
  FOOD(ServiceHighCode.FOOD, "A0502", "음식점", SHOPPING_TYPE),
  NONE(ServiceHighCode.NONE, "", null, null),
  ;
  private ServiceHighCode serviceHighCode;
  private String code;
  private String title;
  private ServiceContentTypeCode contentType;

  ServiceMiddleCode(
      ServiceHighCode serviceHighCode,
      String code,
      String title,
      ServiceContentTypeCode contentTypeCode) {
    this.serviceHighCode = serviceHighCode;
    this.code = code;
    this.title = title;
    this.contentType = contentTypeCode;
  }

  @Override
  public ServiceHighCode getServiceHighCode() {
    return serviceHighCode;
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
}