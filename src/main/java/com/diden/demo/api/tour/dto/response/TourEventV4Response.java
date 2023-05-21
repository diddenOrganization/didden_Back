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
    private String agelimit;
    private String bookingplace;
    private String discountinfofestival;
    private String eventenddate;
    private String eventhomepage;
    private String eventplace;
    private String eventstartdate;
    private String festivalgrade;
    private String placeinfo;
    private String playtime;
    private String program;
    private String spendtimefestival;
    private String sponsor1;
    private String sponsor1tel;
    private String sponsor2;
    private String sponsor2tel;
    private String subevent;
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



