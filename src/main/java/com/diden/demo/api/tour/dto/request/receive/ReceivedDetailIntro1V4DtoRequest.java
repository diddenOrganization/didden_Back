package com.diden.demo.api.tour.dto.request.receive;

import com.diden.demo.api.tour.dto.request.CommonRequiredDtoRequest;
import com.diden.demo.domain.tour.enums.MobileOSType;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceivedDetailIntro1V4DtoRequest extends CommonRequiredDtoRequest {

    private Long contentId;
    private String contentTypeId;

    public ReceivedDetailIntro1V4DtoRequest() {
    }

    @Builder
    public ReceivedDetailIntro1V4DtoRequest(MobileOSType mobileOS, Long contentId, String contentTypeId) {
        super(mobileOS);
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
    }

    public static ReceivedDetailIntro1V4DtoRequest init(MobileOSType mobileOS, Long contentId, ServiceContentTypeCode serviceContentTypeCode) {
        return ReceivedDetailIntro1V4DtoRequest.builder()
                .mobileOS(mobileOS)
                .contentId(contentId)
                .contentTypeId(serviceContentTypeCode.getCodeTypeCasting())
                .build();
    }

    public static ReceivedDetailIntro1V4DtoRequest receive(MobileOSType mobileOS, Long contentId, ServiceContentTypeCode serviceContentTypeCode) {
        return init(mobileOS, contentId, serviceContentTypeCode);
    }


}
