package com.diden.demo.tour.controller;

import com.diden.demo.TestStartConfig;
import com.diden.demo.common.utils.ParsingFromURL;
import com.diden.demo.domain.tour.mapper.TourMapper;
import com.diden.demo.domain.tour.vo.TourAreaCodeVo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TourInitCodeInsert extends TestStartConfig {
  @Autowired MockMvc mockMvc;
  @Autowired TourMapper tourMapper;
  @Autowired ResourceLoader resourceLoader;
  @Autowired ParsingFromURL parsingFromURL;



  @Test
  @Commit
  void sigunguCodeInsert() {
    List<TourAreaCodeVo> areaCodeList = tourMapper.findAreaCodeList();

    for (TourAreaCodeVo getData : areaCodeList) {
      String parsingURL =
          parsingFromURL.getParsingURL(
              "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&numOfRows=999&pageNo=1&MobileOS=ETC&MobileApp=mobileApp&areaCode="
                  + getData.getCode()
                  + "&_type=json");

      JsonObject obj = new Gson().fromJson(parsingURL, JsonObject.class);
      JsonArray asJsonArray =
          obj.getAsJsonObject("response")
              .getAsJsonObject("body")
              .getAsJsonObject("items")
              .getAsJsonArray("item");

      List<TourAreaCodeVo> listObj =
          new Gson().fromJson(asJsonArray, new TypeToken<ArrayList<TourAreaCodeVo>>() {}.getType());

      for (TourAreaCodeVo tourAreaCodeVo : listObj) {
        tourMapper.insertSigunguCode(
            Integer.parseInt(tourAreaCodeVo.getCode()),
            tourAreaCodeVo.getName(),
            Integer.parseInt(getData.getCode()));
      }
    }
  }

  @Test
  void pasingDataToJson() {
    String parsingURL =
        parsingFromURL.getParsingURL(
            "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&numOfRows=999&pageNo=1&MobileOS=ETC&MobileApp=mobileApp&areaCode=2&_type=json");

    JsonObject obj = new Gson().fromJson(parsingURL, JsonObject.class);
    JsonArray asJsonArray =
        obj.getAsJsonObject("response")
            .getAsJsonObject("body")
            .getAsJsonObject("items")
            .getAsJsonArray("item");

    List<TourAreaCodeVo> listObj =
        new Gson().fromJson(asJsonArray, new TypeToken<ArrayList<TourAreaCodeVo>>() {}.getType());

    System.out.println(listObj);
  }

  @Test
  void resourceJsonGetTest() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:/tour_area_data.json");
    String data = new String(resource.getInputStream().readAllBytes());
    JsonObject obj = new Gson().fromJson(data, JsonObject.class);
    JsonArray asJsonArray =
        obj.getAsJsonObject("response")
            .getAsJsonObject("body")
            .getAsJsonObject("items")
            .getAsJsonArray("item");

    List<SigunguDto> listObj =
        new Gson().fromJson(asJsonArray, new TypeToken<ArrayList<SigunguDto>>() {}.getType());
    System.out.println(listObj);
  }

  @Getter
  @Setter
  @ToString
  class SigunguDto {
    Integer rnum;
    String code;
    String name;
  }
}
