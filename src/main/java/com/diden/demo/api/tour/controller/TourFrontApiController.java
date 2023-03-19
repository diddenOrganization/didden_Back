package com.diden.demo.api.tour.controller;

import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import com.diden.demo.domain.tour.enums.ServiceHighCode;
import com.diden.demo.domain.tour.enums.ServiceMiddleCode;
import com.diden.demo.domain.tour.vo.response.CommonCodeMapperValue;
import com.diden.demo.domain.tour.vo.response.HighCodeMapperValue;
import com.diden.demo.domain.tour.vo.response.MiddleCodeMapperValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourFrontApiController {
    @GetMapping(value = "/high-code")
    public HttpResponse<List<HighCodeMapperValue>> findTourHighCode() {
        return HttpResponse.toResponse(
                HttpStatus.OK,
                "대분류 조회",
                Arrays.stream(ServiceHighCode.values())
                        .map(HighCodeMapperValue::new)
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/middle-code")
    public HttpResponse<List<MiddleCodeMapperValue>> findTourMiddleCode() {
        return HttpResponse.toResponse(
                HttpStatus.OK,
                "중분류 조회",
                Arrays.stream(ServiceMiddleCode.values())
                        .map(MiddleCodeMapperValue::new)
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/content-type-code")
    public HttpResponse<List<CommonCodeMapperValue>> findTourContentTypeCode() {
        return HttpResponse.toResponse(
                HttpStatus.OK,
                "컨텐츠 서비스 코드",
                Arrays.stream(ServiceContentTypeCode.values())
                        .map(CommonCodeMapperValue::new)
                        .collect(Collectors.toList()));
    }


}
