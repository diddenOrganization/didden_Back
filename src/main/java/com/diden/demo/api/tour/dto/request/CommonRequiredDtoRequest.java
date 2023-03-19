package com.diden.demo.api.tour.dto.request;

import com.diden.demo.common.config.properties.TourProperties;
import com.diden.demo.domain.tour.enums.MobileOSType;
import lombok.Getter;

import java.util.Optional;

@Getter
public abstract class CommonRequiredDtoRequest {
    private String _type;
    private String mobileApp;
    private MobileOSType mobileOS;
    private String serviceKey;

    public CommonRequiredDtoRequest() {
    }

    public CommonRequiredDtoRequest(MobileOSType mobileOS) {
        this._type = "json";
        this.mobileApp = "DIDDEN";
        this.mobileOS = Optional.ofNullable(mobileOS).orElse(MobileOSType.ETC);
        this.serviceKey = TourProperties.SERVICE_DEV_KEY_ORIGIN;
    }
}
