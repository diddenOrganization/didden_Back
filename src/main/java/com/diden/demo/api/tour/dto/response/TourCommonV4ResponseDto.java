package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.stream.Collectors;

@Getter
@Schema(title = "여행 공통 응답 DTO")
public class TourCommonV4ResponseDto {
    @Schema(title = "컨텐츠 ID", description = "")
    private Long contentId;
    @Schema(title = "주소", description = "")
    private String address;
    @Schema(title = "전화번호", description = "")
    private String cellphone;
    @Schema(title = "상세 이미지", description = "")
    private String detailImage;
    @Schema(title = "썸네일 이미지", description = "")
    private String thumbnailImage;
    @Schema(title = "컨텐츠 타입 코드", description = "")
    private Integer serviceCode;
    @Schema(title = "대분류 코드", description = "")
    private String highCode;
    @Schema(title = "중분류 코드", description = "")
    private String middleCode;
    @Schema(title = "여행지 제목", description = "")
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
