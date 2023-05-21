package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourShoppingV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourShoppingV4Response - SHOPPING_TYPE", description = "SHOPPING_TYPE = 쇼핑")
public class TourShoppingV4Response implements TourEntitySupportResponseInterface{

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

    public TourShoppingV4Response() {
    }

    @Builder
    public TourShoppingV4Response(Long contentId, String chkbabycarriageShopping, String chkcreditcardShopping, String chkpetshopping, String culturecenter, String fairday, String infocentershopping, String opendateshopping, String opentime, String parkingshopping, String restdateshopping, String restroom, String saleitem, String saleitemcost, String scaleshopping, String shopguide) {
        this.contentId = contentId;
        this.chkbabycarriageShopping = chkbabycarriageShopping;
        this.chkcreditcardShopping = chkcreditcardShopping;
        this.chkpetshopping = chkpetshopping;
        this.culturecenter = culturecenter;
        this.fairday = fairday;
        this.infocentershopping = infocentershopping;
        this.opendateshopping = opendateshopping;
        this.opentime = opentime;
        this.parkingshopping = parkingshopping;
        this.restdateshopping = restdateshopping;
        this.restroom = restroom;
        this.saleitem = saleitem;
        this.saleitemcost = saleitemcost;
        this.scaleshopping = scaleshopping;
        this.shopguide = shopguide;
    }

    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourShoppingV4 tourShoppingV4 = (TourShoppingV4) entity;
        return TourShoppingV4Response.builder()
                .contentId(tourShoppingV4.getContentId())
                .chkbabycarriageShopping(tourShoppingV4.getChkbabycarriageShopping())
                .chkcreditcardShopping(tourShoppingV4.getChkcreditcardShopping())
                .chkpetshopping(tourShoppingV4.getChkpetshopping())
                .culturecenter(tourShoppingV4.getCulturecenter())
                .fairday(tourShoppingV4.getFairday())
                .infocentershopping(tourShoppingV4.getInfocentershopping())
                .opendateshopping(tourShoppingV4.getOpendateshopping())
                .opentime(tourShoppingV4.getOpentime())
                .parkingshopping(tourShoppingV4.getParkingshopping())
                .restdateshopping(tourShoppingV4.getRestdateshopping())
                .restroom(tourShoppingV4.getRestroom())
                .saleitem(tourShoppingV4.getSaleitem())
                .saleitemcost(tourShoppingV4.getSaleitemcost())
                .scaleshopping(tourShoppingV4.getScaleshopping())
                .shopguide(tourShoppingV4.getShopguide())
                .build();
    }
}
