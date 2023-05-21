package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourTourismV4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourTourismV4Response - TOURISM_TYPE_A01, TOURISM_TYPE_A02", description = "TOURISM_TYPE_A01, TOURISM_TYPE_A02 = 관광지")
public class TourTourismV4Response implements TourEntitySupportResponseInterface {

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

    public TourTourismV4Response() {
    }

    @Builder
    public TourTourismV4Response(Long contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet, String expagerange, String expguide, String heritage1, String heritage2, String heritage3, String infocenter, String opendate, String parking, String restdate, String useseason, String usetime) {
        this.contentId = contentId;
        this.accomcount = accomcount;
        this.chkbabycarriage = chkbabycarriage;
        this.chkcreditcard = chkcreditcard;
        this.chkpet = chkpet;
        this.expagerange = expagerange;
        this.expguide = expguide;
        this.heritage1 = heritage1;
        this.heritage2 = heritage2;
        this.heritage3 = heritage3;
        this.infocenter = infocenter;
        this.opendate = opendate;
        this.parking = parking;
        this.restdate = restdate;
        this.useseason = useseason;
        this.usetime = usetime;
    }

    @Override
    public TourEntitySupportResponseInterface getTourResponse(TourEntitySupportInterface entity) {
        TourTourismV4 tourTourismV4 = (TourTourismV4) entity;
        return TourTourismV4Response.builder()
                .contentId(tourTourismV4.getContentId())
                .accomcount(tourTourismV4.getAccomcount())
                .chkbabycarriage(tourTourismV4.getChkbabycarriage())
                .chkcreditcard(tourTourismV4.getChkcreditcard())
                .chkpet(tourTourismV4.getChkpet())
                .expagerange(tourTourismV4.getExpagerange())
                .expguide(tourTourismV4.getExpguide())
                .heritage1(tourTourismV4.getHeritage1())
                .heritage2(tourTourismV4.getHeritage2())
                .heritage3(tourTourismV4.getHeritage3())
                .infocenter(tourTourismV4.getInfocenter())
                .opendate(tourTourismV4.getOpendate())
                .parking(tourTourismV4.getParking())
                .restdate(tourTourismV4.getRestdate())
                .useseason(tourTourismV4.getUseseason())
                .usetime(tourTourismV4.getUsetime())
                .build();
    }
}
