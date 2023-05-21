package com.diden.demo.domain.tour.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommonCodeMapperValue {
    @Schema(title = "관공사 코드", description = "관공사에서 정의해준 코드 값 입니다.")
    private Integer code;
    @Schema(title = "관공사 코드 이름", description = "관공사에서 정의해준 코드 명 입니다.")
    private String title;
    @Schema(title = "코드 재정의", description = "우리 서버에서 재정의한 코드입니다. 해당 코드를 사용해 조회 할 수 있습니다.")
    private String codeName;

    public CommonCodeMapperValue() {
    }

    public CommonCodeMapperValue(CommonCodeMapperType commonCodeMapperType) {
        this.code = commonCodeMapperType.getCode();
        this.title = commonCodeMapperType.getTitle();
        this.codeName = commonCodeMapperType.getCodeName();
    }
}
