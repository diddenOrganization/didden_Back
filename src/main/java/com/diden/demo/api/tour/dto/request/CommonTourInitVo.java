package com.diden.demo.api.tour.dto.request;

import com.diden.demo.domain.tour.enums.MobileOSType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonTourInitVo {
    MobileOSType mobileOS;
    String mobileApp;
    Integer numOfRows;
}
