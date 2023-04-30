package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.entity.TourAccommodationV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.stream.Collectors;

@Getter
@Setter
public class TourCommonV4ResponseVo {
    private Long contentId;
    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private Integer serviceCode;
    private String highCode;
    private String middleCode;
    private String title;

    public TourCommonV4ResponseVo() {
    }

    @Builder
    public TourCommonV4ResponseVo(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
        this.contentId = contentId;
        this.address = address;
        this.cellphone = cellphone;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
        this.title = title;
    }

    public TourCommonV4ResponseVo selectInit(TourCultureFacilityV4 tourCultureFacilityV4) {
        return TourCommonV4ResponseVo.builder()
                .contentId(tourCultureFacilityV4.getContentId())
                .address(tourCultureFacilityV4.getAddress())
                .cellphone(tourCultureFacilityV4.getCellphone())
                .detailImage(tourCultureFacilityV4.getDetailImage())
                .thumbnailImage(tourCultureFacilityV4.getThumbnailImage())
                .serviceCode(tourCultureFacilityV4.getServiceCode())
                .highCode(tourCultureFacilityV4.getHighCode())
                .middleCode(tourCultureFacilityV4.getMiddleCode())
                .title(tourCultureFacilityV4.getTitle())
                .build();
    }

    public Slice<TourCommonV4ResponseVo> selectSliceInit(Slice<TourCultureFacilityV4> slice) {
        return new SliceImpl<>(slice.stream().map(this::selectInit).collect(Collectors.toList()));
    }
}
