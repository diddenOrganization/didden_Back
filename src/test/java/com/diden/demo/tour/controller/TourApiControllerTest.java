package com.diden.demo.tour.controller;

import com.diden.demo.TestStartConfig;
import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.tour._dto.CommonTourResponseDto;
import com.diden.demo.domain.tour.definition.AreaCode;
import com.diden.demo.domain.tour.definition.ServiceContentTypeCode;
import com.diden.demo.domain.tour.mapper.TourMapper;
import com.diden.demo.domain.tour.service.TourService;
import com.diden.demo.domain.tour.service.impl.TourServiceImpl;
import com.diden.demo.domain.tour.vo.TourAreaInfoResponseDto;
import com.diden.demo.domain.tour.vo.TourSigunguCodeVo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
class TourApiControllerTest extends TestStartConfig {
  @Autowired MockMvc mockMvc;
  @Autowired TourMapper tourMapper;
  @Autowired ResourceLoader resourceLoader;
  @Autowired TourService tourService;
  @Autowired TourServiceImpl tourServiceImpl;
  Gson gson = LazyHolderObject.getGson();

  @Test
  void select_tour_data() {
    // given
    String cat1 = "A02";
    String cat2 = "A0206";
    String cat3 = "A02061200";

    Map<String, Object> tourInfoParam = new HashMap<>();
    tourInfoParam.put("cat1", cat1);
    tourInfoParam.put("cat2", cat2);
    //tourInfoParam.put("cat3", cat3);
    List<TourAreaInfoResponseDto> tourAreaInfoResponseDtos =
        tourServiceImpl.tourInfoList(tourInfoParam);

    Map<AreaCode, List<TourSigunguCodeVo>> areaCodeListMap = tourService.newSigunguCodeList();

    // when
    final List<TourAreaInfoResponseDto> collectResult = new ArrayList<>();

    for(TourAreaInfoResponseDto dto : tourAreaInfoResponseDtos) {
      dto.convertServiceTypeCodeByEnumTypeCodeAndTitleSetting(dto, dto.getContentTypeId(), dto.getCat1(), dto.getCat2());
      dto.convertServiceTypeCodeTitle(dto, dto.getServiceContentTypeCode(), dto.getServiceHighCode(), dto.getServiceMiddleCode());
      dto.convertSigunuAndAreaCodeByTitle(dto, dto.getAreaCode(), dto.getSigunuCode(), areaCodeListMap);
      collectResult.add(dto);
    }

    // then
    System.out.println("tourAreaInfoResponseDtos = " + gson.toJson(collectResult));
  }

  @Test
  void tourInfoListSelect() {

    ServiceContentTypeCode[] serviceContentTypeCodes = ServiceContentTypeCode.values();
    Map<ServiceContentTypeCode, List<CommonTourResponseDto>> tourDataList = new HashMap<>();
    for (ServiceContentTypeCode serviceContentTypeCode : serviceContentTypeCodes) {
      Integer code = serviceContentTypeCode.getCode();
      tourDataList.put(serviceContentTypeCode, tourMapper.newTourInfoListTest(code, null));
    }

    System.out.println(gson.toJson(tourDataList));
  }

  @Test
  void pageable_object() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/info/page/test").param("test", "sss"))
        .andDo(print());
  }

  @Test
  @Commit
  void bulk_insert() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:/converter_tour_data.json");
    String data = new String(resource.getInputStream().readAllBytes());
    List<CommonTourResponseDto> resultList =
        new Gson().fromJson(data, new TypeToken<List<CommonTourResponseDto>>() {}.getType());
    System.out.println(resultList);

    // List<CommonTourInfoVo> commonTourInfoVoList = new ArrayList<>();
    tourMapper.newTourInfoInsert(resultList);
  }

  @Test
  @Commit
  void jsonToMapConverter() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:/areabasedlist.json");
    String data = new String(resource.getInputStream().readAllBytes());
    JsonObject obj = new Gson().fromJson(data, JsonObject.class);
    JsonArray asJsonArray =
        obj.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item");

    List<Map<String, String>> converter =
        new Gson().fromJson(asJsonArray, new TypeToken<List<Map<String, String>>>() {}.getType());
    // System.out.println(converter.size());
    // System.out.println(converter);

    tourMapper.newTourInfoInsertTest(converter);
  }
}
