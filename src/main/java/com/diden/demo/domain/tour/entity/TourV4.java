package com.diden.demo.domain.tour.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@Table(name = "TB_TOUR_V4")
public class TourV4 {
    @Id
    private String contentId;
    private String title;
    private String address;
    private LocalDateTime openTime;
    private String cellphone;
    private String etcInfo;
    private String detailImage;
    private String thumbnailImage;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer serviceCode;
    private String highCode;
    private String middleCode;

    public TourV4() {
    }

    @Builder
    public TourV4(String contentId, String title, String address, LocalDateTime openTime, String cellphone, String etcInfo, String detailImage, String thumbnailImage, LocalDateTime checkIn, LocalDateTime checkOut, Integer serviceCode, String highCode, String middleCode) {
        this.contentId = contentId;
        this.title = title;
        this.address = address;
        this.openTime = openTime;
        this.cellphone = cellphone;
        this.etcInfo = etcInfo;
        this.detailImage = detailImage;
        this.thumbnailImage = thumbnailImage;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.serviceCode = serviceCode;
        this.highCode = highCode;
        this.middleCode = middleCode;
    }

    public static List<TourV4> init(JsonObject jsonObject) {
        final JsonArray asJsonArray = jsonObject.get("response").getAsJsonObject()
                .get("body").getAsJsonObject()
                .get("items").getAsJsonObject()
                .get("item").getAsJsonArray();

        final List<TourV4> tourV4List = new ArrayList<>();
        for(JsonElement obj : asJsonArray) {
            final JsonObject asJsonObject = obj.getAsJsonObject();
            tourV4List.add(new TourV4(
                    asJsonObject.get("contentid").getAsString(),
                    asJsonObject.get("title").getAsString(),
                    asJsonObject.get("addr1").getAsString(),
                    null,
                    asJsonObject.get("tel").getAsString(),
                    null,
                    asJsonObject.get("firstimage").getAsString(),
                    asJsonObject.get("firstimage2").getAsString(),
                    null,
                    null,
                    asJsonObject.get("contenttypeid").getAsInt(),
                    asJsonObject.get("cat1").getAsString(),
                    asJsonObject.get("cat2").getAsString()
            ));
        }

        return tourV4List;
    }

    public void setEtcInfo(JsonObject jsonObject) {
        final JsonArray asJsonArray = jsonObject.get("response").getAsJsonObject()
                .get("body").getAsJsonObject()
                .get("items").getAsJsonObject()
                .get("item").getAsJsonArray();

        for(JsonElement obj : asJsonArray) {
            final JsonObject asJsonObject = obj.getAsJsonObject();
            this.etcInfo = asJsonObject.get("overview").getAsString();
        }
    }
}
