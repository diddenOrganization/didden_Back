package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.stream.Collectors;

@Getter
public class TourCommonV4ResponseDto {
    private Long contentId;
    private String address;
    private String cellphone;
    private String detailImage;
    private String thumbnailImage;
    private Integer serviceCode;
    private String highCode;
    private String middleCode;
    private String title;

    public TourCommonV4ResponseDto() {
    }

    @Builder
    public TourCommonV4ResponseDto(Long contentId, String address, String cellphone, String detailImage, String thumbnailImage, Integer serviceCode, String highCode, String middleCode, String title) {
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

    public TourCommonV4ResponseDto receiveByVo(TourCommonV4ResponseVo tourCommonV4ResponseVo) {
        return TourCommonV4ResponseDto.builder()
                .contentId(tourCommonV4ResponseVo.getContentId())
                .address(tourCommonV4ResponseVo.getAddress())
                .cellphone(tourCommonV4ResponseVo.getCellphone())
                .detailImage(tourCommonV4ResponseVo.getDetailImage())
                .thumbnailImage(tourCommonV4ResponseVo.getThumbnailImage())
                .serviceCode(tourCommonV4ResponseVo.getServiceCode())
                .highCode(tourCommonV4ResponseVo.getHighCode())
                .middleCode(tourCommonV4ResponseVo.getMiddleCode())
                .title(tourCommonV4ResponseVo.getTitle())
                .build();
    }

    public Slice<TourCommonV4ResponseDto> receiveSliceByVo(Slice<TourCommonV4ResponseVo> slice) {
        return new SliceImpl<>(slice.stream().map(this::receiveByVo).collect(Collectors.toList()), slice.getPageable(), slice.hasNext());
    }
}
