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
@Table(name = "TB_TOUR_TOURISM_V4")
@AllArgsConstructor
public class TourTourismV4 extends TourCommonEntityV4 implements TourEntitySupportInterface<TourTourismV4> {
    @Id
    private Long contentId;

    private String accomcount;
    private String chkbabycarriage;
    private String chkcreditcard;
    private String chkpet;
    private String expagerange;
    private String expguide;
    private String heritage1;
    private String heritage2;
    private String heritage3;
    private String infocenter;
    private String opendate;
    private String parking;
    private String restdate;
    private String useseason;
    private String usetime;

    public TourTourismV4() {
    }

    public TourTourismV4(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.TOURISM_TYPE_A01.equals(contentType) || ServiceContentTypeCode.TOURISM_TYPE_A02.equals(contentType);
    }

    @Override
    public TourTourismV4 init(JsonObject jsonObject) {
        super.commonInit(jsonObject);
        return new TourTourismV4(jsonObject.get("contentid").getAsLong());
    }

    @Override
    public ServiceContentTypeCode getContentTypeCode() {
        if (ServiceContentTypeCode.TOURISM_TYPE_A01.getCode().equals(this.getServiceCode())) {
            return ServiceContentTypeCode.TOURISM_TYPE_A01;
        } else if (ServiceContentTypeCode.TOURISM_TYPE_A02.getCode().equals(this.getServiceCode())) {
            return ServiceContentTypeCode.TOURISM_TYPE_A02;
        }
        throw new RuntimeException("해당 서비스 코드가 아닙니다.");
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.accomcount = jsonObject.get("accomcount").getAsString();
        this.chkbabycarriage = jsonObject.get("chkbabycarriage").getAsString();
        this.chkcreditcard = jsonObject.get("chkcreditcard").getAsString();
        this.chkpet = jsonObject.get("chkpet").getAsString();
        this.expagerange = jsonObject.get("expagerange").getAsString();
        this.expguide = jsonObject.get("expguide").getAsString();
        this.heritage1 = jsonObject.get("heritage1").getAsString();
        this.heritage2 = jsonObject.get("heritage2").getAsString();
        this.heritage3 = jsonObject.get("heritage3").getAsString();
        this.infocenter = jsonObject.get("infocenter").getAsString();
        this.opendate = jsonObject.get("opendate").getAsString();
        this.parking = jsonObject.get("parking").getAsString();
        this.restdate = jsonObject.get("restdate").getAsString();
        this.useseason = jsonObject.get("useseason").getAsString();
        this.usetime = jsonObject.get("usetime").getAsString();
    }
}
