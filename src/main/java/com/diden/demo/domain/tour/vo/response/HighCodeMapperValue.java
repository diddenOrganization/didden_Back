package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HighCodeMapperValue {
  @Schema(title = "관공사 코드", description = "관공사에서 정의해준 코드 값 입니다.")
  private String code;
  @Schema(title = "관공사 코드 이름", description = "관공사에서 정의해준 코드 명 입니다.")
  private String title;
  @Schema(title = "컨텐츠 타입 코드", description = "컨텐츠 타입 코드 하위 목록이 어떤 대분류 코드인지 알 수 있습니다.")
  private ServiceContentTypeCode contentTypeCode;
  @Schema(title = "코드 재정의", description = "우리 서버에서 재정의한 코드입니다. 해당 코드를 사용해 조회 할 수 있습니다.")
  private String codeName;

  public HighCodeMapperValue() {
  }

  public HighCodeMapperValue(HighCodeMapperType highCodeMapperType) {
    this.code = highCodeMapperType.getCode();
    this.title = highCodeMapperType.getTitle();
    this.contentTypeCode = highCodeMapperType.getContentType();
    this.codeName = highCodeMapperType.getCodeName();
  }
}
