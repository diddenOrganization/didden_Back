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
@Table(name = "TB_TOUR_EVENT_V4")
@AllArgsConstructor
public class TourEventV4 extends TourCommonEntityV4 implements TourEntitySupportInterface<TourEventV4> {
    @Id
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

    public TourEventV4() {
    }

    public TourEventV4(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.FESTIVAL_TYPE.equals(contentType);
    }

    @Override
    public TourEventV4 init(JsonObject jsonObject) {
        super.commonInit(jsonObject);
        return new TourEventV4(jsonObject.get("contentid").getAsLong());
    }

    @Override
    public ServiceContentTypeCode getContentTypeCode() {
        return ServiceContentTypeCode.FESTIVAL_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.agelimit = jsonObject.get("agelimit").getAsString();
        this.bookingplace = jsonObject.get("bookingplace").getAsString();
        this.discountinfofestival = jsonObject.get("discountinfofestival").getAsString();
        this.eventenddate = jsonObject.get("eventenddate").getAsString();
        this.eventhomepage = jsonObject.get("eventhomepage").getAsString();
        this.eventplace = jsonObject.get("eventplace").getAsString();
        this.eventstartdate = jsonObject.get("eventstartdate").getAsString();
        this.festivalgrade = jsonObject.get("festivalgrade").getAsString();
        this.placeinfo = jsonObject.get("placeinfo").getAsString();
        this.playtime = jsonObject.get("playtime").getAsString();
        this.program = jsonObject.get("program").getAsString();
        this.spendtimefestival = jsonObject.get("spendtimefestival").getAsString();
        this.sponsor1 = jsonObject.get("sponsor1").getAsString();
        this.sponsor1tel = jsonObject.get("sponsor1tel").getAsString();
        this.sponsor2 = jsonObject.get("sponsor2").getAsString();
        this.sponsor2tel = jsonObject.get("sponsor2tel").getAsString();
        this.subevent = jsonObject.get("subevent").getAsString();
        this.usetimefestival = jsonObject.get("usetimefestival").getAsString();
    }
}



