package com.diden.tour.controller;

import java.io.IOException;

import com.diden.tour.vo.TourApiVo;
import com.diden.utils.ParsingFromURL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourApiController {

    static String SERVICE_DEV_KEY = "96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D";

    @GetMapping(value = "/tour/api/info", produces = "application/json; charset=UTF-8")
    public String test() throws IOException {
        ParsingFromURL parsingJSONFromURL = new ParsingFromURL();
        return parsingJSONFromURL.getParsingURL(
                "https://api.odcloud.kr/api/15003416/v1/uddi:a635e6c7-82cf-4714-b002-c7cf4cb20121_201609071527"
                        + "?page=1"
                        + "&perPage=10"
                        + "&serviceKey=" + SERVICE_DEV_KEY);
    }

    @GetMapping(value = "/tour/api/info/image", produces = "application/json;application/xml; charset=UTF-8")
    public String tourImageApi() {
        String url = new String(
                "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&pageNo=1"
                        + "&numOfRows=10"
                        + "&MobileOS=ETC"
                        + "&MobileApp=AppTest"
                        + "&arrange=A");
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        return parsingFromURL.getParsingURL(url);
    }

    @PostMapping(value = "/tour/api/info/detail", produces = "application/json;application/xml; charset=UTF-8")
    public String korServiceDetailCommon(@RequestBody(required = false) TourApiVo tourApiVo) {
        String url = new String(
                "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=10"
                        + "&pageNo=1"
                        + "&MobileOS=ETC"
                        + "&MobileApp=App"
                        + "&contentId=" + tourApiVo.getContentId()
                        + "&contentTypeId=" + tourApiVo.getContentTypeId()
                        + "&defaultYN=Y"
                        + "&firstImageYN=Y"
                        + "&areacodeYN=Y"
                        + "&catcodeYN=Y"
                        + "&addrinfoYN=Y"
                        + "&mapinfoYN=Y"
                        + "&overviewYN=Y"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        return parsingFromURL.getParsingURL(url);
    }
}
