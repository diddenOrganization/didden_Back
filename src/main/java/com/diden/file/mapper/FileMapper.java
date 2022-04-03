package com.diden.file.mapper;

import java.util.List;

import com.diden.file.vo.FileVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@Deprecated
public interface FileMapper {
    public void fileInsert(FileVo fileVo);

    public FileVo fileRead(FileVo fileVo);

    public List<FileVo> fileList(FileVo fileVo);
}
