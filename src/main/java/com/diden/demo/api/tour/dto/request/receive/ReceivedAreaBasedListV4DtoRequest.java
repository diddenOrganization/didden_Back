package com.diden.demo.api.tour.dto.request.receive;

import com.diden.demo.api.tour.dto.request.CommonRequiredDtoRequest;
import com.diden.demo.domain.tour.enums.MobileOSType;
import com.diden.demo.domain.tour.enums.ServiceContentTypeCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceivedAreaBasedListV4DtoRequest extends CommonRequiredDtoRequest {
    private Integer contentTypeId;
    private Integer numOfRows;
    private Integer pageNo;

    public ReceivedAreaBasedListV4DtoRequest() {
    }

    @Builder
    public ReceivedAreaBasedListV4DtoRequest(ServiceContentTypeCode serviceContentTypeCode, MobileOSType mobileOSType, Integer numOfRows, Integer pageNo) {
        super(mobileOSType);
        this.contentTypeId = serviceContentTypeCode.getCode();
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;
    }

    public static ReceivedAreaBasedListV4DtoRequest init(final ServiceContentTypeCode serviceContentTypeCode, final MobileOSType mobileOSType, final Integer numOfRows, final Integer pageNo) {
        return ReceivedAreaBasedListV4DtoRequest.builder()
                .mobileOSType(mobileOSType)
                .serviceContentTypeCode(serviceContentTypeCode)
                .numOfRows(numOfRows)
                .pageNo(pageNo)
                .build();
    }


    public static ReceivedAreaBasedListV4DtoRequest init(final ServiceContentTypeCode serviceContentTypeCode, final Integer numOfRows, final Integer pageNo) {
        return init(serviceContentTypeCode, null, numOfRows, pageNo);
    }

}
