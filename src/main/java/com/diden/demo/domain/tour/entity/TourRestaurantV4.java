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
@Table(name = "TB_TOUR_RESTAURANT_V4")
@AllArgsConstructor
public class TourRestaurantV4 implements TourEntitySupportInterface<TourRestaurantV4> {
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
    private Long seat;
    private String smoking;

    public TourRestaurantV4() {
    }

    public TourRestaurantV4(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
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
        return ServiceContentTypeCode.FOOD_TYPE.equals(contentType);
    }

    @Override
    public TourRestaurantV4 init(JsonObject jsonObject) {
        return new TourRestaurantV4(
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
        return ServiceContentTypeCode.FOOD_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.chkcreditcardfood = jsonObject.get("chkcreditcardfood").getAsString();
        this.discountinfofood = jsonObject.get("discountinfofood").getAsString();
        this.firstmenu = jsonObject.get("firstmenu").getAsString();
        this.infocenterfood = jsonObject.get("infocenterfood").getAsString();
        this.kidsfacility = jsonObject.get("kidsfacility").getAsString();
        this.opendatefood = jsonObject.get("opendatefood").getAsString();
        this.opentimefood = jsonObject.get("opentimefood").getAsString();
        this.packing = jsonObject.get("packing").getAsString();
        this.parkingfood = jsonObject.get("parkingfood").getAsString();
        this.reservationfood = jsonObject.get("reservationfood").getAsString();
        this.restdatefood = jsonObject.get("restdatefood").getAsString();
        this.scalefood = jsonObject.get("scalefood").getAsString();
        this.seat = jsonObject.get("seat").getAsLong();
        this.smoking = jsonObject.get("smoking").getAsString();
    }
}

