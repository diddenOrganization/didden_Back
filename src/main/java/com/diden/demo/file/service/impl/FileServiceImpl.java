package com.diden.demo.file.service.impl;

import com.diden.demo.file.mapper.FileMapper;
import com.diden.demo.file.service.FileService;
import com.diden.demo.file.vo.FileVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Deprecated
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;
    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

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
