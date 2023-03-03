package com.diden.demo.domain.file.mapper;

import com.diden.demo.domain.file.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Deprecated
public interface FileMapper {
  public void fileInsert(FileVo fileVo);

  public FileVo fileRead(FileVo fileVo);

  public List<FileVo> fileList(FileVo fileVo);
}
