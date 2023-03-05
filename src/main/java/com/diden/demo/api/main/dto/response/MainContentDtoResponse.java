package com.diden.demo.api.main.dto.response;

import com.diden.demo.api.main.dto.request.MainContentDtoRequest;
import com.diden.demo.domain.main.vo.request.MainContentVoRequest;
import com.diden.demo.domain.main.vo.response.MainContentVo;
import lombok.Builder;

public class MainContentDtoResponse {
    private String contentId;
    private String contentName;
    private String contentCreateDate;
    private String contentUpdateDate;
    private String contentExtension;
    private String contentImageUri;

    public MainContentDtoResponse() {
    }

    @Builder
    public MainContentDtoResponse(String contentId, String contentName, String contentCreateDate, String contentUpdateDate, String contentExtension, String contentImageUri) {
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentCreateDate = contentCreateDate;
        this.contentUpdateDate = contentUpdateDate;
        this.contentExtension = contentExtension;
        this.contentImageUri = contentImageUri;
    }

    public static MainContentDtoResponse transportDataByMainContentVo(MainContentVo mainContentVo) {
        return MainContentDtoResponse.builder()
                .contentId(mainContentVo.getContentId())
                .contentName(mainContentVo.getContentName())
                .contentCreateDate(mainContentVo.getContentCreateDate())
                .contentUpdateDate(mainContentVo.getContentUpdateDate())
                .contentExtension(mainContentVo.getContentExtension())
                .contentImageUri(mainContentVo.getContentImageUri())
                .build();
    }
}
