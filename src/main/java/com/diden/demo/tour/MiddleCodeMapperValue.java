package com.diden.demo.tour;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MiddleCodeMapperValue {
  private ServiceHighCode serviceHighCode;
  private String code;
  private String title;
  private ServiceContentTypeCode contentTypeCode;

  public MiddleCodeMapperValue(MiddleCodeMapperType middleCodeMapperType) {
    this.serviceHighCode = middleCodeMapperType.getServiceHighCode();
    this.code = middleCodeMapperType.getCode();
    this.title = middleCodeMapperType.getTitle();
    this.contentTypeCode = middleCodeMapperType.getContentType();
  }
}
