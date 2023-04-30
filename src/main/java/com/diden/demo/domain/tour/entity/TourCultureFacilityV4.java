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
@Table(name = "TB_TOUR_CULTURE_FACILITY_V4")
@AllArgsConstructor
public class TourCultureFacilityV4 implements TourEntitySupportInterface<TourCultureFacilityV4> {
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

    private String accomcountculture;
    private String chkbabycarriageculture;
    private String chkcreditcardculture;
    private String chkpetculture;
    private String discountinfo;
    private String infocenterculture;
    private String parkingculture;
    private String parkingfee;
    private String restdateculture;
    private String usefee;
    private String usetimeculture;
    private String scale;
    private String spendtime;

    public TourCultureFacilityV4() {
    }

    public TourCultureFacilityV4(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
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
        return ServiceContentTypeCode.CULTURAL_FACILITIES_TYPE.equals(contentType);
    }

    @Override
    public TourCultureFacilityV4 init(JsonObject jsonObject) {
        return new TourCultureFacilityV4(
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
        return ServiceContentTypeCode.CULTURAL_FACILITIES_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.accomcountculture = jsonObject.get("accomcountculture").getAsString();
        this.chkbabycarriageculture = jsonObject.get("chkbabycarriageculture").getAsString();
        this.chkcreditcardculture = jsonObject.get("chkcreditcardculture").getAsString();
        this.chkpetculture = jsonObject.get("chkpetculture").getAsString();
        this.discountinfo = jsonObject.get("discountinfo").getAsString();
        this.infocenterculture = jsonObject.get("infocenterculture").getAsString();
        this.parkingculture = jsonObject.get("parkingculture").getAsString();
        this.parkingfee = jsonObject.get("parkingfee").getAsString();
        this.restdateculture = jsonObject.get("restdateculture").getAsString();
        this.usefee = jsonObject.get("usefee").getAsString();
        this.usetimeculture = jsonObject.get("usetimeculture").getAsString();
        this.scale = jsonObject.get("scale").getAsString();
        this.spendtime = jsonObject.get("spendtime").getAsString();
    }
}
