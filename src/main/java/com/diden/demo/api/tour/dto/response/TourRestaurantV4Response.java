package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourRecreationV4;
import com.diden.demo.domain.tour.entity.TourRestaurantV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourRestaurantV4Response - FOOD_TYPE", description = "FOOD_TYPE = 음식점")
public class TourRestaurantV4Response implements TourEntitySupportResponseInterface {

    private Long contentId;
    private String chkcreditcardfood;
    private String discountinfofood;
    private String firstmenu;
    private String infocenterfood;
    private String kidsfacility;
    private String opendatefood;
    private String opentimefood;
    private String packing;
    private String parkingfood;
    private String reservationfood;
    private String restdatefood;
    private String scalefood;
    private String seat;
    private String smoking;

    public TourRestaurantV4Response() {
    }

    @Builder
    public TourRestaurantV4Response(Long contentId, String chkcreditcardfood, String discountinfofood, String firstmenu, String infocenterfood, String kidsfacility, String opendatefood, String opentimefood, String packing, String parkingfood, String reservationfood, String restdatefood, String scalefood, String seat, String smoking) {
        this.contentId = contentId;
        this.chkcreditcardfood = chkcreditcardfood;
        this.discountinfofood = discountinfofood;
        this.firstmenu = firstmenu;
        this.infocenterfood = infocenterfood;
        this.kidsfacility = kidsfacility;
        this.opendatefood = opendatefood;
        this.opentimefood = opentimefood;
        this.packing = packing;
        this.parkingfood = parkingfood;
        this.reservationfood = reservationfood;
        this.restdatefood = restdatefood;
        this.scalefood = scalefood;
        this.seat = seat;
        this.smoking = smoking;
    }

    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourRestaurantV4 tourRestaurantV4 = (TourRestaurantV4) entity;
        return TourRestaurantV4Response.builder()
                .contentId(tourRestaurantV4.getContentId())
                .chkcreditcardfood(tourRestaurantV4.getChkcreditcardfood())
                .discountinfofood(tourRestaurantV4.getDiscountinfofood())
                .firstmenu(tourRestaurantV4.getFirstmenu())
                .infocenterfood(tourRestaurantV4.getInfocenterfood())
                .kidsfacility(tourRestaurantV4.getKidsfacility())
                .opendatefood(tourRestaurantV4.getOpendatefood())
                .opentimefood(tourRestaurantV4.getOpentimefood())
                .packing(tourRestaurantV4.getPacking())
                .parkingfood(tourRestaurantV4.getParkingfood())
                .reservationfood(tourRestaurantV4.getReservationfood())
                .restdatefood(tourRestaurantV4.getRestdatefood())
                .scalefood(tourRestaurantV4.getScalefood())
                .seat(tourRestaurantV4.getSeat())
                .smoking(tourRestaurantV4.getSmoking())
                .build();
    }
}

