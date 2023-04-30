package com.diden.demo.api.tour.feign;

import com.diden.demo.api.tour.dto.request.receive.ReceivedAreaBasedListV4DtoRequest;
import com.diden.demo.api.tour.dto.request.receive.ReceivedDetailCommonV4DtoRequest;
import com.diden.demo.api.tour.dto.request.receive.ReceivedDetailIntro1V4DtoRequest;
import com.diden.demo.common.config.properties.TourProperties;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "korServiceV4FeignClient", url = TourProperties.KOR_SERVICE_URL1)
public interface KorServiceV4FeignClient {

  @GetMapping(value = "/areaBasedList1", produces = MediaType.APPLICATION_JSON_VALUE)
  JsonObject receivesResponseFromAreaBasedListByContentTypeId(@SpringQueryMap ReceivedAreaBasedListV4DtoRequest receivedAreaBasedListV4DtoRequest);

  @GetMapping(value = "/detailCommon1", produces = MediaType.APPLICATION_JSON_VALUE)
  JsonObject receivesResponseFromDetailCommonByContentId(@SpringQueryMap ReceivedDetailCommonV4DtoRequest receivedDetailCommonV4DtoRequest);

  @GetMapping(value = "/detailIntro1", produces = MediaType.APPLICATION_JSON_VALUE)
  JsonObject receivesResponseFromDetailIntro1ByContentId(@SpringQueryMap ReceivedDetailIntro1V4DtoRequest detailIntro1V4DtoRequest);

}
