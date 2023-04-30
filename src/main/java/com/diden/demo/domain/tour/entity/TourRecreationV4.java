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
@Table(name = "TB_TOUR_RECREATION_V4")
@AllArgsConstructor
public class TourRecreationV4 implements TourEntitySupportInterface<TourRecreationV4> {
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

    private String accomcountleports;
    private String chkbabycarriageleports;
    private String chkcreditcardleports;
    private String chkpetleports;
    private String expagerangeleports;
    private String infocenterleports;
    private String openperiod;
    private String parkingfeeleports;
    private String parkingleports;
    private String reservation;
    private String restdateleports;
    private String scaleleports;
    private String usefeeleports;
    private String usetimeleports;

    public TourRecreationV4() {
    }

    public TourRecreationV4(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
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
        return ServiceContentTypeCode.LEPORTS_TYPE.equals(contentType);
    }

    @Override
    public TourRecreationV4 init(JsonObject jsonObject) {
        return new TourRecreationV4(
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
        return ServiceContentTypeCode.LEPORTS_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.accomcountleports = jsonObject.get("accomcountleports").getAsString();
        this.chkbabycarriageleports = jsonObject.get("chkbabycarriageleports").getAsString();
        this.chkcreditcardleports = jsonObject.get("chkcreditcardleports").getAsString();
        this.chkpetleports = jsonObject.get("chkpetleports").getAsString();
        this.expagerangeleports = jsonObject.get("expagerangeleports").getAsString();
        this.infocenterleports = jsonObject.get("infocenterleports").getAsString();
        this.openperiod = jsonObject.get("openperiod").getAsString();
        this.parkingfeeleports = jsonObject.get("parkingfeeleports").getAsString();
        this.parkingleports = jsonObject.get("parkingleports").getAsString();
        this.reservation = jsonObject.get("reservation").getAsString();
        this.restdateleports = jsonObject.get("restdateleports").getAsString();
        this.scaleleports = jsonObject.get("scaleleports").getAsString();
        this.usefeeleports = jsonObject.get("usefeeleports").getAsString();
        this.usetimeleports = jsonObject.get("usetimeleports").getAsString();
    }
}
