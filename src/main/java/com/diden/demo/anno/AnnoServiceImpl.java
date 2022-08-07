package com.diden.demo.anno;

import com.diden.demo.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnoServiceImpl implements AnnoService {
  private final AnnoMapper annoMapper;

  public List<AnnoVo> findAll() {
    return annoMapper.findAll();
  }

  public AnnoVo findOne(AnnoVo annoVo) {
    Optional.ofNullable(annoVo)
        .map(AnnoVo::getAnnoId)
        .filter(v -> v.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("");
            });

    return annoMapper.findOne(annoVo);
  }

  public void save(AnnoVo annoVo) {
    Optional.ofNullable(annoVo)
        .map(AnnoVo::getAnnoTitle)
        .filter(s -> s.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("제목을 입력하지 않았습니다.");
            });

    Optional.ofNullable(annoVo)
        .map(AnnoVo::getAnnoContent)
        .filter(s -> s.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("내용을 입력하지 않았습니다.");
            });

    annoMapper.save(annoVo);
  }

  public void update(AnnoVo annoVo) {
    Optional.ofNullable(annoVo)
        .map(AnnoVo::getAnnoTitle)
        .filter(s -> s.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("제목을 입력하지 않았습니다.");
            });

    Optional.ofNullable(annoVo)
        .map(AnnoVo::getAnnoContent)
        .filter(s -> s.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("내용을 입력하지 않았습니다.");
            });

    annoMapper.update(annoVo);
  }

  public void delete(final String annoId) {
    Optional.ofNullable(annoId)
        .filter(v -> v.length() != 0)
        .orElseThrow(
            () -> {
              throw new BadRequestException("게시글 아이디가 존재하지 않습니다.");
            });

    annoMapper.delete(annoId);
  }
}
