package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourCultureFacilityV4Response - CULTURAL_FACILITIES_TYPE", description = "CULTURAL_FACILITIES_TYPE = 문화시설")
public class TourCultureFacilityV4Response implements TourEntitySupportResponseInterface {
    private Long contentId;
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

    public TourCultureFacilityV4Response() {
    }

    @Builder
    public TourCultureFacilityV4Response(Long contentId, String accomcountculture, String chkbabycarriageculture, String chkcreditcardculture, String chkpetculture, String discountinfo, String infocenterculture, String parkingculture, String parkingfee, String restdateculture, String usefee, String usetimeculture, String scale, String spendtime) {
        this.contentId = contentId;
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
