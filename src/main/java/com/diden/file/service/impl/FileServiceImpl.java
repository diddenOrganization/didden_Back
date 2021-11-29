package com.diden.file.service.impl;

import java.util.List;

import com.diden.file.mapper.FileMapper;
import com.diden.file.service.FileService;
import com.diden.file.vo.FileVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void fileInsert(FileVo fileVo) {
        fileMapper.fileInsert(fileVo);
    }

    @Override
    public FileVo fileRead(FileVo fileVo) {
        return fileMapper.fileRead(fileVo);
    }

    @Override
    public List<FileVo> fileList(FileVo fileVo) {
        return fileMapper.fileList(fileVo);
    }

}
