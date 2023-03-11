package com.diden.demo.api.main.dto.request;

import com.diden.demo.domain.main.vo.request.MainContentVoRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainContentDtoRequest {
    private String contentId;
    private String contentName;
    private String contentCreateDate;
    private String contentUpdateDate;
    private String contentExtension;
    private String contentImageUri;

    public MainContentDtoRequest() {
    }

    @Builder
    public MainContentDtoRequest(String contentId, String contentName, String contentCreateDate, String contentUpdateDate, String contentExtension, String contentImageUri) {
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentCreateDate = contentCreateDate;
        this.contentUpdateDate = contentUpdateDate;
        this.contentExtension = contentExtension;
        this.contentImageUri = contentImageUri;
    }

    public static MainContentVoRequest transportDataByMainContentDtoRequest(MainContentDtoRequest mainContentDtoRequest) {
        return MainContentVoRequest.builder()
                .contentId(mainContentDtoRequest.getContentId())
                .contentName(mainContentDtoRequest.getContentName())
                .contentCreateDate(mainContentDtoRequest.getContentCreateDate())
                .contentUpdateDate(mainContentDtoRequest.getContentUpdateDate())
                .contentExtension(mainContentDtoRequest.getContentExtension())
                .contentImageUri(mainContentDtoRequest.getContentImageUri())
                .build();
    }
}
