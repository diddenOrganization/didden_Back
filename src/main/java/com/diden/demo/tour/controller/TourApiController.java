package com.diden.demo.tour.controller;

import com.diden.demo.tour.service.TourService;
import com.diden.demo.tour.vo.korservicevo.*;
import com.diden.demo.utils.ParsingFromURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class TourApiController {

    private static final String SERVICE_DEV_KEY =
        "qkkQCeoqwr8qJxvWQVqBL4wOrWBMElcerMxOzagR3yb5AaKdIrlpH%2Fwa%2BRjCbUllM2oEBKYzI7hkmOd1J4Sg3Q%3D%3D";
        //"96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D";
    private static final String KOR_SERVICE_URL =
          "http://apis.data.go.kr/B551011/KorService/";
    //    "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";


    @Autowired TourService tourService;

    /**
     * 지역기반 관광정보 조회
     *
     * @param "/tour/api/info/areabasedlist"
     * @param
     */
    @GetMapping(value = "/tour/api/info/areabasedlist")
    public String tourAreaBasedList() {
      String tourAreaBasedListUrl =
              KOR_SERVICE_URL
                      + "areaBasedList"
                      + "?serviceKey="
                      + SERVICE_DEV_KEY
                      + "&numOfRows=1000&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=C&_type=json";
      ParsingFromURL parsingFromURL = new ParsingFromURL();
      log.info(tourAreaBasedListUrl);
      ObjectMapper mapper = new ObjectMapper();

      try{
        JsonNode json = mapper.readTree(parsingFromURL.getParsingURL(tourAreaBasedListUrl));
        JsonNode jsonTest = json.get("response").get("body").get("items").get("item");
        Map<String, Object> map = new HashMap<>();
        if(jsonTest.size() > 0){
          tourService.tourInfoDelete();
        }
        for(int i=0; i < jsonTest.size();i++){
          map.put("addr1", jsonTest.get(i).get("addr1"));
          map.put("addr2", jsonTest.get(i).get("addr2"));
          map.put("areacode", jsonTest.get(i).get("areacode"));
          map.put("booktour", jsonTest.get(i).get("booktour"));
          map.put("cat1", jsonTest.get(i).get("cat1"));
          map.put("cat2", jsonTest.get(i).get("cat2"));
          map.put("cat3", jsonTest.get(i).get("cat3"));
          map.put("contentid", jsonTest.get(i).get("contentid"));
          map.put("contenttypeid", jsonTest.get(i).get("contenttypeid"));
          map.put("createdtime", jsonTest.get(i).get("createdtime"));
          map.put("firstimage", jsonTest.get(i).get("firstimage"));
          map.put("mapx", jsonTest.get(i).get("mapx"));
          map.put("mapy", jsonTest.get(i).get("mapy"));
          map.put("mlevel", jsonTest.get(i).get("mlevel"));
          map.put("modifiedtime", jsonTest.get(i).get("modifiedtime"));
          map.put("readcount", jsonTest.get(i).get("readcount"));
          map.put("sigungucode", jsonTest.get(i).get("sigungucode"));
          map.put("tel", jsonTest.get(i).get("tel"));
          map.put("title", jsonTest.get(i).get("title"));
          map.put("zipcode", jsonTest.get(i).get("zipcode"));

          //tourService.tourInfoInsert(map);

          log.info(" ============= jsonTest ("+i+") : "+map);
          map.clear();
        }
      }catch (Exception c){
        c.printStackTrace();
      }

      return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
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
              + "detailCommon"
              + "?serviceKey="
              + SERVICE_DEV_KEY
              + "&contentId="
              + contentId
              + "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest";
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
     * 지역코드조회
     *
     * @param "/tour/api/info/areacode"
     * @param tourAreaCodeVo
     */
    @GetMapping(value = "/tour/api/info/areacode")
    public String tourAreaCode(@RequestBody(required = false) TourAreaCodeVo tourAreaCodeVo) {
      String tourAreaCodeUrl =
          KOR_SERVICE_URL
              + "areaCode"
              + "?serviceKey="
              + SERVICE_DEV_KEY
              + "&numOfRows="
              + tourAreaCodeVo.getNumOfRows()
              + "&pageNo="
              + tourAreaCodeVo.getPageNo()
              + "&MobileOS="
              + tourAreaCodeVo.getMobileOS()
              + "&MobileApp="
              + tourAreaCodeVo.getMobileApp()
              + "&areaCode="
              + tourAreaCodeVo.getAreaCode()
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
    @GetMapping(value = "/tour/api/info/categorycode")
    public String tourCategoryCode(
        @RequestBody(required = false) TourCategoryCodeVo tourCategoryCodeVo) {
      String tourCategoryCodeUrl =
          KOR_SERVICE_URL
              + "categoryCode"
              + "?serviceKey="
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
    /*@GetMapping(value = "/tour/api/info/areabasedlist")
    public String tourAreaBasedList(
        @RequestBody(required = false) TourAreaBasedListVo tourAreaBasedListVo) {
      String tourAreaBasedListUrl =
          KOR_SERVICE_URL
              + "areaBasedList"
              + "?serviceKey="
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
      ParsingFromURL parsingFromURL = new ParsingFromURL();
      log.info(tourAreaBasedListUrl);
      return parsingFromURL.getParsingURL(tourAreaBasedListUrl);
    }*/

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
              + "locationBasedList"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/searchkeyword")
    public String tourSearchKeyword(
        @RequestBody(required = false) TourSearchKeywordVo tourSearchKeywordVo) {
      String tourSearchKeywordUrl =
          KOR_SERVICE_URL
              + "searchKeyword"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/searchfestival")
    public String tourSearchFestival(
        @RequestBody(required = false) TourSearchFestivalVo tourSearchFestivalVo) {
      String tourSearchFestivalUrl =
          KOR_SERVICE_URL
              + "searchFestival"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/searchstay")
    public String tourSearchStay(@RequestBody(required = false) TourSearchStayVo tourSearchStayVo) {
      String tourSearchStayUrl =
          KOR_SERVICE_URL
              + "searchStay"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/detailcommon")
    public String tourDetailCommon(
        @RequestBody(required = false) TourDetailCommonVo tourDetailCommonVo) {
      String tourDetailCommonUrl =
          KOR_SERVICE_URL
              + "detailCommon"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/detailintro")
    public String tourDetailIntro(
        @RequestBody(required = false) TourDetailIntroVo tourDetailIntroVo) {
      String tourDetailIntroUrl =
          KOR_SERVICE_URL
              + "detailIntro"
              + "?serviceKey="
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
      ParsingFromURL parsingFromURL = new ParsingFromURL();
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
              + "detailInfo"
              + "?serviceKey="
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
    @GetMapping(value = "/tour/api/info/detailimage")
    public String tourDetailImage(
        @RequestBody(required = false) TourDetailImageVo tourDetailImageVo) {
      String tourDetailImageUrl =
          KOR_SERVICE_URL
              + "detailImage"
              + "?serviceKey="
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
      ParsingFromURL parsingFromURL = new ParsingFromURL();
      log.info(tourDetailImageUrl);
      return parsingFromURL.getParsingURL(tourDetailImageUrl);
    }
}
