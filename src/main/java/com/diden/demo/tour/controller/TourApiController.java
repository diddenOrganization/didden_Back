package com.diden.demo.tour.controller;

import com.diden.demo.tour._dto.CommonTourResponseDto;
import com.diden.demo.tour._vo.CommonTourInfoVo;
import com.diden.demo.tour.definition.*;
import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.tour.vo.TourSigunguCodeVo;
import com.diden.demo.tour.vo.korservicevo.*;
import com.diden.demo.utils.HttpResponse;
import com.diden.demo.utils.ParsingFromURL;
import com.diden.demo.utils.TourUriUtils;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.diden.demo.tour.TourProperties.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tour/api/info", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourApiController {
  private final TourService tourService;
  private final ParsingFromURL parsingFromURL;

  /**
   * 상세정보조회
   *
   * @param "/tour/api/info/detailCommon"
   * @param contentId
   */
  @GetMapping(value = "/detailCommon")
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
      System.out.println("==================================== searchkeyword");
      Map<String, Object> tourInfoParam = new HashMap<>();
      tourInfoParam.put("cat1", cat1);
      tourInfoParam.put("cat2", cat2);
      tourInfoParam.put("cat3", cat3);
      tourInfoParam.put("keyword", keyword);

      List<Map<String, Object>> mList = tourService.tourInfoList(tourInfoParam);
      ObjectMapper mapper = new ObjectMapper();
      ParsingFromURL parsingFromURL = new ParsingFromURL();
      Map<String, Object> map = new HashMap<>();

      for(int i = 0; i < mList.size(); i++){
          log.info("mList.get("+i+") = "+mList.get(i));
          String overview = (String) mList.get(i).get("overview");
          String contentId = (String) mList.get(i).get("contentid");
          String areaCode = (String) mList.get(i).get("areacode");

          if(overview == null){
              map = new HashMap<>();
              JsonNode OverView;
              try {
                  String tourDetailCommonUrl =
                          KOR_SERVICE_URL
                                  + "detailCommon"
                                  + "?serviceKey="
                                  + SERVICE_DEV_KEY
                                  + "&contentId="
                                  + new ObjectMapper().writeValueAsString(mList.get(i).get("contentid")).replaceAll("\"","")
                                  + "&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest";

                  JsonNode OverViewjson = mapper.readTree(parsingFromURL.getParsingURL(tourDetailCommonUrl));
                  log.info("OverViewjson = "+OverViewjson);
                  OverView = OverViewjson.get("body").get("items").get("item").get("overview");
                  map.put("contentid", contentId);
                  map.put("overview", OverView);
                  tourService.tourInfoOverViewUpdate(map);

              } catch (JsonProcessingException e) {
                  e.printStackTrace();
              }
          }

          if(areaCode == null){
              map = new HashMap<>();
              JsonNode AreaName;
              try {
                  String tourAreaCodeUrl = KOR_SERVICE_URL
                          + "areaCode"
                          + "?serviceKey="
                          + SERVICE_DEV_KEY
                          + "&areaCode="
                          + new ObjectMapper().writeValueAsString(mList.get(i).get("areacode")).replaceAll("\"","")
                          + "&numOfRows=1&pageNo=1&MobileOS=ETC&MobileApp=AppTest";
                  JsonNode AreaNamejson = mapper.readTree(parsingFromURL.getParsingURL(tourAreaCodeUrl));

                      AreaName = AreaNamejson.get("body").get("items").get("item").get("name");

                      map.put("contentid", contentId);
                      map.put("areaname", AreaName);

                      tourService.tourInfoAreaNameUpdate(map);

              } catch (JsonProcessingException e) {
                  e.printStackTrace();
              }
          }
      }

        return tourService.tourInfoList(tourInfoParam);
    }

  /**
   * @param mobileOS
   * @param mobileApp
   * @param numOfRows
   * @return
   */
  @GetMapping(value = "/areacode")
  public String tourAreaCode(
      @NotNull(message = "모바일 타입이 존재하지 않습니다.") final MobileOSType mobileOS,
      @NotBlank(message = "모바일 앱이 존재하지 않습니다.") final String mobileApp,
      @NotNull(message = "출력할 갯수가 존재하지 않습니다.") final Integer numOfRows) {

    final StringBuilder sb =
        new StringBuilder()
            .append(KOR_SERVICE_URL)
            .append(AREA_CODE)
            .append(SERVICE_DEV_KEY)
            .append(TourUriUtils.tourInitUri(mobileOS, mobileApp, numOfRows))
            .append(TYPE_JSON);

    log.info(sb.toString());
    return parsingFromURL.getParsingURL(sb.toString());
  }

  /**
   * 서비스 분류코드 조회
   *
   * @param mobileOS
   * @param mobileApp
   * @param numOfRows
   * @param serviceContentTypeCode
   * @param serviceHighCode
   * @param serviceMiddleCode
   * @param serviceLowCode
   * @return
   */
  @GetMapping(value = "/categorycode")
  public String tourCategoryCode(
      final MobileOSType mobileOS,
      final String mobileApp,
      final Integer numOfRows,
      final ServiceContentTypeCode serviceContentTypeCode,
      final ServiceHighCode serviceHighCode,
      final ServiceMiddleCode serviceMiddleCode,
      final String serviceLowCode) {

    final StringBuilder sb =
        new StringBuilder()
            .append(KOR_SERVICE_URL)
            .append(CATEGORY_CODE)
            .append(SERVICE_DEV_KEY)
            .append(TourUriUtils.tourInitUri(mobileOS, mobileApp, numOfRows))
            .append(TourUriUtils.tourServiceContentTypeUri(serviceContentTypeCode))
            .append(
                TourUriUtils.tourServiceCategoryUri(
                    serviceHighCode, serviceMiddleCode, serviceLowCode))
            .append(TYPE_JSON);

    log.info(sb.toString());
    return parsingFromURL.getParsingURL(sb.toString());
  }

  @GetMapping(value = "/areabasedlist/re")
  public String reAreaBasedList(Integer numOfRows) {
    StringBuilder sb =
        new StringBuilder()
            .append(KOR_SERVICE_URL)
            .append(AREA_BASED_LIST)
            .append(SERVICE_DEV_KEY)
            .append(TourUriUtils.tourInitUri(MobileOSType.ETC, "AppTest", numOfRows));
    log.info(sb.toString());
    return parsingFromURL.getParsingURL(sb.toString());
  }

  /**
   * 지역기반 관광정보 조회
   *
   * @param "/areabasedlist"
   * @param tourAreaBasedListVo
   */
  @GetMapping(value = "/areabasedlist")
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
   * @param "/locationbasedlist"
   * @param tourLocationBasedListVo
   */
  @GetMapping(value = "/locationbasedlist")
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
   * @param "/searchkeyword"
   * @param tourSearchKeywordVo
   */
  @SneakyThrows
  @GetMapping(value = "/searchkeyword")
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
   * @param "/searchfestival"
   * @param tourSearchFestivalVo
   */
  @GetMapping(value = "/searchfestival")
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
   * @param "/searchstay"
   * @param tourSearchStayVo
   */
  @GetMapping(value = "/searchstay")
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
   * @param "/detailcommon"
   * @param tourDetailCommonVo
   */
  @GetMapping(value = "/detailcommon")
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
   * @param "/detailintro"
   * @param tourDetailIntroVo
   */
  @GetMapping(value = "/detailintro")
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
  @GetMapping(value = "/detailinfo")
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
   * @param "/detailimage"
   * @param tourDetailImageVo
   */
  @GetMapping(value = "/detailimage")
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

  @GetMapping(value = "/area")
  public HttpResponse<List<CommonCodeMapperValue>> findTourAreaInfo() {
    return HttpResponse.toResponse(
        HttpStatus.OK,
        "지역코드 조회",
        Arrays.stream(AreaCode.values())
            .map(CommonCodeMapperValue::new)
            .collect(Collectors.toList()));
  }

  @GetMapping(value = "/sigungu")
  public HttpResponse<Map<AreaCode, List<TourSigunguCodeVo>>> sigunguInfo() {
    return HttpResponse.toResponse(HttpStatus.OK, "성공", tourService.newSigunguCodeList());
  }

  @GetMapping(value = "/{areaCode}/sigungu")
  public HttpResponse<List<TourSigunguCodeVo>> findTourSigunguInfo(
      @PathVariable(value = "areaCode") @NotNull(message = "지역코드가 존재하지 않습니다.")
          final AreaCode areaCode) {
    return HttpResponse.toResponse(
        HttpStatus.OK, "시군구 조회", tourService.tourSigunguCodeList(areaCode));
  }

  @GetMapping(value = "/content-type-id")
  public HttpResponse<Map<ServiceContentTypeCode, List<CommonTourResponseDto>>> tourInfo() {
    return HttpResponse.toResponse(HttpStatus.OK, "성공", tourService.newTourInfoListTest());
  }
}
