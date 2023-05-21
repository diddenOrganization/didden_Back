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
    private String accomcountlodging;
    private String benikia;
    private String checkintime;
    private String checkouttime;
    private String chkcooking;
    private String foodplace;
    private String goodstay;
    private String hanok;
    private String infocenterlodging;
    private String parkinglodging;
    private String pickup;
    private String roomcount;
    private String reservationlodging;
    private String reservationurl;
    private String roomtype;
    private String scalelodging;
    private String subfacility;
    private String barbecue;
    private String beauty;
    private String beverage;
    private String bicycle;
    private String campfire;
    private String fitness;
    private String karaoke;
    private String publicbath;
    private String publicpc;
    private String sauna;
    private String seminar;
    private String sports;
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
