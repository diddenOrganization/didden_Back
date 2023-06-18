package com.diden.demo.api.tour.controller;

import com.diden.demo.api.tour.dto.response.TourCommonV4ResponseDto;
import com.diden.demo.api.tour.service.TourApiService;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "여행 검색 API", description = "여행 검색 관련된 API 목록입니다.")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourSearchApiController {
  private final TourApiService tourApiService;

  @Operation(summary = "여행지 검색", description = "여행 정보를 검색합니다. (서비스 컨텐츠 코드, 대분류, 중분류, 여행지 검색)")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "성공")
  })
  @GetMapping
  @PageableAsQueryParam
  public HttpResponse<List<TourCommonV4ResponseDto>> getTours_New(
          @Parameter(hidden = true) final Pageable pageable,
          @Parameter(description = "서비스 컨텐츠 타입 코드를 입력합니다. (구분자 ,)" , example = "TOURISM_TYPE_A01,CULTURAL_FACILITIES_TYPE,FESTIVAL_TYPE")              @RequestParam(required = false) final Optional<String> optServiceContentTypeCodes,
          @Parameter(description = "대분류 코드를 입력합니다. (구분자 ,)"          , example = "NATURE,TOURIST_LIBERAL_ARTS,CULTURAL_LIBERAL_ARTS,EVENT_LIBERAL_ARTS") @RequestParam(required = false) final Optional<String> optServiceHighCodes,
          @Parameter(description = "중분류 코드를 입력합니다. (구분자 ,)"          , example = "NATURAL_TOURIST,TOURISM_RESOURCES,CULTURAL_FACILITIES,FESTIVAL")       @RequestParam(required = false) final Optional<String> optServiceMiddleCodes,
          @Parameter(description = "여행지를 검색합니다. (여행지 제목)"            , example = "여행")                                                                  @RequestParam(required = false) final String keyword) {

    final List<String> arrayContentTypeCodeList = Arrays.asList(StringUtils.split(optServiceContentTypeCodes.orElseGet(() -> ""), ","));
    final List<String> arrayHighCodeList = Arrays.asList(StringUtils.split(optServiceHighCodes.orElseGet(() -> ""), ","));
    final List<String> arrayMiddleCodeList = Arrays.asList(StringUtils.split(optServiceMiddleCodes.orElseGet(() -> ""), ","));

    final List<ServiceContentTypeCode> serviceContentTypeCodeList = ServiceContentTypeCode.codeObjectsChangeByParameters(arrayContentTypeCodeList);
    final List<ServiceHighCode> serviceHighCodeList = ServiceHighCode.codeObjectsChangeByParameters(arrayHighCodeList);
    final List<ServiceMiddleCode> serviceMiddleCodeList = ServiceMiddleCode.codeObjectsChangeByParameters(arrayMiddleCodeList);

    final Slice<TourCommonV4ResponseDto> tourCommonV4ResponseDtos = tourApiService.pageSlice(pageable, serviceContentTypeCodeList, serviceHighCodeList, serviceMiddleCodeList, keyword);

    return HttpResponse.toSlicedResponse(
            HttpStatus.OK,
            "성공",
            tourCommonV4ResponseDtos.getContent(),
            tourCommonV4ResponseDtos.hasNext());
  }


}
