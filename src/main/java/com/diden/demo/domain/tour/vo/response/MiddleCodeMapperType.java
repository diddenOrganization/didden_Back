package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;

public interface MiddleCodeMapperType {
    ServiceHighCode getServiceHighCode();
    String getCode();
    String getTitle();
    ServiceContentTypeCode getContentType();
}
