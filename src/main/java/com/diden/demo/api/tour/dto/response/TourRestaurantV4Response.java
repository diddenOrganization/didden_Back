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
    @Schema(title = "신용카드 가능 여부")
    private String chkcreditcardfood;
    @Schema(title = "할인 정보")
    private String discountinfofood;
    @Schema(title = "대표 메뉴")
    private String firstmenu;
    @Schema(title = "문의 및 안내")
    private String infocenterfood;
    @Schema(title = "어린이 놀이방 여부")
    private String kidsfacility;
    @Schema(title = "개업일")
    private String opendatefood;
    @Schema(title = "영업시간")
    private String opentimefood;
    @Schema(title = "포장 가능 여부")
    private String packing;
    @Schema(title = "주차시설")
    private String parkingfood;
    @Schema(title = "예약 안내")
    private String reservationfood;
    @Schema(title = "쉬는 날")
    private String restdatefood;
    @Schema(title = "규모")
    private String scalefood;
    @Schema(title = "좌석 수")
    private String seat;
    @Schema(title = "금연/흡연 여부")
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

