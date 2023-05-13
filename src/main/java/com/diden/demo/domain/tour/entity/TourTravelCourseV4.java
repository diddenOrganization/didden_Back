package com.diden.demo.domain.tour.entity;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@ToString
@Table(name = "TB_TOUR_TRAVEL_COURSE_V4")
@AllArgsConstructor
public class TourTravelCourseV4 extends TourCommonEntityV4 implements TourEntitySupportInterface<TourTravelCourseV4> {
    @Id
    private Long contentId;

    private String distance;
    private String infocentertourcourse;
    private String schedule;
    private String taketime;
    private String theme;

    public TourTravelCourseV4() {
    }

    public TourTravelCourseV4(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.TRAVEL_TYPE.equals(contentType);
    }

    @Override
    public TourTravelCourseV4 init(JsonObject jsonObject) {
        super.commonInit(jsonObject);
        return new TourTravelCourseV4(jsonObject.get("contentid").getAsLong());
    }

    @Override
    public ServiceContentTypeCode getContentTypeCode() {
        return ServiceContentTypeCode.TRAVEL_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.distance = jsonObject.get("distance").getAsString();
        this.infocentertourcourse = jsonObject.get("infocentertourcourse").getAsString();
        this.schedule = jsonObject.get("schedule").getAsString();
        this.taketime = jsonObject.get("taketime").getAsString();
        this.theme = jsonObject.get("theme").getAsString();
    }
}
