package com.diden.demo.domain.main.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainContentVoRequest {
    private String contentId;
    private String contentName;
    private String contentCreateDate;
    private String contentUpdateDate;
    private String contentExtension;
    private String contentImageUri;

    public MainContentVoRequest() {
    }

    @Builder
    public MainContentVoRequest(String contentId, String contentName, String contentCreateDate, String contentUpdateDate, String contentExtension, String contentImageUri) {
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentCreateDate = contentCreateDate;
        this.contentUpdateDate = contentUpdateDate;
        this.contentExtension = contentExtension;
        this.contentImageUri = contentImageUri;
    }
}
