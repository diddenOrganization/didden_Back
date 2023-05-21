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

    @Schema(title = "유모차 대여 가능 여부")
    private String chkbabycarriageShopping;
    @Schema(title = "신용카드 가능 여부")
    private String chkcreditcardShopping;
    @Schema(title = "애완동물 동반 가능 여부")
    private String chkpetshopping;
    @Schema(title = "문화센터 바로가기")
    private String culturecenter;
    @Schema(title = "장서는 날")
    private String fairday;
    @Schema(title = "문의 및 안내")
    private String infocentershopping;
    @Schema(title = "개장일")
    private String opendateshopping;
    @Schema(title = "영업시간")
    private String opentime;
    @Schema(title = "주차시설")
    private String parkingshopping;
    @Schema(title = "쉬는 날")
    private String restdateshopping;
    @Schema(title = "화장실 설명")
    private String restroom;
    @Schema(title = "판매 품목")
    private String saleitem;
    @Schema(title = "판매 품목별 가격")
    private String saleitemcost;
    @Schema(title = "규모")
    private String scaleshopping;
    @Schema(title = "매장 안내")
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
