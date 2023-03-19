package com.diden.demo.api.tour.feign;

import com.diden.demo.common.config.properties.TourProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TestRequestModel {
    private String serviceKey;
    private String mobileOS;
    private String mobileApp;
    private String _type;
    private Integer contentTypeId;

    public TestRequestModel() {
    }

    @Builder
    public TestRequestModel(String serviceKey, String mobileOS, String mobileApp, String _type, Integer contentTypeId) {
        this.serviceKey = serviceKey;
        this.mobileOS = mobileOS;
        this.mobileApp = mobileApp;
        this._type = _type;
        this.contentTypeId = contentTypeId;
    }

    public static TestRequestModel init(Integer contentTypeId) {
        return TestRequestModel.builder()
                .serviceKey(TourProperties.SERVICE_DEV_KEY_ORIGIN)
                .mobileOS("ETC")
                .mobileApp("DIDDEN")
                ._type("json")
                .contentTypeId(contentTypeId)
                .build();
    }
}
