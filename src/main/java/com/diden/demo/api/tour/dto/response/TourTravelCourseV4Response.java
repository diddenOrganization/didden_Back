package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourTourismV4;
import com.diden.demo.domain.tour.entity.TourTravelCourseV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourTravelCourseV4Response - TRAVEL_TYPE", description = "TRAVEL_TYPE = 여행코스")
public class TourTravelCourseV4Response implements TourEntitySupportResponseInterface {

    private Long contentId;
    private String distance;
    private String infocentertourcourse;
    private String schedule;
    private String taketime;
    private String theme;

    public TourTravelCourseV4Response() {
    }

    @Builder
    public TourTravelCourseV4Response(Long contentId, String distance, String infocentertourcourse, String schedule, String taketime, String theme) {
        this.contentId = contentId;
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
                .distance(tourTravelCourseV4.getDistance())
                .infocentertourcourse(tourTravelCourseV4.getInfocentertourcourse())
                .schedule(tourTravelCourseV4.getSchedule())
                .taketime(tourTravelCourseV4.getTaketime())
                .theme(tourTravelCourseV4.getTheme())
                .build();
    }
}
