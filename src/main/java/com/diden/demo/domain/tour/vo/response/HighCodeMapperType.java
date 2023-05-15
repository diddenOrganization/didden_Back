package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;

public interface HighCodeMapperType {
    String getCode();
    String getTitle();
    ServiceContentTypeCode getContentType();
    String getCodeName();
}
