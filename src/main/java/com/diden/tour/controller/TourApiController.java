package com.diden.tour.controller;

import java.io.IOException;

import com.diden.tour.vo.TourApiVo;
import com.diden.tour.vo.korservicevo.TourAreaBasedListVo;
import com.diden.tour.vo.korservicevo.TourAreaCodeVo;
import com.diden.tour.vo.korservicevo.TourCategoryCodeVo;
import com.diden.tour.vo.korservicevo.TourDetailCommonVo;
import com.diden.tour.vo.korservicevo.TourDetailImageVo;
import com.diden.tour.vo.korservicevo.TourDetailInfoVo;
import com.diden.tour.vo.korservicevo.TourDetailIntroVo;
import com.diden.tour.vo.korservicevo.TourLocationBasedListVo;
import com.diden.tour.vo.korservicevo.TourSearchFestivalVo;
import com.diden.tour.vo.korservicevo.TourSearchKeywordVo;
import com.diden.tour.vo.korservicevo.TourSearchStayVo;
import com.diden.utils.ParsingFromURL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourApiController {

    static String SERVICE_DEV_KEY = "96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D";
    static String KOR_SERVICE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";

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
                        + "&arrange=A"
                        + "&_type=json"

        );
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
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        return parsingFromURL.getParsingURL(url);
    }

    /**
     * 지역코드조회
     * 
     * @param "/tour/api/info/areacode"
     * @param tourAreaCodeVo
     */
    @PostMapping(value = "/tour/api/info/areacode", produces = "application/json;application/xml; charset=UTF-8")
    public String tourAreaCode(@RequestBody(required = false) TourAreaCodeVo tourAreaCodeVo) {
        String tourAreaCodeUrl = new String(
                KOR_SERVICE_URL + "areaCode"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourAreaCodeVo.getNumOfRows()
                        + "&pageNo=" + tourAreaCodeVo.getPageNo()
                        + "&MobileOS=" + tourAreaCodeVo.getMobileOS()
                        + "&MobileApp=" + tourAreaCodeVo.getMobileApp()
                        + "&areaCode=" + tourAreaCodeVo.getAreaCode()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourAreaCodeUrl);
        return parsingFromURL.getParsingURL(tourAreaCodeUrl);
    }

    /**
     * 서비스 분류코드 조회
     * 
     * @param "/tour/api/info/categorycode"
     * @param tourCategoryCodeVo
     */
    @PostMapping(value = "/tour/api/info/categorycode", produces = "application/json;application/xml; charset=UTF-8")
    public String tourCategoryCode(@RequestBody(required = false) TourCategoryCodeVo tourCategoryCodeVo) {
        String tourCategoryCodeUrl = new String(
                KOR_SERVICE_URL + "categoryCode"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourCategoryCodeVo.getNumOfRows()
                        + "&pageNo=" + tourCategoryCodeVo.getPageNo()
                        + "&MobileOS=" + tourCategoryCodeVo.getMobileOS()
                        + "&MobileApp=" + tourCategoryCodeVo.getMobileApp()
                        + "&contentTypeId=" + tourCategoryCodeVo.getContentTypeId()
                        + "&cat1=" + tourCategoryCodeVo.getCat1()
                        + "&cat2=" + tourCategoryCodeVo.getCat2()
                        + "&cat3=" + tourCategoryCodeVo.getCat3()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourCategoryCodeUrl);
        return parsingFromURL.getParsingURL(tourCategoryCodeUrl);
    }

    /**
     * 지역기반 관광정보 조회
     * 
     * @param "/tour/api/info/areabasedlist"
     * @param tourAreaBasedListVo
     */
    @PostMapping(value = "/tour/api/info/areabasedlist", produces = "application/json;application/xml; charset=UTF-8")
    public String tourAreaBasedList(@RequestBody(required = false) TourAreaBasedListVo tourAreaBasedListVo) {
        String tourAreaBasedListUrl = new String(
                KOR_SERVICE_URL + "areaBasedList"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourAreaBasedListVo.getNumOfRows()
                        + "&pageNo=" + tourAreaBasedListVo.getPageNo()
                        + "&MobileOS=" + tourAreaBasedListVo.getMobileOS()
                        + "&MobileApp=" + tourAreaBasedListVo.getMobileApp()
                        + "&listYN=" + tourAreaBasedListVo.getListYN()
                        + "&arrange=" + tourAreaBasedListVo.getArrange()
                        + "&contentTypeId=" + tourAreaBasedListVo.getContentTypeId()
                        + "&areaCode=" + tourAreaBasedListVo.getAreaCode()
                        + "&sigunguCode=" + tourAreaBasedListVo.getSigunguCode()
                        + "&cat1=" + tourAreaBasedListVo.getCat1()
                        + "&cat2=" + tourAreaBasedListVo.getCat2()
                        + "&cat3=" + tourAreaBasedListVo.getCat3()
                        + "&modifiedtime=" + tourAreaBasedListVo.getModifiedtime()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourAreaBasedListUrl);
        return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
    }

    /**
     * 위치기반 관광정보 조회
     * 
     * @param "/tour/api/info/locationbasedlist"
     * @param tourLocationBasedListVo
     */
    @PostMapping(value = "/tour/api/info/locationbasedlist", produces = "application/json;application/xml; charset=UTF-8")
    public String tourLocationBasedList(
            @RequestBody(required = false) TourLocationBasedListVo tourLocationBasedListVo) {
        String tourLocationBasedListUrl = new String(
                KOR_SERVICE_URL + "locationBasedList"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourLocationBasedListVo.getNumOfRows()
                        + "&pageNo=" + tourLocationBasedListVo.getPageNo()
                        + "&MobileOS=" + tourLocationBasedListVo.getMobileOS()
                        + "&MobileApp=" + tourLocationBasedListVo.getMobileApp()
                        + "&listYN=" + tourLocationBasedListVo.getListYN()
                        + "&arrange=" + tourLocationBasedListVo.getArrange()
                        + "&contentTypeId=" + tourLocationBasedListVo.getContentTypeId()
                        + "&mapX=" + tourLocationBasedListVo.getMapX()
                        + "&mapY=" + tourLocationBasedListVo.getMapY()
                        + "&radius=" + tourLocationBasedListVo.getRadius()
                        + "&modifiedtime=" + tourLocationBasedListVo.getModifiedtime()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourLocationBasedListUrl);
        return parsingFromURL.getParsingURL(tourLocationBasedListUrl);
    }

    /**
     * 키워드 검색 조회
     * 
     * @param "/tour/api/info/searchkeyword"
     * @param tourSearchKeywordVo
     */
    @PostMapping(value = "/tour/api/info/searchkeyword", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchKeyword(@RequestBody(required = false) TourSearchKeywordVo tourSearchKeywordVo) {
        String tourSearchKeywordUrl = new String(
                KOR_SERVICE_URL + "searchKeyword"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourSearchKeywordVo.getNumOfRows()
                        + "&pageNo=" + tourSearchKeywordVo.getPageNo()
                        + "&MobileOS=" + tourSearchKeywordVo.getMobileOS()
                        + "&MobileApp=" + tourSearchKeywordVo.getMobileApp()
                        + "&listYN=" + tourSearchKeywordVo.getListYN()
                        + "&arrange=" + tourSearchKeywordVo.getArrange()
                        + "&contentTypeId=" + tourSearchKeywordVo.getContentTypeId()
                        + "&areaCode=" + tourSearchKeywordVo.getAreaCode()
                        + "&sigunguCode=" + tourSearchKeywordVo.getSigunguCode()
                        + "&cat1=" + tourSearchKeywordVo.getCat1()
                        + "&cat2=" + tourSearchKeywordVo.getCat2()
                        + "&cat3=" + tourSearchKeywordVo.getCat3()
                        + "&keyword=" + tourSearchKeywordVo.getKeyword()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourSearchKeywordUrl);
        return parsingFromURL.getParsingURL(tourSearchKeywordUrl);
    }

    /**
     * 행사정보 조회
     * 
     * @param "/tour/api/info/searchfestival"
     * @param tourSearchFestivalVo
     */
    @PostMapping(value = "/tour/api/info/searchfestival", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchFestival(@RequestBody(required = false) TourSearchFestivalVo tourSearchFestivalVo) {
        String tourSearchFestivalUrl = new String(
                KOR_SERVICE_URL + "searchFestival"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourSearchFestivalVo.getNumOfRows()
                        + "&pageNo=" + tourSearchFestivalVo.getPageNo()
                        + "&MobileOS=" + tourSearchFestivalVo.getMobileOS()
                        + "&MobileApp=" + tourSearchFestivalVo.getMobileApp()
                        + "&listYN=" + tourSearchFestivalVo.getListYN()
                        + "&arrange=" + tourSearchFestivalVo.getArrange()
                        + "&areaCode=" + tourSearchFestivalVo.getAreaCode()
                        + "&sigunguCode=" + tourSearchFestivalVo.getSigunguCode()
                        + "&eventStartDate=" + tourSearchFestivalVo.getEventStartDate()
                        + "&eventEndDate=" + tourSearchFestivalVo.getEventEndDate()
                        + "&modifiedtime=" + tourSearchFestivalVo.getModifiedtime()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourSearchFestivalUrl);
        return parsingFromURL.getParsingURL(tourSearchFestivalUrl);
    }

    /**
     * 숙박정보 조회
     * 
     * @param "/tour/api/info/searchstay"
     * @param tourSearchStayVo
     */
    @PostMapping(value = "/tour/api/info/searchstay", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchStay(@RequestBody(required = false) TourSearchStayVo tourSearchStayVo) {
        String tourSearchStayUrl = new String(
                KOR_SERVICE_URL + "searchStay"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourSearchStayVo.getNumOfRows()
                        + "&pageNo=" + tourSearchStayVo.getPageNo()
                        + "&MobileOS=" + tourSearchStayVo.getMobileOS()
                        + "&MobileApp=" + tourSearchStayVo.getMobileApp()
                        + "&listYN=" + tourSearchStayVo.getListYN()
                        + "&arrange=" + tourSearchStayVo.getArrange()
                        + "&areaCode=" + tourSearchStayVo.getAreaCode()
                        + "&sigunguCode=" + tourSearchStayVo.getSigunguCode()
                        + "&hanOk=" + tourSearchStayVo.getHanOk()
                        + "&benikia=" + tourSearchStayVo.getBenikia()
                        + "&goodStay=" + tourSearchStayVo.getGoodStay()
                        + "&modifiedtime=" + tourSearchStayVo.getModifiedtime()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourSearchStayUrl);
        return parsingFromURL.getParsingURL(tourSearchStayUrl);
    }

    /**
     * 공통정보 조회
     * 
     * @param "/tour/api/info/detailcommon"
     * @param tourDetailCommonVo
     */
    @PostMapping(value = "/tour/api/info/detailcommon", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailCommon(@RequestBody(required = false) TourDetailCommonVo tourDetailCommonVo) {
        String tourDetailCommonUrl = new String(
                KOR_SERVICE_URL + "detailCommon"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourDetailCommonVo.getNumOfRows()
                        + "&pageNo=" + tourDetailCommonVo.getPageNo()
                        + "&MobileOS=" + tourDetailCommonVo.getMobileOS()
                        + "&MobileApp=" + tourDetailCommonVo.getMobileApp()
                        + "&contentId=" + tourDetailCommonVo.getContentId()
                        + "&contentTypeId=" + tourDetailCommonVo.getContentTypeId()
                        + "&defaultYN=" + tourDetailCommonVo.getDefaultYN()
                        + "&firstImageYN=" + tourDetailCommonVo.getFirstImageYN()
                        + "&areacodeYN=" + tourDetailCommonVo.getAreacodeYN()
                        + "&catcodeYN=" + tourDetailCommonVo.getCatcodeYN()
                        + "&addrinfoYN=" + tourDetailCommonVo.getAddrinfoYN()
                        + "&mapinfoYN=" + tourDetailCommonVo.getMapinfoYN()
                        + "&overviewYN=" + tourDetailCommonVo.getOverviewYN()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourDetailCommonUrl);
        return parsingFromURL.getParsingURL(tourDetailCommonUrl);
    }

    /**
     * 소개정보 조회
     * 
     * @param "/tour/api/info/detailintro"
     * @param tourDetailIntroVo
     */
    @PostMapping(value = "/tour/api/info/detailintro", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailIntro(@RequestBody(required = false) TourDetailIntroVo tourDetailIntroVo) {
        String tourDetailIntroUrl = new String(
                KOR_SERVICE_URL + "detailIntro"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourDetailIntroVo.getNumOfRows()
                        + "&pageNo=" + tourDetailIntroVo.getPageNo()
                        + "&MobileOS=" + tourDetailIntroVo.getMobileOS()
                        + "&MobileApp=" + tourDetailIntroVo.getMobileApp()
                        + "&contentId=" + tourDetailIntroVo.getContentId()
                        + "&contentTypeId=" + tourDetailIntroVo.getContentTypeId()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourDetailIntroUrl);
        return parsingFromURL.getParsingURL(tourDetailIntroUrl);
    }

    /**
     * 소개정보 조회
     * 
     * @param "/tour/api/info/detailinfo"
     * @param tourDetailInfoVo
     */
    @PostMapping(value = "/tour/api/info/detailinfo", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailInfo(@RequestBody(required = false) TourDetailInfoVo tourDetailInfoVo) {
        String tourDetailInfoUrl = new String(
                KOR_SERVICE_URL + "detailInfo"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourDetailInfoVo.getNumOfRows()
                        + "&pageNo=" + tourDetailInfoVo.getPageNo()
                        + "&MobileOS=" + tourDetailInfoVo.getMobileOS()
                        + "&MobileApp=" + tourDetailInfoVo.getMobileApp()
                        + "&contentId=" + tourDetailInfoVo.getContentId()
                        + "&contentTypeId=" + tourDetailInfoVo.getContentTypeId()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourDetailInfoUrl);
        return parsingFromURL.getParsingURL(tourDetailInfoUrl);
    }

    /**
     * 이미지정보 조회
     * 
     * @param "/tour/api/info/detailimage"
     * @param tourDetailImageVo
     */
    @PostMapping(value = "/tour/api/info/detailimage", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailImage(@RequestBody(required = false) TourDetailImageVo tourDetailImageVo) {
        String tourDetailImageUrl = new String(
                KOR_SERVICE_URL + "detailImage"
                        + "?serviceKey=" + SERVICE_DEV_KEY
                        + "&numOfRows=" + tourDetailImageVo.getNumOfRows()
                        + "&pageNo=" + tourDetailImageVo.getPageNo()
                        + "&MobileOS=" + tourDetailImageVo.getMobileOS()
                        + "&MobileApp=" + tourDetailImageVo.getMobileApp()
                        + "&contentId=" + tourDetailImageVo.getContentId()
                        + "&imageYN=" + tourDetailImageVo.getImageYN()
                        + "&subImageYN=" + tourDetailImageVo.getSubImageYN()
                        + "&_type=json"

        );
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourDetailImageUrl);
        return parsingFromURL.getParsingURL(tourDetailImageUrl);
    }
}
