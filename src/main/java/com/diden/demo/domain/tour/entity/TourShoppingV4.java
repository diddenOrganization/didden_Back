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
@Table(name = "TB_TOUR_SHOPPING_V4")
@AllArgsConstructor
public class TourShoppingV4 extends TourCommonEntityV4 implements TourEntitySupportInterface<TourShoppingV4> {
    @Id
    private Long contentId;

    private String chkbabycarriageShopping;
    private String chkcreditcardShopping;
    private String chkpetshopping;
    private String culturecenter;
    private String fairday;
    private String infocentershopping;
    private String opendateshopping;
    private String opentime;
    private String parkingshopping;
    private String restdateshopping;
    private String restroom;
    private String saleitem;
    private String saleitemcost;
    private String scaleshopping;
    private String shopguide;

    public TourShoppingV4() {
    }

    public TourShoppingV4(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean isContentTypeSupported(ServiceContentTypeCode contentType) {
        return ServiceContentTypeCode.SHOPPING_TYPE.equals(contentType);
    }

    @Override
    public TourShoppingV4 init(JsonObject jsonObject) {
        super.commonInit(jsonObject);
        return new TourShoppingV4(jsonObject.get("contentid").getAsLong());
    }

    @Override
    public ServiceContentTypeCode getContentTypeCode() {
        return ServiceContentTypeCode.SHOPPING_TYPE;
    }

    @Override
    public void settingValue(JsonObject jsonObject) {
        this.chkbabycarriageShopping = jsonObject.get("chkbabycarriageshopping").getAsString();
        this.chkcreditcardShopping = jsonObject.get("chkcreditcardshopping").getAsString();
        this.chkpetshopping = jsonObject.get("chkpetshopping").getAsString();
        this.culturecenter = jsonObject.get("culturecenter").getAsString();
        this.fairday = jsonObject.get("fairday").getAsString();
        this.infocentershopping = jsonObject.get("infocentershopping").getAsString();
        this.opendateshopping = jsonObject.get("opendateshopping").getAsString();
        this.opentime = jsonObject.get("opentime").getAsString();
        this.parkingshopping = jsonObject.get("parkingshopping").getAsString();
        this.restdateshopping = jsonObject.get("restdateshopping").getAsString();
        this.restroom = jsonObject.get("restroom").getAsString();
        this.saleitem = jsonObject.get("saleitem").getAsString();
        this.saleitemcost = jsonObject.get("saleitemcost").getAsString();
        this.scaleshopping = jsonObject.get("scaleshopping").getAsString();
        this.shopguide = jsonObject.get("shopguide").getAsString();
    }
}
