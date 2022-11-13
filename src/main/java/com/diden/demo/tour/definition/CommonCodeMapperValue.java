package com.diden.demo.tour.definition;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommonCodeMapperValue {
    private Integer code;
    private String title;
    private String codeName;

    public CommonCodeMapperValue(CommonCodeMapperType commonCodeMapperType) {
        this.code = commonCodeMapperType.getCode();
        this.title = commonCodeMapperType.getTitle();
        this.codeName = commonCodeMapperType.getCodeName();
    }
}
