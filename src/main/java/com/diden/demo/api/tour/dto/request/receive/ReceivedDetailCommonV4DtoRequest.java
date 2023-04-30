package com.diden.demo.api.tour.dto.request.receive;

import com.diden.demo.api.tour.dto.request.CommonRequiredDtoRequest;
import com.diden.demo.domain.tour.enums.MobileOSType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceivedDetailCommonV4DtoRequest extends CommonRequiredDtoRequest {

    private String contentId;
    private String defaultYN;
    private String firstImageYN;
    private String areacodeYN;
    private String addrinfoYN;
    private String mapinfoYN;
    private String overviewYN;
    private String catcodeYN;

    public ReceivedDetailCommonV4DtoRequest() {
    }

    @Builder
    public ReceivedDetailCommonV4DtoRequest(MobileOSType mobileOS, String contentId, String defaultYN, String firstImageYN, String areacodeYN, String addrinfoYN, String mapinfoYN, String overviewYN, String catcodeYN) {
        super(mobileOS);
        this.contentId = contentId;
        this.defaultYN = defaultYN;
        this.firstImageYN = firstImageYN;
        this.areacodeYN = areacodeYN;
        this.addrinfoYN = addrinfoYN;
        this.mapinfoYN = mapinfoYN;
        this.overviewYN = overviewYN;
        this.catcodeYN = catcodeYN;
    }

    public static ReceivedDetailCommonV4DtoRequest init(MobileOSType mobileOS, String contentId, String defaultYN, String firstImageYN, String areacodeYN, String addrinfoYN, String mapinfoYN, String overviewYN, String catcodeYN) {
        return ReceivedDetailCommonV4DtoRequest.builder()
                .mobileOS(mobileOS)
                .contentId(contentId)
                .defaultYN(defaultYN)
                .firstImageYN(firstImageYN)
                .areacodeYN(areacodeYN)
                .addrinfoYN(addrinfoYN)
                .mapinfoYN(mapinfoYN)
                .overviewYN(overviewYN)
                .catcodeYN(catcodeYN)
                .build();
    }

    public static ReceivedDetailCommonV4DtoRequest init(MobileOSType mobileOS, String contentId) {
        return init(mobileOS, contentId, "Y", "Y", "Y", "Y", "Y", "Y", "Y");
    }
}
