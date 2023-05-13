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
public class TourRestaurantV4 extends TourCommonEntityV4 implements TourEntitySupportInterface<TourRestaurantV4> {
    @Id
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

    public TourRestaurantV4() {
    }

    public TourRestaurantV4(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.FOOD_TYPE.equals(contentType);
    }

    @Override
    public TourRestaurantV4 init(JsonObject jsonObject) {
        super.commonInit(jsonObject);
        return new TourRestaurantV4(jsonObject.get("contentid").getAsLong());
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
        this.seat = jsonObject.get("seat").getAsString();
        this.smoking = jsonObject.get("smoking").getAsString();
    }
}

