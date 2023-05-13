package com.diden.demo.domain.tour.entity;

import com.google.gson.JsonObject;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class TourCommonEntityV4 {
    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private Integer serviceCode;
    private String highCode;
    private String middleCode;
    private String title;

    public TourCommonEntityV4() {
    }

    public TourCommonEntityV4(String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
    }

    public void commonInit(JsonObject jsonObject) {
        this.address = jsonObject.get("addr1").getAsString();
        this.cellphone =  jsonObject.get("tel").getAsString();
        this.detailImage = jsonObject.get("firstimage").getAsString();
        this.thumbnailImage = jsonObject.get("firstimage2").getAsString();
        this.serviceCode = jsonObject.get("contenttypeid").getAsInt();
        this.highCode = jsonObject.get("cat1").getAsString();
        this.middleCode = jsonObject.get("cat2").getAsString();
        this.title = jsonObject.get("title").getAsString();
    }

    public abstract Long getContentId();
}
