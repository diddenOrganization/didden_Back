package com.diden.tour.controller;

import java.io.IOException;

import com.diden.utils.ParsingFromURL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourApiController {

    @GetMapping(value = "/tour/api/info", produces = "application/json; charset=UTF-8")
    public String test() throws IOException {
        ParsingFromURL parsingJSONFromURL = new ParsingFromURL();
        return parsingJSONFromURL.getParsingURL(
                "https://api.odcloud.kr/api/15003416/v1/uddi:a635e6c7-82cf-4714-b002-c7cf4cb20121_201609071527?page=1&perPage=10&serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D");
    }

    @GetMapping(value = "/tour/api/info/image", produces = "application/json;application/xml; charset=UTF-8")
    public String tourImageApi() {
        String url = new String(
                "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A");
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        return parsingFromURL.getParsingURL(url);
    }

}
