package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;

import javax.persistence.Id;

import com.diden.demo.domain.tour.entity.TourEventV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourEventV4Response - FESTIVAL_TYPE", description = "FESTIVAL_TYPE = 행사/공연/축제")
public class TourEventV4Response implements TourEntitySupportResponseInterface{
    private Long contentId;
    @Schema(title = "관람 가능 연령")
    private String agelimit;
    @Schema(title = "예매처")
    private String bookingplace;
    @Schema(title = "할인 정보")
    private String discountinfofestival;
    @Schema(title = "행사 종료일")
    private String eventenddate;
    @Schema(title = "행사 홈페이지")
    private String eventhomepage;
    @Schema(title = "행사 장소")
    private String eventplace;
    @Schema(title = "행사 시작일")
    private String eventstartdate;
    @Schema(title = "축제 등급")
    private String festivalgrade;
    @Schema(title = "행사장 위치 안내")
    private String placeinfo;
    @Schema(title = "공연 시간")
    private String playtime;
    @Schema(title = "행사 프로그램")
    private String program;
    @Schema(title = "관람 소요 시간")
    private String spendtimefestival;
    @Schema(title = "주최자 정보")
    private String sponsor1;
    @Schema(title = "주최자 연락처")
    private String sponsor1tel;
    @Schema(title = "주관사 정보")
    private String sponsor2;
    @Schema(title = "주관사 연락처")
    private String sponsor2tel;
    @Schema(title = "부대 행사")
    private String subevent;
    @Schema(title = "이용 요금")
    private String usetimefestival;

    public TourEventV4Response() {
    }

    @Builder
    public TourEventV4Response(Long contentId, String agelimit, String bookingplace, String discountinfofestival, String eventenddate, String eventhomepage, String eventplace, String eventstartdate, String festivalgrade, String placeinfo, String playtime, String program, String spendtimefestival, String sponsor1, String sponsor1tel, String sponsor2, String sponsor2tel, String subevent, String usetimefestival) {
        this.contentId = contentId;
        this.agelimit = agelimit;
        this.bookingplace = bookingplace;
        this.discountinfofestival = discountinfofestival;
        this.eventenddate = eventenddate;
        this.eventhomepage = eventhomepage;
        this.eventplace = eventplace;
        this.eventstartdate = eventstartdate;
        this.festivalgrade = festivalgrade;
        this.placeinfo = placeinfo;
        this.playtime = playtime;
        this.program = program;
        this.spendtimefestival = spendtimefestival;
        this.sponsor1 = sponsor1;
        this.sponsor1tel = sponsor1tel;
        this.sponsor2 = sponsor2;
        this.sponsor2tel = sponsor2tel;
        this.subevent = subevent;
        this.usetimefestival = usetimefestival;
    }

    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourEventV4 tourEventV4 = (TourEventV4) entity;
        return TourEventV4Response.builder()
                .contentId(tourEventV4.getContentId())
                .agelimit(tourEventV4.getAgelimit())
                .bookingplace(tourEventV4.getBookingplace())
                .discountinfofestival(tourEventV4.getDiscountinfofestival())
                .eventenddate(tourEventV4.getEventenddate())
                .eventhomepage(tourEventV4.getEventhomepage())
                .eventplace(tourEventV4.getEventplace())
                .eventstartdate(tourEventV4.getEventstartdate())
                .festivalgrade(tourEventV4.getFestivalgrade())
                .placeinfo(tourEventV4.getPlaceinfo())
                .playtime(tourEventV4.getPlaytime())
                .program(tourEventV4.getProgram())
                .spendtimefestival(tourEventV4.getSpendtimefestival())
                .sponsor1(tourEventV4.getSponsor1())
                .sponsor1tel(tourEventV4.getSponsor1tel())
                .sponsor2(tourEventV4.getSponsor2())
                .sponsor2tel(tourEventV4.getSponsor2tel())
                .subevent(tourEventV4.getSubevent())
                .usetimefestival(tourEventV4.getUsetimefestival())
                .build();
    }
}



