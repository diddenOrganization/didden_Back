package com.diden.demo.domain.tour.vo.response;

import com.diden.demo.domain.tour.entity.TourCommonEntityV4;
import com.diden.demo.domain.tour.entity.TourCultureFacilityV4;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
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

    private TourCommonV4ResponseVo selectInit(TourCommonEntityV4 tourCommonEntityV4) {
        return TourCommonV4ResponseVo.builder()
                .contentId(tourCommonEntityV4.getContentId())
                .address(tourCommonEntityV4.getAddress())
                .cellphone(tourCommonEntityV4.getCellphone())
                .detailImage(tourCommonEntityV4.getDetailImage())
                .thumbnailImage(tourCommonEntityV4.getThumbnailImage())
                .serviceCode(tourCommonEntityV4.getServiceCode())
                .highCode(tourCommonEntityV4.getHighCode())
                .middleCode(tourCommonEntityV4.getMiddleCode())
                .title(tourCommonEntityV4.getTitle())
                .build();
    }

    public <T extends TourCommonEntityV4> TourCommonV4ResponseVo commonInit(T obj) {
        return new TourCommonV4ResponseVo().selectInit(obj);
    }

    public Slice<TourCommonV4ResponseVo> selectSliceInit(Slice<TourCultureFacilityV4> slice) {
        return new SliceImpl<>(slice.stream().map(this::selectInit).collect(Collectors.toList()), slice.getPageable(), slice.hasNext());
    }

    public Slice<TourCommonV4ResponseVo> selectSliceInit(PageRequest pageRequest, Map<Long, TourCommonEntityV4> tourCommonEntityV4Map) {
        final List<TourCommonEntityV4> collectList = new ArrayList<>(tourCommonEntityV4Map.values());

        final int start = (int) pageRequest.getOffset();
        final int end = Math.min((start + pageRequest.getPageSize()), collectList.size());

        final List<TourCommonV4ResponseVo> byObject = collectList.stream().map(this::commonInit).collect(Collectors.toList());
        final Page<TourCommonV4ResponseVo> page = new PageImpl<>(byObject.subList(start, end), pageRequest, byObject.size());

        return new SliceImpl<>(page.getContent(), page.getPageable(), page.hasNext());
    }

    public Slice<TourCommonV4ResponseVo> selectSliceInit() {
        return new SliceImpl<>(new ArrayList<>());
    }
}
