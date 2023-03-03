package com.diden.demo.domain.anno.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnnoVoRequest {
    private String annoId;
    private String annoTitle;
    private String annoContent;
    private String annoCreateDate;

    public AnnoVoRequest() {
    }

    @Builder
    public AnnoVoRequest(String annoId, String annoTitle, String annoContent, String annoCreateDate) {
        this.annoId = annoId;
        this.annoTitle = annoTitle;
        this.annoContent = annoContent;
        this.annoCreateDate = annoCreateDate;
    }
}
