package com.diden.file.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FileVo {
    private String fileId = null;
    private String fileName = null;
    private Long fileSize = 0L;
    private String fileCreateDate = null;
    private String fileUpdateDate = null;
    private byte[] fileContent;
    private String fileContent64;
    private String fileExtension;
    private String fileUrl;
}
