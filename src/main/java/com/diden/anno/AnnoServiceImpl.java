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
        try{
            if(annoVo.getAnnoTitle().isEmpty() || annoVo.getAnnoContent().isEmpty()) throw new NullPointerException("제목 또는 내용을 입력하지 않았습니다.");
            else annoMapper.save(annoVo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(AnnoVo annoVo) {
        try{
            if(annoVo.getAnnoTitle().isEmpty() || annoVo.getAnnoContent().isEmpty()) throw new NullPointerException("제목 또는 내용을 입력하지 않았습니다.");
            else annoMapper.update(annoVo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(AnnoVo annoVo) {
        try{
            if(annoVo.getAnnoId().isEmpty()) throw new NullPointerException("아이디 미 입력.");
            else annoMapper.delete(annoVo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
