package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourRecreationV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourRecreationV4Response - LEPORTS_TYPE", description = "LEPORTS_TYPE = 레포츠")
public class TourRecreationV4Response implements TourEntitySupportResponseInterface {

    private Long contentId;
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

    public TourRecreationV4Response() {
    }

    @Builder
    public TourRecreationV4Response(Long contentId, String accomcountleports, String chkbabycarriageleports, String chkcreditcardleports, String chkpetleports, String expagerangeleports, String infocenterleports, String openperiod, String parkingfeeleports, String parkingleports, String reservation, String restdateleports, String scaleleports, String usefeeleports, String usetimeleports) {
        this.contentId = contentId;
        this.accomcountleports = accomcountleports;
        this.chkbabycarriageleports = chkbabycarriageleports;
        this.chkcreditcardleports = chkcreditcardleports;
        this.chkpetleports = chkpetleports;
        this.expagerangeleports = expagerangeleports;
        this.infocenterleports = infocenterleports;
        this.openperiod = openperiod;
        this.parkingfeeleports = parkingfeeleports;
        this.parkingleports = parkingleports;
        this.reservation = reservation;
        this.restdateleports = restdateleports;
        this.scaleleports = scaleleports;
        this.usefeeleports = usefeeleports;
        this.usetimeleports = usetimeleports;
    }

    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourRecreationV4 tourRecreationV4 = (TourRecreationV4) entity;
        return TourRecreationV4Response.builder()
                .contentId(tourRecreationV4.getContentId())
                .accomcountleports(tourRecreationV4.getAccomcountleports())
                .chkbabycarriageleports(tourRecreationV4.getChkbabycarriageleports())
                .chkcreditcardleports(tourRecreationV4.getChkcreditcardleports())
                .chkpetleports(tourRecreationV4.getChkpetleports())
                .expagerangeleports(tourRecreationV4.getExpagerangeleports())
                .infocenterleports(tourRecreationV4.getInfocenterleports())
                .openperiod(tourRecreationV4.getOpenperiod())
                .parkingfeeleports(tourRecreationV4.getParkingfeeleports())
                .parkingleports(tourRecreationV4.getParkingleports())
                .reservation(tourRecreationV4.getReservation())
                .restdateleports(tourRecreationV4.getRestdateleports())
                .scaleleports(tourRecreationV4.getScaleleports())
                .usefeeleports(tourRecreationV4.getUsefeeleports())
                .usetimeleports(tourRecreationV4.getUsetimeleports())
                .build();
    }
}
