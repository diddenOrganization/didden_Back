package com.diden.demo.domain.anno.service;

import com.diden.demo.domain.anno.repository.AnnoMapper;
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

  public AnnoVo findOne(final AnnoVo annoVo) {
    return annoMapper.findOne(annoVo);
  }

  public int save(final AnnoVo annoVo) {
    return annoMapper.save(annoVo);
  }

  public int update(final AnnoVo annoVo) {
    return annoMapper.update(annoVo);
  }

  public int delete(final String annoId) {
    return annoMapper.delete(annoId);
  }
}
