package com.diden.demo.tour._vo;

import com.diden.demo.tour.definition.MobileOSType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonTourInitVo {
    MobileOSType mobileOS;
    String mobileApp;
    Integer numOfRows;
}
