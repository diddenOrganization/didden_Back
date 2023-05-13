package com.diden.demo.api.tour.dto.request;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TourSearchRequestDto {
    @Schema(title = "페이지", description = "1,2,3,4 ... 한 페이지를 뜻 합니다.", defaultValue = "0")
    private Integer page;
    @Schema(title = "출력할 수", description = "한 페이지에 데이터가 N개 를 출력합니다.", defaultValue = "10")
    private Integer size;
    private List<ServiceContentTypeCode> serviceContentTypeCodes;
    private List<ServiceHighCode> serviceHighCodes;
    private List<ServiceMiddleCode> serviceMiddleCodes;
}
