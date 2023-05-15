package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HighCodeMapperValue {
  private String code;
  private String title;
  private ServiceContentTypeCode contentTypeCode;
  private String codeName;

  public HighCodeMapperValue(HighCodeMapperType highCodeMapperType) {
    this.code = highCodeMapperType.getCode();
    this.title = highCodeMapperType.getTitle();
    this.contentTypeCode = highCodeMapperType.getContentType();
    this.codeName = highCodeMapperType.getCodeName();
  }
}
