package com.diden.demo.domain.tour.entity;

import com.diden.demo.domain.tour.converter.ContentTypeCodeAttributeConverter;
import com.diden.demo.domain.tour.converter.HighCodeAttributeConverter;
import com.diden.demo.domain.tour.converter.MiddleCodeAttributeConverter;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.google.gson.JsonObject;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.Converter;
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
    @Convert(converter = ContentTypeCodeAttributeConverter.class)
    private ServiceContentTypeCode serviceCode;
    @Convert(converter = HighCodeAttributeConverter.class)
    private ServiceHighCode highCode;
    @Convert(converter = MiddleCodeAttributeConverter.class)
    private ServiceMiddleCode middleCode;
    private String title;

    public TourCommonEntityV4() {
    }

    public TourCommonEntityV4(String address, String cellphone, String detailImage, String thumbnailImage, ServiceContentTypeCode serviceCode, ServiceHighCode highCode, ServiceMiddleCode middleCode, String title) {
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
        this.serviceCode = ServiceContentTypeCode.codeToEnum(jsonObject.get("contenttypeid").getAsInt());
        this.highCode = ServiceHighCode.codeToEnum(jsonObject.get("cat1").getAsString());
        this.middleCode = ServiceMiddleCode.codeToEnum(jsonObject.get("cat2").getAsString());
        this.title = jsonObject.get("title").getAsString();
    }

    public abstract Long getContentId();
}
