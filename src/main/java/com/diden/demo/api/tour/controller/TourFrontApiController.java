package com.diden.demo.api.tour.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourFrontApiController {


}
