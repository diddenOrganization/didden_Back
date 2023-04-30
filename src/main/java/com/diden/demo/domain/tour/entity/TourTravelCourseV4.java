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
public class TourTravelCourseV4 implements TourEntitySupportInterface<TourTravelCourseV4> {
    @Id
    private Long contentId;
    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private Integer serviceCode;
    private String highCode;
    private String middleCode;
    private String title;

    private String distance;
    private String infocentertourcourse;
    private String schedule;
    private String taketime;
    private String theme;

    public TourTravelCourseV4() {
    }

    public TourTravelCourseV4(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
        this.contentId = contentId;
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.TRAVEL_TYPE.equals(contentType);
    }

    @Override
    public TourTravelCourseV4 init(JsonObject jsonObject) {
        return new TourTravelCourseV4(
                jsonObject.get("contentid").getAsLong(),
                jsonObject.get("addr1").getAsString(),
                jsonObject.get("tel").getAsString(),
                jsonObject.get("firstimage").getAsString(),
                jsonObject.get("firstimage2").getAsString(),
                jsonObject.get("contenttypeid").getAsInt(),
                jsonObject.get("cat1").getAsString(),
                jsonObject.get("cat2").getAsString(),
                jsonObject.get("title").getAsString());
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
