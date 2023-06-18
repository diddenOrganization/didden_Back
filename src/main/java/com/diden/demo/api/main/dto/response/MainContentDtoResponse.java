package com.diden.demo.api.main.dto.response;

import com.diden.demo.api.main.dto.request.MainContentDtoRequest;
import com.diden.demo.domain.main.vo.request.MainContentVoRequest;
import com.diden.demo.domain.main.vo.response.MainContentVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MainContentDtoResponse {
    @Schema(title = "컨텐츠 ID", description = "")
    private String contentId;
    @Schema(title = "컨텐츠 이름", description = "")
    private String contentName;
    @Schema(title = "컨텐츠 생성일시", description = "")
    private String contentCreateDate;
    @Schema(title = "컨텐츠 수정일시", description = "")
    private String contentUpdateDate;
    @Schema(title = "컨텐츠 확장자", description = "")
    private String contentExtension;
    @Schema(title = "컨텐츠 이미지 URI", description = "")
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
