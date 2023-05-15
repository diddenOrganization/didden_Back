package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MiddleCodeMapperValue {
  private ServiceHighCode serviceHighCode;
  private String code;
  private String title;
  private ServiceContentTypeCode contentTypeCode;
  private String codeName;

  public MiddleCodeMapperValue(MiddleCodeMapperType middleCodeMapperType) {
    this.serviceHighCode = middleCodeMapperType.getServiceHighCode();
    this.code = middleCodeMapperType.getCode();
    this.title = middleCodeMapperType.getTitle();
    this.contentTypeCode = middleCodeMapperType.getContentType();
    this.codeName = middleCodeMapperType.getCodeName();
  }
}
