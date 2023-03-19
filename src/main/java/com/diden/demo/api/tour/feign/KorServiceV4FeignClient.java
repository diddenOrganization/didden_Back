package com.diden.demo.api.tour.feign;

import com.diden.demo.api.tour.dto.request.ReceivedAreaBasedListV4DtoRequest;
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

}
