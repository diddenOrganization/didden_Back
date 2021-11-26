package com.diden.file.service;

import java.util.List;

import com.diden.file.vo.FileVo;

public interface FileService {
    public void fileInsert(FileVo fileVo);

    public FileVo fileRead(FileVo fileVo);

    public List<FileVo> fileList(FileVo fileVo);

}
