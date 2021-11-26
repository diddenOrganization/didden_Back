package com.diden.file.service.impl;

import java.io.File;
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
        // TODO Auto-generated method stub
        return fileMapper.fileRead(fileVo);
    }

    @Override
    public List<FileVo> fileList(FileVo fileVo) {
        // TODO Auto-generated method stub
        return fileMapper.fileList(fileVo);
    }

}
