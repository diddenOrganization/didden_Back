package com.diden.demo.domain.tour.entity;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.google.gson.JsonObject;

public interface TourEntitySupportInterface<T> {
    boolean isContentTypeSupported(ServiceContentTypeCode contentType);
    String toString();
    T init(JsonObject jsonObject);

    ServiceContentTypeCode getContentTypeCode();

    Long getContentId();

    void settingValue(JsonObject jsonObject);
}
