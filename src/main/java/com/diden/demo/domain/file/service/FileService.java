package com.diden.demo.domain.file.service;

import com.diden.demo.domain.file.vo.FileVo;

import java.util.List;

@Deprecated
public interface FileService {
  public void fileInsert(FileVo fileVo);

  public FileVo fileRead(FileVo fileVo);

  public List<FileVo> fileList(FileVo fileVo);
}
