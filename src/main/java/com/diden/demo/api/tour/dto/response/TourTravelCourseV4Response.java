package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourTourismV4;
import com.diden.demo.domain.tour.entity.TourTravelCourseV4;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourTravelCourseV4Response - TRAVEL_TYPE", description = "TRAVEL_TYPE = 여행코스")
public class TourTravelCourseV4Response implements TourEntitySupportResponseInterface {

    private Long contentId;

    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private ServiceContentTypeCode serviceCode;
    private ServiceHighCode highCode;
    private ServiceMiddleCode middleCode;
    private String title;

    @Schema(title = "코스총거리")
    private String distance;
    @Schema(title = "문의및안내")
    private String infocentertourcourse;
    @Schema(title = "코스일정")
    private String schedule;
    @Schema(title = "코스총소요시간")
    private String taketime;
    @Schema(title = "코스테마")
    private String theme;

    public TourTravelCourseV4Response() {
    }

    @Builder
    public TourTravelCourseV4Response(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, ServiceContentTypeCode serviceCode, ServiceHighCode highCode, ServiceMiddleCode middleCode, String title, String distance, String infocentertourcourse, String schedule, String taketime, String theme) {
        this.contentId = contentId;
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
        this.distance = distance;
        this.infocentertourcourse = infocentertourcourse;
        this.schedule = schedule;
        this.taketime = taketime;
        this.theme = theme;
    }




    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourTravelCourseV4 tourTravelCourseV4 = (TourTravelCourseV4) entity;
        return TourTravelCourseV4Response.builder()
                .contentId(tourTravelCourseV4.getContentId())

                .address(tourTravelCourseV4.getAddress())
                .cellphone(tourTravelCourseV4.getCellphone())
                .detailImage(tourTravelCourseV4.getDetailImage())
                .thumbnailImage(tourTravelCourseV4.getThumbnailImage())
                .serviceCode(tourTravelCourseV4.getServiceCode())
                .highCode(tourTravelCourseV4.getHighCode())
                .middleCode(tourTravelCourseV4.getMiddleCode())
                .title(tourTravelCourseV4.getTitle())

                .distance(tourTravelCourseV4.getDistance())
                .infocentertourcourse(tourTravelCourseV4.getInfocentertourcourse())
                .schedule(tourTravelCourseV4.getSchedule())
                .taketime(tourTravelCourseV4.getTaketime())
                .theme(tourTravelCourseV4.getTheme())
                .build();
    }
}
