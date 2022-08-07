package com.diden.demo.main;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MainContentVo {
  private String contentId = null;
  private String contentName = null;
  private Long contentSize = 0L;
  private String contentCreateDate = null;
  private String contentUpdateDate = null;
  private transient byte[] contentImageByte;
  private String contentContent64 = null;
  private String contentExtension = null;
  private String contentUrl = null;
  private transient LocalDateTime dataCreateTime = LocalDateTime.now();
}
