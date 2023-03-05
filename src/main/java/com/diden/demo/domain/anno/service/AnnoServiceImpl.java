package com.diden.demo.domain.anno.service;

import com.diden.demo.domain.anno.repository.AnnoMapper;
import com.diden.demo.domain.anno.vo.request.AnnoVoRequest;
import com.diden.demo.domain.anno.vo.response.AnnoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Deque;

@Service
@RequiredArgsConstructor
public class AnnoServiceImpl implements AnnoService {
  private final AnnoMapper annoMapper;

  @Override
  public Deque<AnnoVo> findAll(Integer findId, Integer limit) {
    return annoMapper.findAll(findId, limit);
  }

  public AnnoVo findOne(final AnnoVoRequest annoVoRequest) {
    return annoMapper.findOne(annoVoRequest);
  }

  public int save(final AnnoVoRequest annoVoRequest) {
    return annoMapper.save(annoVoRequest);
  }

  public int update(final AnnoVoRequest annoVoRequest) {
    return annoMapper.update(annoVoRequest);
  }

  public int delete(final String annoId) {
    return annoMapper.delete(annoId);
  }
}
