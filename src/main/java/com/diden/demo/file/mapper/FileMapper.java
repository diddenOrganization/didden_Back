package com.diden.demo.file.mapper;

import java.util.List;

import com.diden.demo.file.vo.FileVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
@Deprecated
public interface FileMapper {
    public void fileInsert(FileVo fileVo);

    public FileVo fileRead(FileVo fileVo);

    public List<FileVo> fileList(FileVo fileVo);
}
