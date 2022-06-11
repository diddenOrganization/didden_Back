package com.diden.demo.tour.controller;

import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.vo.korservicevo.*;
import com.diden.demo.utils.ParsingFromURL;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TourApiController {

    private static final String SERVICE_DEV_KEY = "96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D";
    private static final String KOR_SERVICE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";

    @Autowired
    TourService tourService;
    /**
     * 상세정보조회
     *
     * @param "/tour/api/info/detailCommon"
     * @param contentId
     */
    @GetMapping(value = "/tour/api/info/detailCommon", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailCommon(@RequestParam String contentId){
        String tourDetailCommonUrl = KOR_SERVICE_URL + "detailCommon"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&contentId=" + contentId
                + "&defaultYN=Y&addrinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTesting";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println("URL => " + tourDetailCommonUrl);
        return parsingFromURL.getParsingURL(tourDetailCommonUrl);
    }

    /**
     * 키워드 검색 조회
     *
     * @param "/tour/api/info/searchKeyword"
     * @param cat1, cat2, cat3, keyword
     */
    @GetMapping(value = "/tour/api/info/searchKeyword", produces = "application/json;application/xml; charset=UTF-8")
    public List<Map<String, Object>> searchKeyword(@RequestParam String cat1, @RequestParam String cat2, @RequestParam String cat3, @RequestParam String keyword){
        Map<String, Object> tourInfoParam = new HashMap<>();
        tourInfoParam.put("cat1",cat1);
        tourInfoParam.put("cat2",cat2);
        tourInfoParam.put("cat3",cat3);
        tourInfoParam.put("keyword",keyword);

        return tourService.tourInfoList(tourInfoParam);
    }

    /**
     * 지역코드조회
     *
     * @param "/tour/api/info/areacode"
     * @param tourAreaCodeVo
     */
    @GetMapping(value = "/tour/api/info/areacode", produces = "application/json;application/xml; charset=UTF-8")
    public String tourAreaCode(@RequestBody(required = false) TourAreaCodeVo tourAreaCodeVo) {
        String tourAreaCodeUrl = KOR_SERVICE_URL + "areaCode"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&numOfRows=" + tourAreaCodeVo.getNumOfRows()
                + "&pageNo=" + tourAreaCodeVo.getPageNo()
                + "&MobileOS=" + tourAreaCodeVo.getMobileOS()
                + "&MobileApp=" + tourAreaCodeVo.getMobileApp()
                + "&areaCode=" + tourAreaCodeVo.getAreaCode()
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourAreaCodeUrl);
        return parsingFromURL.getParsingURL(tourAreaCodeUrl);
    }

    /**
     * 서비스 분류코드 조회
     *
     * @param "/tour/api/info/categorycode"
     * @param tourCategoryCodeVo
     */
    @GetMapping(value = "/tour/api/info/categorycode", produces = "application/json;application/xml; charset=UTF-8")
    public String tourCategoryCode(@RequestBody(required = false) TourCategoryCodeVo tourCategoryCodeVo) {
        String tourCategoryCodeUrl = KOR_SERVICE_URL + "categoryCode"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&numOfRows=" + tourCategoryCodeVo.getNumOfRows()
                + "&pageNo=" + tourCategoryCodeVo.getPageNo()
                + "&MobileOS=" + tourCategoryCodeVo.getMobileOS()
                + "&MobileApp=" + tourCategoryCodeVo.getMobileApp()
                + "&contentTypeId=" + tourCategoryCodeVo.getContentTypeId()
                + "&cat1=" + tourCategoryCodeVo.getCat1()
                + "&cat2=" + tourCategoryCodeVo.getCat2()
                + "&cat3=" + tourCategoryCodeVo.getCat3()
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourCategoryCodeUrl);
        return parsingFromURL.getParsingURL(tourCategoryCodeUrl);
    }

    /**
     * 지역기반 관광정보 조회
     *
     * @param "/tour/api/info/areabasedlist"
     * @param tourAreaBasedListVo
     */
    @GetMapping(value = "/tour/api/info/areabasedlist", produces = "application/json;application/xml; charset=UTF-8")
    public String tourAreaBasedList(@RequestBody(required = false) TourAreaBasedListVo tourAreaBasedListVo) {
        String tourAreaBasedListUrl = KOR_SERVICE_URL + "areaBasedList"
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
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourAreaBasedListUrl);
        return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
    }

    /**
     * 위치기반 관광정보 조회
     *
     * @param "/tour/api/info/locationbasedlist"
     * @param tourLocationBasedListVo
     */
    @GetMapping(value = "/tour/api/info/locationbasedlist", produces = "application/json;application/xml; charset=UTF-8")
    public String tourLocationBasedList(
            @RequestBody(required = false) TourLocationBasedListVo tourLocationBasedListVo) {
        String tourLocationBasedListUrl = KOR_SERVICE_URL + "locationBasedList"
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
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourLocationBasedListUrl);
        return parsingFromURL.getParsingURL(tourLocationBasedListUrl);
    }

    /**
     * 키워드 검색 조회
     *
     * @param "/tour/api/info/searchkeyword"
     * @param tourSearchKeywordVo
     */
    @SneakyThrows
    @GetMapping(value = "/tour/api/info/searchkeyword", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchKeyword(@RequestBody(required = false) TourSearchKeywordVo tourSearchKeywordVo) {
        String tourSearchKeywordUrl = KOR_SERVICE_URL + "searchKeyword"
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
                + "&keyword=" + URLEncoder.encode(tourSearchKeywordVo.getKeyword(), "UTF-8")
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourSearchKeywordUrl);
        return parsingFromURL.getParsingURL(tourSearchKeywordUrl);
    }

    /**
     * 행사정보 조회
     *
     * @param "/tour/api/info/searchfestival"
     * @param tourSearchFestivalVo
     */
    @GetMapping(value = "/tour/api/info/searchfestival", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchFestival(@RequestBody(required = false) TourSearchFestivalVo tourSearchFestivalVo) {
        String tourSearchFestivalUrl = KOR_SERVICE_URL + "searchFestival"
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
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourSearchFestivalUrl);
        return parsingFromURL.getParsingURL(tourSearchFestivalUrl);
    }

    /**
     * 숙박정보 조회
     *
     * @param "/tour/api/info/searchstay"
     * @param tourSearchStayVo
     */
    @GetMapping(value = "/tour/api/info/searchstay", produces = "application/json;application/xml; charset=UTF-8")
    public String tourSearchStay(@RequestBody(required = false) TourSearchStayVo tourSearchStayVo) {
        String tourSearchStayUrl = KOR_SERVICE_URL + "searchStay"
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
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourSearchStayUrl);
        return parsingFromURL.getParsingURL(tourSearchStayUrl);
    }

    /**
     * 공통정보 조회
     *
     * @param "/tour/api/info/detailcommon"
     * @param tourDetailCommonVo
     */
    @GetMapping(value = "/tour/api/info/detailcommon", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailCommon(@RequestBody(required = false) TourDetailCommonVo tourDetailCommonVo) {
        String tourDetailCommonUrl = KOR_SERVICE_URL + "detailCommon"
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
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourDetailCommonUrl);
        return parsingFromURL.getParsingURL(tourDetailCommonUrl);
    }

    /**
     * 소개정보 조회
     *
     * @param "/tour/api/info/detailintro"
     * @param tourDetailIntroVo
     */
    @GetMapping(value = "/tour/api/info/detailintro", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailIntro(@RequestBody(required = false) TourDetailIntroVo tourDetailIntroVo) {
        String tourDetailIntroUrl = KOR_SERVICE_URL + "detailIntro"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&numOfRows=" + tourDetailIntroVo.getNumOfRows()
                + "&pageNo=" + tourDetailIntroVo.getPageNo()
                + "&MobileOS=" + tourDetailIntroVo.getMobileOS()
                + "&MobileApp=" + tourDetailIntroVo.getMobileApp()
                + "&contentId=" + tourDetailIntroVo.getContentId()
                + "&contentTypeId=" + tourDetailIntroVo.getContentTypeId()
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourDetailIntroUrl);
        return parsingFromURL.getParsingURL(tourDetailIntroUrl);
    }

    /**
     * 반복정보 조회
     * @param tourDetailInfoVo
     * @return tourDetailInfoUrl json 형식
     */
    @GetMapping(value = "/tour/api/info/detailinfo", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailInfo(@RequestBody(required = false) TourDetailInfoVo tourDetailInfoVo) {
        String tourDetailInfoUrl = KOR_SERVICE_URL + "detailInfo"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&numOfRows=" + tourDetailInfoVo.getNumOfRows()
                + "&pageNo=" + tourDetailInfoVo.getPageNo()
                + "&MobileOS=" + tourDetailInfoVo.getMobileOS()
                + "&MobileApp=" + tourDetailInfoVo.getMobileApp()
                + "&contentId=" + tourDetailInfoVo.getContentId()
                + "&contentTypeId=" + tourDetailInfoVo.getContentTypeId()
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourDetailInfoUrl);
        return parsingFromURL.getParsingURL(tourDetailInfoUrl);
    }

    /**
     * 이미지정보 조회
     *
     * @param "/tour/api/info/detailimage"
     * @param tourDetailImageVo
     */
    @GetMapping(value = "/tour/api/info/detailimage", produces = "application/json;application/xml; charset=UTF-8")
    public String tourDetailImage(@RequestBody(required = false) TourDetailImageVo tourDetailImageVo) {
        String tourDetailImageUrl = KOR_SERVICE_URL + "detailImage"
                + "?serviceKey=" + SERVICE_DEV_KEY
                + "&numOfRows=" + tourDetailImageVo.getNumOfRows()
                + "&pageNo=" + tourDetailImageVo.getPageNo()
                + "&MobileOS=" + tourDetailImageVo.getMobileOS()
                + "&MobileApp=" + tourDetailImageVo.getMobileApp()
                + "&contentId=" + tourDetailImageVo.getContentId()
                + "&imageYN=" + tourDetailImageVo.getImageYN()
                + "&subImageYN=" + tourDetailImageVo.getSubImageYN()
                + "&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourDetailImageUrl);
        return parsingFromURL.getParsingURL(tourDetailImageUrl);
    }
}
