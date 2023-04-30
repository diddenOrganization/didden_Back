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
@Table(name = "TB_TOUR_ACCOMMODATION_V4")
@AllArgsConstructor
public class TourAccommodationV4 implements TourEntitySupportInterface<TourAccommodationV4> {
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

    private Long accomcountlodging;
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
    private Long roomcount;
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

    public TourAccommodationV4() {
    }

    public TourAccommodationV4(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
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
        return ServiceContentTypeCode.HOTEL_TYPE.equals(contentType);
    }

    @Override
    public TourAccommodationV4 init(JsonObject jsonObject) {
        return new TourAccommodationV4(
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
        return ServiceContentTypeCode.HOTEL_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.accomcountlodging = jsonObject.get("accomcountlodging").getAsLong();
        this.benikia = jsonObject.get("benikia").getAsString();
        this.checkintime = jsonObject.get("checkintime").getAsString();
        this.checkouttime = jsonObject.get("checkouttime").getAsString();
        this.chkcooking = jsonObject.get("chkcooking").getAsString();
        this.foodplace = jsonObject.get("foodplace").getAsString();
        this.goodstay = jsonObject.get("goodstay").getAsString();
        this.hanok = jsonObject.get("hanok").getAsString();
        this.infocenterlodging = jsonObject.get("infocenterlodging").getAsString();
        this.parkinglodging = jsonObject.get("parkinglodging").getAsString();
        this.pickup = jsonObject.get("pickup").getAsString();
        this.roomcount = jsonObject.get("roomcount").getAsLong();
        this.reservationlodging = jsonObject.get("reservationlodging").getAsString();
        this.reservationurl = jsonObject.get("reservationurl").getAsString();
        this.roomtype = jsonObject.get("roomtype").getAsString();
        this.scalelodging = jsonObject.get("scalelodging").getAsString();
        this.subfacility = jsonObject.get("subfacility").getAsString();
        this.barbecue = jsonObject.get("barbecue").getAsString();
        this.beauty = jsonObject.get("beauty").getAsString();
        this.beverage = jsonObject.get("beverage").getAsString();
        this.bicycle = jsonObject.get("bicycle").getAsString();
        this.campfire = jsonObject.get("campfire").getAsString();
        this.fitness = jsonObject.get("fitness").getAsString();
        this.karaoke = jsonObject.get("karaoke").getAsString();
        this.publicbath = jsonObject.get("publicbath").getAsString();
        this.publicpc = jsonObject.get("publicpc").getAsString();
        this.sauna = jsonObject.get("sauna").getAsString();
        this.seminar = jsonObject.get("seminar").getAsString();
        this.sports = jsonObject.get("sports").getAsString();
        this.refundregulation = jsonObject.get("refundregulation").getAsString();
    }


}
