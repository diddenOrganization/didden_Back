package com.diden.main;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data @Getter @Setter
public class MainContentVo {
    private String contentId = null;
    private String contentName = null;
    private Long contentSize = 0L;
    private String contentCreateDate = null;
    private String contentUpdateDate = null;
    private byte[] contentImageByte;
    private String contentContent64 = null;
    private String contentExtension = null;
    private String contentUrl = null;
    private transient LocalDateTime dataCreateTime = LocalDateTime.now();

    public MainContentVo() {
    }

    public MainContentVo(String contentId, String contentName, Long contentSize, String contentCreateDate, String contentUpdateDate, byte[] contentImageByte, String contentContent64, String contentExtension, String contentUrl) {
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentSize = contentSize;
        this.contentCreateDate = contentCreateDate;
        this.contentUpdateDate = contentUpdateDate;
        this.contentImageByte = contentImageByte;
        this.contentContent64 = contentContent64;
        this.contentExtension = contentExtension;
        this.contentUrl = contentUrl;
    }
}
