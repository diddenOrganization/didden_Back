package com.diden.demo.api.tour.controller;

import com.diden.demo.api.tour.dto.response.TourCommonV4ResponseDto;
import com.diden.demo.api.tour.service.TourApiService;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourSearchApiController {
  private final TourApiService tourApiService;

  @GetMapping("/list")
  public HttpResponse<List<TourCommonV4ResponseDto>> getTours(
      @Parameter(
              schema =
                  @Schema(title = "페이지", description = "1,2,3,4 ... 한 페이지를 뜻 합니다.", example = "0"))
          @RequestParam final Integer page,
      @Parameter(
              schema =
                  @Schema(title = "출력할 수", description = "한 페이지에 데이터가 N개 를 출력합니다.", example = "10"))
          @RequestParam final Integer size,
      @RequestParam(required = false) final List<ServiceContentTypeCode> serviceContentTypeCodes,
      @RequestParam(required = false) final List<ServiceHighCode> serviceHighCodes,
      @RequestParam(required = false) final List<ServiceMiddleCode> serviceMiddleCodes,
      @RequestParam(required = false) final String keyword
  ) {

    final Slice<TourCommonV4ResponseDto> tourCommonV4ResponseDtos =
        tourApiService.pageSlice(PageRequest.of(page, size), serviceContentTypeCodes, serviceHighCodes, serviceMiddleCodes, keyword);

    return HttpResponse.toSlicedResponse(
        HttpStatus.OK,
        "성공",
        tourCommonV4ResponseDtos.getContent(),
        tourCommonV4ResponseDtos.hasNext());
  }
}
