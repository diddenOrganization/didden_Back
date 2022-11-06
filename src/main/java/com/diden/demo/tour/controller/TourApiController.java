package com.diden.demo.tour.controller;

import com.diden.demo.tour.MobileOSType;
import com.diden.demo.tour.ServiceHighCode;
import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.vo.korservicevo.*;
import com.diden.demo.utils.ParsingFromURL;
import com.diden.demo.utils.TourUriUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.diden.demo.tour.TourProperties.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TourApiController {
  private final TourService tourService;
  private final ParsingFromURL parsingFromURL;


  @GetMapping(value = "/tour/test")
  public String searchTourInfo(@RequestParam ServiceHighCode serviceHighCode) {

    // http://apis.data.go.kr/B551011/KorService/
    // areaBasedList?
    // numOfRows=12
    // pageNo=1
    // MobileOS=ETC
    // MobileApp=AppTest
    // ServiceKey=인증키
    // listYN=Y
    // arrange=A
    // contentTypeId=
    // areaCode=
    // sigunguCode=
    // cat1=A01
    // cat2=
    // cat3=

    String uri =
        "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&numOfRows=999&pageNo=1&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=&contentTypeId=&areaCode=1&sigunguCode=&cat1="
            + serviceHighCode.getCode()
            + "&cat2=&cat3=&modifiedtime=&_type=json";
    return parsingFromURL.getParsingURL(uri);
  }

  /**
   * 상세정보조회
   *
   * @param "/tour/api/info/detailCommon"
   * @param contentId
   */
  @GetMapping(value = "/tour/api/info/detailCommon")
  public String tourDetailCommon(@RequestParam String contentId) {
    String tourDetailCommonUrl =
        KOR_SERVICE_URL
            + DETAIL_COMMON
            + SERVICE_DEV_KEY
            + "&contentId="
            + contentId
            + "&defaultYN=Y&addrinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTesting";
    System.out.println("URL => " + tourDetailCommonUrl);
    return parsingFromURL.getParsingURL(tourDetailCommonUrl);
  }

  /**
   * 키워드 검색 조회
   *
   * @param "/tour/api/info/searchKeyword"
   * @param cat1, cat2, cat3, keyword
   */
  @GetMapping(value = "/tour/api/info/searchKeyword")
  public List<Map<String, Object>> searchKeyword(
      @RequestParam String cat1,
      @RequestParam String cat2,
      @RequestParam String cat3,
      @RequestParam String keyword) {
    Map<String, Object> tourInfoParam = new HashMap<>();
    tourInfoParam.put("cat1", cat1);
    tourInfoParam.put("cat2", cat2);
    tourInfoParam.put("cat3", cat3);
    tourInfoParam.put("keyword", keyword);

    return tourService.tourInfoList(tourInfoParam);
  }

  /**
   * 지역코드조회
   *
   * @param "/tour/api/info/areacode"
   * @param tourAreaCodeVo
   */
  @GetMapping(value = "/tour/api/info/areacode")
  public String tourAreaCode(
      @RequestParam final MobileOSType mobileOS, final String mobileApp, final Integer numOfRows) {

    StringBuilder areaResponse =
        new StringBuilder()
            .append(KOR_SERVICE_URL)
            .append(AREA_CODE)
            .append(SERVICE_DEV_KEY)
            .append(TourUriUtils.tourInitUri(mobileOS, mobileApp, numOfRows))
            .append("&_type=json");

    log.info(areaResponse.toString());
    return parsingFromURL.getParsingURL(areaResponse.toString());
  }

  /**
   * 서비스 분류코드 조회
   *
   * @param "/tour/api/info/categorycode"
   * @param tourCategoryCodeVo
   */
  @GetMapping(value = "/tour/api/info/categorycode")
  public String tourCategoryCode(
      @RequestBody(required = false) TourCategoryCodeVo tourCategoryCodeVo) {
    String tourCategoryCodeUrl =
        KOR_SERVICE_URL
            + CATEGORY_CODE
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourCategoryCodeVo.getNumOfRows()
            + "&pageNo="
            + tourCategoryCodeVo.getPageNo()
            + "&MobileOS="
            + tourCategoryCodeVo.getMobileOS()
            + "&MobileApp="
            + tourCategoryCodeVo.getMobileApp()
            + "&contentTypeId="
            + tourCategoryCodeVo.getContentTypeId()
            + "&cat1="
            + tourCategoryCodeVo.getCat1()
            + "&cat2="
            + tourCategoryCodeVo.getCat2()
            + "&cat3="
            + tourCategoryCodeVo.getCat3()
            + "&_type=json";
    log.info(tourCategoryCodeUrl);
    return parsingFromURL.getParsingURL(tourCategoryCodeUrl);
  }

  /**
   * 지역기반 관광정보 조회
   *
   * @param "/tour/api/info/areabasedlist"
   * @param tourAreaBasedListVo
   */
  @GetMapping(value = "/tour/api/info/areabasedlist")
  public String tourAreaBasedList(
      @RequestBody(required = false) TourAreaBasedListVo tourAreaBasedListVo) {
    String tourAreaBasedListUrl =
        KOR_SERVICE_URL
            + AREA_BASED_LIST
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourAreaBasedListVo.getNumOfRows()
            + "&pageNo="
            + tourAreaBasedListVo.getPageNo()
            + "&MobileOS="
            + tourAreaBasedListVo.getMobileOS()
            + "&MobileApp="
            + tourAreaBasedListVo.getMobileApp()
            + "&listYN="
            + tourAreaBasedListVo.getListYN()
            + "&arrange="
            + tourAreaBasedListVo.getArrange()
            + "&contentTypeId="
            + tourAreaBasedListVo.getContentTypeId()
            + "&areaCode="
            + tourAreaBasedListVo.getAreaCode()
            + "&sigunguCode="
            + tourAreaBasedListVo.getSigunguCode()
            + "&cat1="
            + tourAreaBasedListVo.getCat1()
            + "&cat2="
            + tourAreaBasedListVo.getCat2()
            + "&cat3="
            + tourAreaBasedListVo.getCat3()
            + "&modifiedtime="
            + tourAreaBasedListVo.getModifiedtime()
            + "&_type=json";
    log.info(tourAreaBasedListUrl);
    return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
  }

  /**
   * 위치기반 관광정보 조회
   *
   * @param "/tour/api/info/locationbasedlist"
   * @param tourLocationBasedListVo
   */
  @GetMapping(value = "/tour/api/info/locationbasedlist")
  public String tourLocationBasedList(
      @RequestBody(required = false) TourLocationBasedListVo tourLocationBasedListVo) {
    String tourLocationBasedListUrl =
        KOR_SERVICE_URL
            + LOCATION_BASED_LIST
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourLocationBasedListVo.getNumOfRows()
            + "&pageNo="
            + tourLocationBasedListVo.getPageNo()
            + "&MobileOS="
            + tourLocationBasedListVo.getMobileOS()
            + "&MobileApp="
            + tourLocationBasedListVo.getMobileApp()
            + "&listYN="
            + tourLocationBasedListVo.getListYN()
            + "&arrange="
            + tourLocationBasedListVo.getArrange()
            + "&contentTypeId="
            + tourLocationBasedListVo.getContentTypeId()
            + "&mapX="
            + tourLocationBasedListVo.getMapX()
            + "&mapY="
            + tourLocationBasedListVo.getMapY()
            + "&radius="
            + tourLocationBasedListVo.getRadius()
            + "&modifiedtime="
            + tourLocationBasedListVo.getModifiedtime()
            + "&_type=json";
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
  @GetMapping(value = "/tour/api/info/searchkeyword")
  public String tourSearchKeyword(
      @RequestBody(required = false) TourSearchKeywordVo tourSearchKeywordVo) {
    String tourSearchKeywordUrl =
        KOR_SERVICE_URL
            + SEARCH_KEYWORD
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourSearchKeywordVo.getNumOfRows()
            + "&pageNo="
            + tourSearchKeywordVo.getPageNo()
            + "&MobileOS="
            + tourSearchKeywordVo.getMobileOS()
            + "&MobileApp="
            + tourSearchKeywordVo.getMobileApp()
            + "&listYN="
            + tourSearchKeywordVo.getListYN()
            + "&arrange="
            + tourSearchKeywordVo.getArrange()
            + "&contentTypeId="
            + tourSearchKeywordVo.getContentTypeId()
            + "&areaCode="
            + tourSearchKeywordVo.getAreaCode()
            + "&sigunguCode="
            + tourSearchKeywordVo.getSigunguCode()
            + "&cat1="
            + tourSearchKeywordVo.getCat1()
            + "&cat2="
            + tourSearchKeywordVo.getCat2()
            + "&cat3="
            + tourSearchKeywordVo.getCat3()
            + "&keyword="
            + URLEncoder.encode(tourSearchKeywordVo.getKeyword(), "UTF-8")
            + "&_type=json";
    log.info(tourSearchKeywordUrl);
    return parsingFromURL.getParsingURL(tourSearchKeywordUrl);
  }

  /**
   * 행사정보 조회
   *
   * @param "/tour/api/info/searchfestival"
   * @param tourSearchFestivalVo
   */
  @GetMapping(value = "/tour/api/info/searchfestival")
  public String tourSearchFestival(
      @RequestBody(required = false) TourSearchFestivalVo tourSearchFestivalVo) {
    String tourSearchFestivalUrl =
        KOR_SERVICE_URL
            + SEARCH_FESTIVAL
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourSearchFestivalVo.getNumOfRows()
            + "&pageNo="
            + tourSearchFestivalVo.getPageNo()
            + "&MobileOS="
            + tourSearchFestivalVo.getMobileOS()
            + "&MobileApp="
            + tourSearchFestivalVo.getMobileApp()
            + "&listYN="
            + tourSearchFestivalVo.getListYN()
            + "&arrange="
            + tourSearchFestivalVo.getArrange()
            + "&areaCode="
            + tourSearchFestivalVo.getAreaCode()
            + "&sigunguCode="
            + tourSearchFestivalVo.getSigunguCode()
            + "&eventStartDate="
            + tourSearchFestivalVo.getEventStartDate()
            + "&eventEndDate="
            + tourSearchFestivalVo.getEventEndDate()
            + "&modifiedtime="
            + tourSearchFestivalVo.getModifiedtime()
            + "&_type=json";
    log.info(tourSearchFestivalUrl);
    return parsingFromURL.getParsingURL(tourSearchFestivalUrl);
  }

  /**
   * 숙박정보 조회
   *
   * @param "/tour/api/info/searchstay"
   * @param tourSearchStayVo
   */
  @GetMapping(value = "/tour/api/info/searchstay")
  public String tourSearchStay(@RequestBody(required = false) TourSearchStayVo tourSearchStayVo) {
    String tourSearchStayUrl =
        KOR_SERVICE_URL
            + SEARCH_STAY
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourSearchStayVo.getNumOfRows()
            + "&pageNo="
            + tourSearchStayVo.getPageNo()
            + "&MobileOS="
            + tourSearchStayVo.getMobileOS()
            + "&MobileApp="
            + tourSearchStayVo.getMobileApp()
            + "&listYN="
            + tourSearchStayVo.getListYN()
            + "&arrange="
            + tourSearchStayVo.getArrange()
            + "&areaCode="
            + tourSearchStayVo.getAreaCode()
            + "&sigunguCode="
            + tourSearchStayVo.getSigunguCode()
            + "&hanOk="
            + tourSearchStayVo.getHanOk()
            + "&benikia="
            + tourSearchStayVo.getBenikia()
            + "&goodStay="
            + tourSearchStayVo.getGoodStay()
            + "&modifiedtime="
            + tourSearchStayVo.getModifiedtime()
            + "&_type=json";
    log.info(tourSearchStayUrl);
    return parsingFromURL.getParsingURL(tourSearchStayUrl);
  }

  /**
   * 공통정보 조회
   *
   * @param "/tour/api/info/detailcommon"
   * @param tourDetailCommonVo
   */
  @GetMapping(value = "/tour/api/info/detailcommon")
  public String tourDetailCommon(
      @RequestBody(required = false) TourDetailCommonVo tourDetailCommonVo) {
    String tourDetailCommonUrl =
        KOR_SERVICE_URL
            + DETAIL_COMMON
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourDetailCommonVo.getNumOfRows()
            + "&pageNo="
            + tourDetailCommonVo.getPageNo()
            + "&MobileOS="
            + tourDetailCommonVo.getMobileOS()
            + "&MobileApp="
            + tourDetailCommonVo.getMobileApp()
            + "&contentId="
            + tourDetailCommonVo.getContentId()
            + "&contentTypeId="
            + tourDetailCommonVo.getContentTypeId()
            + "&defaultYN="
            + tourDetailCommonVo.getDefaultYN()
            + "&firstImageYN="
            + tourDetailCommonVo.getFirstImageYN()
            + "&areacodeYN="
            + tourDetailCommonVo.getAreacodeYN()
            + "&catcodeYN="
            + tourDetailCommonVo.getCatcodeYN()
            + "&addrinfoYN="
            + tourDetailCommonVo.getAddrinfoYN()
            + "&mapinfoYN="
            + tourDetailCommonVo.getMapinfoYN()
            + "&overviewYN="
            + tourDetailCommonVo.getOverviewYN()
            + "&_type=json";
    log.info(tourDetailCommonUrl);
    return parsingFromURL.getParsingURL(tourDetailCommonUrl);
  }

  /**
   * 소개정보 조회
   *
   * @param "/tour/api/info/detailintro"
   * @param tourDetailIntroVo
   */
  @GetMapping(value = "/tour/api/info/detailintro")
  public String tourDetailIntro(
      @RequestBody(required = false) TourDetailIntroVo tourDetailIntroVo) {
    String tourDetailIntroUrl =
        KOR_SERVICE_URL
            + DETAIL_INTRO
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourDetailIntroVo.getNumOfRows()
            + "&pageNo="
            + tourDetailIntroVo.getPageNo()
            + "&MobileOS="
            + tourDetailIntroVo.getMobileOS()
            + "&MobileApp="
            + tourDetailIntroVo.getMobileApp()
            + "&contentId="
            + tourDetailIntroVo.getContentId()
            + "&contentTypeId="
            + tourDetailIntroVo.getContentTypeId()
            + "&_type=json";
    log.info(tourDetailIntroUrl);
    return parsingFromURL.getParsingURL(tourDetailIntroUrl);
  }

  /**
   * 반복정보 조회
   *
   * @param tourDetailInfoVo
   * @return tourDetailInfoUrl json 형식
   */
  @GetMapping(value = "/tour/api/info/detailinfo")
  public String tourDetailInfo(@RequestBody(required = false) TourDetailInfoVo tourDetailInfoVo) {
    String tourDetailInfoUrl =
        KOR_SERVICE_URL
            + DETAIL_INFO
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourDetailInfoVo.getNumOfRows()
            + "&pageNo="
            + tourDetailInfoVo.getPageNo()
            + "&MobileOS="
            + tourDetailInfoVo.getMobileOS()
            + "&MobileApp="
            + tourDetailInfoVo.getMobileApp()
            + "&contentId="
            + tourDetailInfoVo.getContentId()
            + "&contentTypeId="
            + tourDetailInfoVo.getContentTypeId()
            + "&_type=json";
    log.info(tourDetailInfoUrl);
    return parsingFromURL.getParsingURL(tourDetailInfoUrl);
  }

  /**
   * 이미지정보 조회
   *
   * @param "/tour/api/info/detailimage"
   * @param tourDetailImageVo
   */
  @GetMapping(value = "/tour/api/info/detailimage")
  public String tourDetailImage(
      @RequestBody(required = false) TourDetailImageVo tourDetailImageVo) {
    String tourDetailImageUrl =
        KOR_SERVICE_URL
            + DETAIL_IMAGE
            + SERVICE_DEV_KEY
            + "&numOfRows="
            + tourDetailImageVo.getNumOfRows()
            + "&pageNo="
            + tourDetailImageVo.getPageNo()
            + "&MobileOS="
            + tourDetailImageVo.getMobileOS()
            + "&MobileApp="
            + tourDetailImageVo.getMobileApp()
            + "&contentId="
            + tourDetailImageVo.getContentId()
            + "&imageYN="
            + tourDetailImageVo.getImageYN()
            + "&subImageYN="
            + tourDetailImageVo.getSubImageYN()
            + "&_type=json";
    log.info(tourDetailImageUrl);
    return parsingFromURL.getParsingURL(tourDetailImageUrl);
  }
}
