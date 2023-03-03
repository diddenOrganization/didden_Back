package com.diden.demo.domain.tour._vo;

import com.diden.demo.domain.tour.definition.MobileOSType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonTourInitVo {
    MobileOSType mobileOS;
    String mobileApp;
    Integer numOfRows;
}
