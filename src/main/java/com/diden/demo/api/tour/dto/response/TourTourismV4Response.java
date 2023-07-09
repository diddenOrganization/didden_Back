package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.entity.TourEntitySupportInterface;
import com.diden.demo.domain.tour.entity.TourTourismV4;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "TourTourismV4Response - TOURISM_TYPE_A01, TOURISM_TYPE_A02", description = "TOURISM_TYPE_A01, TOURISM_TYPE_A02 = 관광지")
public class TourTourismV4Response implements TourEntitySupportResponseInterface {

    private Long contentId;

    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private ServiceContentTypeCode serviceCode;
    private ServiceHighCode highCode;
    private ServiceMiddleCode middleCode;
    private String title;
    @Schema(title = "수용 인원")
    private String accomcount;
    @Schema(title = "유모차 대여 정보")
    private String chkbabycarriage;
    @Schema(title = "신용카드 가능 정보")
    private String chkcreditcard;
    @Schema(title = "애완동물 동반 가능 정보")
    private String chkpet;
    @Schema(title = "체험 가능 연령")
    private String expagerange;
    @Schema(title = "체험 안내")
    private String expguide;
    @Schema(title = "세계 문화 유산 유무")
    private String heritage1;
    @Schema(title = "세계 자연 유산 유무")
    private String heritage2;
    @Schema(title = "세계 기록 유산 유무")
    private String heritage3;
    @Schema(title = "문의 및 안내")
    private String infocenter;
    @Schema(title = "개장일")
    private String opendate;
    @Schema(title = "주차 시설")
    private String parking;
    @Schema(title = "쉬는 날")
    private String restdate;
    @Schema(title = "이용 시기")
    private String useseason;
    @Schema(title = "이용 시간")
    private String usetime;

    public TourTourismV4Response() {
    }

    @Builder
    public TourTourismV4Response(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, ServiceContentTypeCode serviceCode, ServiceHighCode highCode, ServiceMiddleCode middleCode, String title, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet, String expagerange, String expguide, String heritage1, String heritage2, String heritage3, String infocenter, String opendate, String parking, String restdate, String useseason, String usetime) {
        this.contentId = contentId;
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
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

                .address(tourTourismV4.getAddress())
                .cellphone(tourTourismV4.getCellphone())
                .detailImage(tourTourismV4.getDetailImage())
                .thumbnailImage(tourTourismV4.getThumbnailImage())
                .serviceCode(tourTourismV4.getServiceCode())
                .highCode(tourTourismV4.getHighCode())
                .middleCode(tourTourismV4.getMiddleCode())
                .title(tourTourismV4.getTitle())

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
