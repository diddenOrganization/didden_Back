package com.diden.demo.anno;

import com.diden.demo.anno.mapper.AnnoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnoServiceImpl implements AnnoService {
    private final AnnoMapper annoMapper;

    @Autowired
    public AnnoServiceImpl(AnnoMapper annoMapper) {
        this.annoMapper = annoMapper;
    }

    public List<AnnoVo> findAll() {
        return annoMapper.findAll();
    }

    public AnnoVo findOne(AnnoVo annoVo) {
        return annoMapper.findOne(annoVo);
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
