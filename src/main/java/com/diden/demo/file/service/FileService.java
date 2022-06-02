package com.diden.demo.file.service;

import java.util.List;

import com.diden.demo.file.vo.FileVo;

@Deprecated
public interface FileService {
    public void fileInsert(FileVo fileVo);

    public FileVo fileRead(FileVo fileVo);

    public List<FileVo> fileList(FileVo fileVo);

}
