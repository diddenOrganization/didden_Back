package com.diden.demo.domain.file.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Deprecated
public class FileVo {
  private String fileId = null;
  private String fileName = null;
  private Long fileSize = 0L;
  private String fileCreateDate = null;
  private String fileUpdateDate = null;
  private byte[] fileContent;
  private String fileContent64 = null;
  private String fileExtension = null;
  private String fileUrl = null;
}
