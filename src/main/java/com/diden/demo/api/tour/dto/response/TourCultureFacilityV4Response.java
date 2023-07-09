package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourCultureFacilityV4Response - CULTURAL_FACILITIES_TYPE", description = "CULTURAL_FACILITIES_TYPE = 문화시설")
public class TourCultureFacilityV4Response implements TourEntitySupportResponseInterface {
    private Long contentId;

    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private ServiceContentTypeCode serviceCode;
    private ServiceHighCode highCode;
    private ServiceMiddleCode middleCode;
    private String title;

    @Schema(title = "수용인원")
    private String accomcountculture;
    @Schema(title = "유모차대여정보")
    private String chkbabycarriageculture;
    @Schema(title = "신용카드가능정보")
    private String chkcreditcardculture;
    @Schema(title = "애완동물동반가능정보")
    private String chkpetculture;
    @Schema(title = "할인정보")
    private String discountinfo;
    @Schema(title = "문의및안내")
    private String infocenterculture;
    @Schema(title = "주차시설")
    private String parkingculture;
    @Schema(title = "주차요금")
    private String parkingfee;
    @Schema(title = "쉬는날")
    private String restdateculture;
    @Schema(title = "이용요금")
    private String usefee;
    @Schema(title = "이용시간")
    private String usetimeculture;
    @Schema(title = "규모")
    private String scale;
    @Schema(title = "관람소요시간")
    private String spendtime;

    public TourCultureFacilityV4Response() {
    }

    @Builder
    public TourCultureFacilityV4Response(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, ServiceContentTypeCode serviceCode, ServiceHighCode highCode, ServiceMiddleCode middleCode, String title, String accomcountculture, String chkbabycarriageculture, String chkcreditcardculture, String chkpetculture, String discountinfo, String infocenterculture, String parkingculture, String parkingfee, String restdateculture, String usefee, String usetimeculture, String scale, String spendtime) {
        this.contentId = contentId;
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
        this.accomcountculture = accomcountculture;
        this.chkbabycarriageculture = chkbabycarriageculture;
        this.chkcreditcardculture = chkcreditcardculture;
        this.chkpetculture = chkpetculture;
        this.discountinfo = discountinfo;
        this.infocenterculture = infocenterculture;
        this.parkingculture = parkingculture;
        this.parkingfee = parkingfee;
        this.restdateculture = restdateculture;
        this.usefee = usefee;
        this.usetimeculture = usetimeculture;
        this.scale = scale;
        this.spendtime = spendtime;
    }


    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourCultureFacilityV4 tourCultureFacilityV4 = (TourCultureFacilityV4) entity;
        return TourCultureFacilityV4Response.builder()
                .contentId(tourCultureFacilityV4.getContentId())

                .address(tourCultureFacilityV4.getAddress())
                .cellphone(tourCultureFacilityV4.getCellphone())
                .detailImage(tourCultureFacilityV4.getDetailImage())
                .thumbnailImage(tourCultureFacilityV4.getThumbnailImage())
                .serviceCode(tourCultureFacilityV4.getServiceCode())
                .highCode(tourCultureFacilityV4.getHighCode())
                .middleCode(tourCultureFacilityV4.getMiddleCode())
                .title(tourCultureFacilityV4.getTitle())

                .accomcountculture(tourCultureFacilityV4.getAccomcountculture())
                .chkbabycarriageculture(tourCultureFacilityV4.getChkbabycarriageculture())
                .chkcreditcardculture(tourCultureFacilityV4.getChkcreditcardculture())
                .chkpetculture(tourCultureFacilityV4.getChkpetculture())
                .discountinfo(tourCultureFacilityV4.getDiscountinfo())
                .infocenterculture(tourCultureFacilityV4.getInfocenterculture())
                .parkingculture(tourCultureFacilityV4.getParkingculture())
                .parkingfee(tourCultureFacilityV4.getParkingfee())
                .restdateculture(tourCultureFacilityV4.getRestdateculture())
                .usefee(tourCultureFacilityV4.getUsefee())
                .usetimeculture(tourCultureFacilityV4.getUsetimeculture())
                .scale(tourCultureFacilityV4.getScale())
                .spendtime(tourCultureFacilityV4.getSpendtime())
                .build();
    }
}
