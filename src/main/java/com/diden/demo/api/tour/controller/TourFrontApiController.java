package com.diden.demo.api.tour.controller;

import com.diden.demo.api.tour.dto.response.*;
import com.diden.demo.api.tour.service.TourApiService;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.CommonCodeMapperValue;
import com.diden.demo.domain.tour.vo.response.HighCodeMapperValue;
import com.diden.demo.domain.tour.vo.response.MiddleCodeMapperValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "여행 데이터 조회 API", description = "여행 데이터 관련된 API 목록입니다.")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourFrontApiController {

  private final TourApiService tourApiService;

  @Operation(summary = "컨텐츠 타입 코드", description = "컨텐츠 타입 코드를 조회합니다.<br>"
          + "TOURISM_TYPE_A01 = 관광지<br>"
          + "TOURISM_TYPE_A02 = 관광지<br>"
          + "CULTURAL_FACILITIES_TYPE = 문화시설<br>"
          + "FESTIVAL_TYPE = 행사/공연/축제<br>"
          + "TRAVEL_TYPE = 여행코스<br>"
          + "LEPORTS_TYPE = 레포츠<br>"
          + "HOTEL_TYPE = 숙박<br>"
          + "SHOPPING_TYPE = 쇼핑<br>"
          + "FOOD_TYPE = 음식점<br>")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @GetMapping(value = "/content-type-code")
  public HttpResponse<List<CommonCodeMapperValue>> findTourContentTypeCode() {
    return HttpResponse.toResponse(
            HttpStatus.OK,
            "컨텐츠 타입 코드",
            Arrays.stream(ServiceContentTypeCode.values())
                    .map(CommonCodeMapperValue::new)
                    .collect(Collectors.toList()));
  }

  @Operation(summary = "대분류 조회", description = "대분로 항목을 조회합니다.<br>"
          + "NATURE = 자연<br>"
          + "TOURIST_LIBERAL_ARTS = 관광지_인문(문화/예술/역사)<br>"
          + "CULTURAL_LIBERAL_ARTS = 문화시설_인문(문화/예술/역사)<br>"
          + "EVENT_LIBERAL_ARTS = 행사_인문(문화/예술/역사)<br>"
          + "RECOMMENDED_COURSE = 추천코스<br>"
          + "LEPORTS = 레포츠<br>"
          + "ACCOMMODATION = 숙박<br>"
          + "SHOPPING = 쇼핑<br>"
          + "FOOD = 음식<br>")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @GetMapping(value = "/high-code")
  public HttpResponse<List<HighCodeMapperValue>> findTourHighCode() {
    return HttpResponse.toResponse(
        HttpStatus.OK,
        "대분류 조회",
        Arrays.stream(ServiceHighCode.values())
            .map(HighCodeMapperValue::new)
            .collect(Collectors.toList()));
  }

  @Operation(summary = "중분류 조회", description = "중분류 항목을 조회합니다.<br>"
          + "NATURAL_TOURIST = 자연관광지<br>"
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
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @GetMapping(value = "/middle-code")
  public HttpResponse<List<MiddleCodeMapperValue>> findTourMiddleCode() {
    return HttpResponse.toResponse(
        HttpStatus.OK,
        "중분류 조회",
        Arrays.stream(ServiceMiddleCode.values())
            .map(MiddleCodeMapperValue::new)
            .collect(Collectors.toList()));
  }



  @Operation(summary = "여행지 상세 조회", description = "컨텐츠 타입 코드와 컨텐츠 ID를 통해 상세 조회 합니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(oneOf = {
                  TourAccommodationV4Response.class,
                  TourEntitySupportResponseInterface.class,
                  TourEventV4Response.class,
                  TourRecreationV4Response.class,
                  TourRestaurantV4Response.class,
                  TourShoppingV4Response.class,
                  TourTourismV4Response.class,
                  TourTravelCourseV4Response.class
          })))
  })
  @GetMapping(value = "/{contentTypeCode}/content-type/{contentId}/details")
  public HttpResponse<TourEntitySupportResponseInterface> getTourContentDetail(
      @PathVariable(value = "contentTypeCode") final ServiceContentTypeCode contentTypeCode,
      @Parameter(description = "컨텐츠 ID", example = "2633896") @PathVariable(value = "contentId") final Long contentId) throws Throwable {

    return HttpResponse.toResponse(HttpStatus.OK, "여행 상세 정보 조회", tourApiService.tourDetail(contentTypeCode, contentId));
  }
}
