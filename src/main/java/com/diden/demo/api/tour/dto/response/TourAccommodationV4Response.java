package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourAccommodationV4Response - HOTEL_TYPE", description = "HOTEL_TYPE = 숙박")
public class TourAccommodationV4Response implements TourEntitySupportResponseInterface {
    private Long contentId;
    @Schema(title = "수용가능인원")
    private String accomcountlodging;
    @Schema(title = "베니키아여부")
    private String benikia;
    @Schema(title = "입실시간")
    private String checkintime;
    @Schema(title = "퇴실시간")
    private String checkouttime;
    @Schema(title = "객실내취사여부")
    private String chkcooking;
    @Schema(title = "식음료장")
    private String foodplace;
    @Schema(title = "굿스테이여부")
    private String goodstay;
    @Schema(title = "한옥여부")
    private String hanok;
    @Schema(title = "문의및안내")
    private String infocenterlodging;
    @Schema(title = "주차시설")
    private String parkinglodging;
    @Schema(title = "픽업서비스")
    private String pickup;
    @Schema(title = "객실수")
    private String roomcount;
    @Schema(title = "예약안내")
    private String reservationlodging;
    @Schema(title = "예약안내홈페이지")
    private String reservationurl;
    @Schema(title = "객실유형")
    private String roomtype;
    @Schema(title = "규모")
    private String scalelodging;
    @Schema(title = "부대시설 (기타)")
    private String subfacility;
    @Schema(title = "바비큐장여부")
    private String barbecue;
    @Schema(title = "뷰티시설정보")
    private String beauty;
    @Schema(title = "식음료장여부")
    private String beverage;
    @Schema(title = "자전거대여여부")
    private String bicycle;
    @Schema(title = "캠프파이어여부")
    private String campfire;
    @Schema(title = "휘트니스센터여부")
    private String fitness;
    @Schema(title = "노래방여부")
    private String karaoke;
    @Schema(title = "공용샤워실여부")
    private String publicbath;
    @Schema(title = "공용 PC실여부")
    private String publicpc;
    @Schema(title = "사우나실여부")
    private String sauna;
    @Schema(title = "세미나실여부")
    private String seminar;
    @Schema(title = "스포츠시설여부")
    private String sports;
    @Schema(title = "환불규정")
    private String refundregulation;

    public TourAccommodationV4Response() {
    }

    @Builder
    public TourAccommodationV4Response(Long contentId, String accomcountlodging, String benikia, String checkintime, String checkouttime, String chkcooking, String foodplace, String goodstay, String hanok, String infocenterlodging, String parkinglodging, String pickup, String roomcount, String reservationlodging, String reservationurl, String roomtype, String scalelodging, String subfacility, String barbecue, String beauty, String beverage, String bicycle, String campfire, String fitness, String karaoke, String publicbath, String publicpc, String sauna, String seminar, String sports, String refundregulation) {
        this.contentId = contentId;
        this.accomcountlodging = accomcountlodging;
        this.benikia = benikia;
        this.checkintime = checkintime;
        this.checkouttime = checkouttime;
        this.chkcooking = chkcooking;
        this.foodplace = foodplace;
        this.goodstay = goodstay;
        this.hanok = hanok;
        this.infocenterlodging = infocenterlodging;
        this.parkinglodging = parkinglodging;
        this.pickup = pickup;
        this.roomcount = roomcount;
        this.reservationlodging = reservationlodging;
        this.reservationurl = reservationurl;
        this.roomtype = roomtype;
        this.scalelodging = scalelodging;
        this.subfacility = subfacility;
        this.barbecue = barbecue;
        this.beauty = beauty;
        this.beverage = beverage;
        this.bicycle = bicycle;
        this.campfire = campfire;
        this.fitness = fitness;
        this.karaoke = karaoke;
        this.publicbath = publicbath;
        this.publicpc = publicpc;
        this.sauna = sauna;
        this.seminar = seminar;
        this.sports = sports;
        this.refundregulation = refundregulation;
    }


    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourAccommodationV4 tourAccommodationV4 = (TourAccommodationV4) entity;
        return TourAccommodationV4Response.builder()
                .contentId(tourAccommodationV4.getContentId())
                .accomcountlodging(tourAccommodationV4.getAccomcountlodging())
                .benikia(tourAccommodationV4.getBenikia())
                .checkintime(tourAccommodationV4.getCheckintime())
                .checkouttime(tourAccommodationV4.getCheckouttime())
                .chkcooking(tourAccommodationV4.getChkcooking())
                .foodplace(tourAccommodationV4.getFoodplace())
                .goodstay(tourAccommodationV4.getGoodstay())
                .hanok(tourAccommodationV4.getHanok())
                .infocenterlodging(tourAccommodationV4.getInfocenterlodging())
                .parkinglodging(tourAccommodationV4.getParkinglodging())
                .pickup(tourAccommodationV4.getPickup())
                .roomcount(tourAccommodationV4.getRoomcount())
                .reservationlodging(tourAccommodationV4.getReservationlodging())
                .reservationurl(tourAccommodationV4.getReservationurl())
                .roomtype(tourAccommodationV4.getRoomtype())
                .scalelodging(tourAccommodationV4.getScalelodging())
                .subfacility(tourAccommodationV4.getSubfacility())
                .barbecue(tourAccommodationV4.getBarbecue())
                .beauty(tourAccommodationV4.getBeauty())
                .beverage(tourAccommodationV4.getBeverage())
                .bicycle(tourAccommodationV4.getBicycle())
                .campfire(tourAccommodationV4.getCampfire())
                .fitness(tourAccommodationV4.getFitness())
                .karaoke(tourAccommodationV4.getKaraoke())
                .publicbath(tourAccommodationV4.getPublicbath())
                .publicpc(tourAccommodationV4.getPublicpc())
                .sauna(tourAccommodationV4.getSauna())
                .seminar(tourAccommodationV4.getSeminar())
                .sports(tourAccommodationV4.getSports())
                .refundregulation(tourAccommodationV4.getRefundregulation())
                .build();
    }
}
