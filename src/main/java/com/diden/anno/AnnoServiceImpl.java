package com.diden.anno;

import com.diden.anno.mapper.AnnoMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnnoServiceImpl implements AnnoService {
    @Autowired
    AnnoMapper annoMapper;

    public String findAll() {
        return new Gson().toJson(annoMapper.findAll());
    }

    public String findOne(AnnoVo annoVo) {
        return new Gson().toJson(annoMapper.findOne(annoVo));
    }

    public void save(AnnoVo annoVo) {
        annoMapper.save(annoVo);
    }

    public void update(AnnoVo annoVo) {
        annoMapper.update(annoVo);
    }

    public void delete(AnnoVo annoVo) {
        annoMapper.delete(annoVo);
    }
}
