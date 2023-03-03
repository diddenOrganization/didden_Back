package com.diden.demo.domain.tour.Scheduler.service;

import com.diden.demo.common.utils.ParsingFromURL;
import com.diden.demo.domain.tour.service.TourService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SchedulerService {

    private static final String SERVICE_DEV_KEY =
            "qkkQCeoqwr8qJxvWQVqBL4wOrWBMElcerMxOzagR3yb5AaKdIrlpH%2Fwa%2BRjCbUllM2oEBKYzI7hkmOd1J4Sg3Q%3D%3D";
    private static final String KOR_SERVICE_URL =
            "http://apis.data.go.kr/B551011/KorService/";

    @Autowired
    TourService tourService;

    //@Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void runTourInfoInsertScheduler(){
        log.info("tourInfoInsert Scheduler start => time : "+ LocalTime.now());

        String tourAreaBasedListUrl =
                KOR_SERVICE_URL
                        + "areaBasedList"
                        + "?serviceKey="
                        + SERVICE_DEV_KEY
                        + "&numOfRows=30&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=C&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourAreaBasedListUrl);
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode json = mapper.readTree(parsingFromURL.getParsingURL(tourAreaBasedListUrl));
            JsonNode tourInfo = json.get("response").get("body").get("items").get("item");
            Map<String, Object> map = new HashMap<>();
            if(tourInfo.size() > 0){
                log.info("tourInfoDelete start");
                tourService.tourInfoDelete();
            }
            for(int i=0; i < tourInfo.size();i++){
                map.put("addr1", tourInfo.get(i).get("addr1"));
                map.put("addr2", "테스트");
                map.put("areacode", tourInfo.get(i).get("areacode"));
                map.put("booktour", tourInfo.get(i).get("booktour"));
                map.put("cat1", tourInfo.get(i).get("cat1"));
                map.put("cat2", tourInfo.get(i).get("cat2"));
                map.put("cat3", tourInfo.get(i).get("cat3"));
                map.put("contentid", tourInfo.get(i).get("contentid"));
                map.put("contenttypeid", tourInfo.get(i).get("contenttypeid"));
                map.put("createdtime", tourInfo.get(i).get("createdtime"));
                map.put("firstimage", tourInfo.get(i).get("firstimage"));
                map.put("mapx", tourInfo.get(i).get("mapx"));
                map.put("mapy", tourInfo.get(i).get("mapy"));
                map.put("mlevel", tourInfo.get(i).get("mlevel"));
                map.put("modifiedtime", tourInfo.get(i).get("modifiedtime"));
                map.put("readcount", tourInfo.get(i).get("readcount"));
                map.put("sigungucode", tourInfo.get(i).get("sigungucode"));
                map.put("tel", "1234");
                map.put("title", tourInfo.get(i).get("title"));
                map.put("zipcode", tourInfo.get(i).get("zipcode"));

                log.info(" ============= jsonTest ("+i+") : "+map);
                tourService.tourInfoInsert(map);

                map.clear();
            }
        }catch (Exception c){
            c.printStackTrace();
        }
    }

    //@Scheduled(cron = "0 0 23 * * *", zone="Asia/Seoul")
    public void runTourInfoUpdateScheduler() {
        log.info("tourInfoInsert Scheduler start => time : "+ LocalTime.now());

        String tourAreaBasedListUrl =
                KOR_SERVICE_URL
                        + "areaBasedList"
                        + "?serviceKey="
                        + SERVICE_DEV_KEY
                        + "&numOfRows=200&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=C&_type=json";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        log.info(tourAreaBasedListUrl);
        ObjectMapper mapper = new ObjectMapper();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");

        LocalDate now = LocalDate.now();
        LocalDateTime currentDataTime = LocalDateTime.now();
        String NowDate = String.valueOf(currentDataTime.getYear())+String.valueOf(currentDataTime.getMonthValue())+String.valueOf(currentDataTime.getDayOfMonth());

        try{
            JsonNode json = mapper.readTree(parsingFromURL.getParsingURL(tourAreaBasedListUrl));
            JsonNode tourInfo = json.get("response").get("body").get("items").get("item");

            Map<String, Object> map = new HashMap<>();

            log.info("tourInfo = "+tourInfo);

            for(int i=0; i < tourInfo.size();i++){
                String Modifiedtime =  String.valueOf(tourInfo.get(i).get("modifiedtime")).substring(1,9);

                if(!Modifiedtime.equals("null")){
                    if(Modifiedtime.equals(NowDate)){
                    map.put("addr1", tourInfo.get(i).get("addr1"));
                    map.put("addr2", tourInfo.get(i).get("addr2"));
                    map.put("areacode", tourInfo.get(i).get("areacode"));
                    map.put("booktour", tourInfo.get(i).get("booktour"));
                    map.put("cat1", tourInfo.get(i).get("cat1"));
                    map.put("cat2", tourInfo.get(i).get("cat2"));
                    map.put("cat3", tourInfo.get(i).get("cat3"));
                    map.put("contentid", tourInfo.get(i).get("contentid"));
                    map.put("contenttypeid", tourInfo.get(i).get("contenttypeid"));
                    map.put("createdtime", tourInfo.get(i).get("createdtime"));
                    map.put("firstimage", tourInfo.get(i).get("firstimage"));
                    map.put("mapx", tourInfo.get(i).get("mapx"));
                    map.put("mapy", tourInfo.get(i).get("mapy"));
                    map.put("mlevel", tourInfo.get(i).get("mlevel"));
                    map.put("modifiedtime", tourInfo.get(i).get("modifiedtime"));
                    map.put("readcount", tourInfo.get(i).get("readcount"));
                    map.put("sigungucode", tourInfo.get(i).get("sigungucode"));
                    map.put("tel", tourInfo.get(i).get("tel"));
                    map.put("title", tourInfo.get(i).get("title"));
                    map.put("zipcode", tourInfo.get(i).get("zipcode"));
                    tourService.tourInfoUpdate(map);
                    map.clear();
                    }
                }
            }
        }catch (Exception c){
            c.printStackTrace();
        }
    }

}
