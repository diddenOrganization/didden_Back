package com.diden.demo.api.tour.dto.response;

import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.TourCommonV4ResponseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Getter
@Schema(description = "여행 데이터를 담고 있는 DTO 입니다.")
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

  @Schema(
      title = "컨텐츠 타입 코드",
      description =
                "TOURISM_TYPE_A01 = 관광지<br>"
              + "TOURISM_TYPE_A02 = 관광지<br>"
              + "CULTURAL_FACILITIES_TYPE = 문화시설<br>"
              + "FESTIVAL_TYPE = 행사/공연/축제<br>"
              + "TRAVEL_TYPE = 여행코스<br>"
              + "LEPORTS_TYPE = 레포츠<br>"
              + "HOTEL_TYPE = 숙박<br>"
              + "SHOPPING_TYPE = 쇼핑<br>"
              + "FOOD_TYPE = 음식점<br>")
  private ServiceContentTypeCode serviceCode;

  @Schema(
      title = "대분류 코드",
      description =
                "NATURE = 자연<br>"
              + "TOURIST_LIBERAL_ARTS = 관광지_인문(문화/예술/역사)<br>"
              + "CULTURAL_LIBERAL_ARTS = 문화시설_인문(문화/예술/역사)<br>"
              + "EVENT_LIBERAL_ARTS = 행사_인문(문화/예술/역사)<br>"
              + "RECOMMENDED_COURSE = 추천코스<br>"
              + "LEPORTS = 레포츠<br>"
              + "ACCOMMODATION = 숙박<br>"
              + "SHOPPING = 쇼핑<br>"
              + "FOOD = 음식<br>"
  )
  private ServiceHighCode highCode;

  @Schema(
      title = "중분류 코드",
      description =
                "NATURAL_TOURIST = 자연관광지<br>"
              + "TOURISM_RESOURCES = 관광자원<br>"
              + "HISTORICAL_TOURIST = 역사관광지<br>"
              + "RECREATIONAL_RESORT = 휴양관광지<br>"
              + "EXPERIENCE_TOURIST = 체험관광지<br>"
              + "INDUSTRIAL_TOURIST = 산업관광지<br>"
              + "ARCHITECTURE_SCULPTURES = 건축/조형물<br>"
              + "CULTURAL_FACILITIES = 문화시설<br>"
              + "FESTIVAL = 축제<br>"
              + "PERFORMANCE_EVENT = 공연/행사<br>"
              + "FAMILY_COURSE = 가족코스<br>"
              + "SOLO_COURSE = 나홀로코스<br>"
              + "HEALING_COURSE = 힐링코스<br>"
              + "WALKING_COURSE = 도보코스<br>"
              + "CAMPING_COURSE = 캠핑코스<br>"
              + "TASTE_COURSE = 맛코스<br>"
              + "LEPORTS_COLLECTION = 레포츠소개<br>"
              + "ATHLETICS_LEPORTS = 육상 레포츠<br>"
              + "WATER_LEPORTS = 수상 레포츠<br>"
              + "AIR_SPORTS = 항공 레포츠<br>"
              + "COMBINED_LEPORTS = 복합 레포츠<br>"
              + "ACCOMMODATION = 숙박시설<br>"
              + "SHOPPING = 쇼핑<br>"
              + "FOOD = 음식점<br>")
  private ServiceMiddleCode middleCode;

  @Schema(title = "여행지 제목", description = "")
  private String title;

  public TourCommonV4ResponseDto() {}

  @Builder
  public TourCommonV4ResponseDto(
      Long contentId,
      String address,
      String cellphone,
      String detailImage,
      String thumbnailImage,
      ServiceContentTypeCode serviceCode,
      ServiceHighCode highCode,
      ServiceMiddleCode middleCode,
      String title) {
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
    return new SliceImpl<>(
        slice.stream().map(this::receiveByVo).collect(Collectors.toList()),
        slice.getPageable(),
        slice.hasNext());
  }
}
