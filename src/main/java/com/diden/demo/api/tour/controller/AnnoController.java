package com.diden.demo.api.tour.controller;

import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.common.error.exception.DataNotProcessExceptions;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.anno.service.AnnoService;
import com.diden.demo.domain.anno.vo.response.AnnoVo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Deque;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/anno", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnoController {
  private final AnnoService annoService;
  private final Gson gson = LazyHolderObject.getGson();

  /**
   * 공지사항 전체목록
   *
   * @param findId
   * @param limit
   * @return
   */
  @GetMapping
  public HttpResponse<Deque<AnnoVo>> findAll(
      final Integer findId, @NotNull(message = "출력할 데이터 갯수가 존재하지 않습니다.") final Integer limit) {
    final Deque<AnnoVo> annoVoList = annoService.findAll(findId, limit);

    Integer nextSeq = null;
    if (annoVoList.size() > limit) {
      nextSeq = Integer.valueOf(annoVoList.pollLast().getAnnoId());
    }

    return HttpResponse.toPageResponse(
        HttpStatus.OK, "공지사항 목록을 호출합니다.", annoVoList, nextSeq);
  }

  /**
   * 공지사항 1건
   *
   * @param paramId path 공지사항 번호
   * @return HttpResponse.toResponse(HttpStatus.OK, "공지사항 1건 호출합니다.",
   *     annoService.findOne(newAnnoVo));
   */
  @GetMapping(value = "/{id}")
  public HttpResponse<AnnoVo> findOne(
      @PathVariable("id") @NotBlank(message = "공지사항 아이디가 존재하지 않습니다.") final String paramId) {
    if (!StringUtils.isNumeric(paramId)) {
      throw new BadRequestException("숫자만 입력할 수 있습니다.");
    }

    final AnnoVo newAnnoVo = AnnoVo.builder().annoId(paramId).build();
    final AnnoVo findAnno = annoService.findOne(newAnnoVo);

    if (findAnno == null) {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 존재하지 않습니다.", null);
    } else {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항 1건 호출합니다.", findAnno);
    }
  }

  /**
   * 공지사항 등록
   *
   * @param annoVo 제목, 내용
   * @return HttpResponse.toResponse(HttpStatus.CREATED, "공지사항이 생성되었습니다.");
   */
  @PostMapping
  public HttpResponse<Void> save(
      @RequestBody(required = false) @NotNull(message = "공지사항이 존재하지 않습니다.") @Valid
          final AnnoVo annoVo) {
    final AnnoVo newAnnoVo =
        AnnoVo.builder()
            .annoId(annoVo.getAnnoId())
            .annoTitle(annoVo.getAnnoTitle())
            .annoContent(annoVo.getAnnoContent())
            .build();

    if (annoService.save(newAnnoVo) > 0) {
      return HttpResponse.toResponse(HttpStatus.CREATED, "공지사항이 생성되었습니다.");
    } else {
      throw new DataNotProcessExceptions("공지사항이 저장되지 않았습니다.");
    }
  }

  /**
   * 공지사항 1건 수정
   *
   * @param annoVo 공지사항 제목, 내용
   * @param paramId path 공지사항 번호
   * @return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 수정되었습니다.",
   *     annoService.findOne(newAnnoVo));
   */
  @PatchMapping(value = "/{id}")
  public HttpResponse<AnnoVo> update(
      @RequestBody(required = false) @NotNull(message = "공지사항이 존재하지 않습니다.") @Valid
          final AnnoVo annoVo,
      @PathVariable(value = "id") @NotBlank(message = "공지사항 아이디가 존재하지 않습니다.")
          final String paramId) {
    if (!StringUtils.isNumeric(paramId)) {
      throw new BadRequestException("숫자만 입력할 수 있습니다.");
    }

    final AnnoVo newAnnoVo =
        AnnoVo.builder()
            .annoId(paramId)
            .annoTitle(annoVo.getAnnoTitle())
            .annoContent(annoVo.getAnnoContent())
            .build();

    if (annoService.update(newAnnoVo) > 0) {
      return HttpResponse.toResponse(
          HttpStatus.OK, "공지사항이 수정되었습니다.", annoService.findOne(newAnnoVo));
    } else {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 존재하지 않습니다.", null);
    }
  }

  /**
   * 공지사항 삭제
   *
   * @param paramId path 공지사항 번호
   * @return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 삭제되었습니다.");
   */
  @DeleteMapping(value = "/{id}")
  public HttpResponse<Void> delete(
      @PathVariable(value = "id") @NotBlank(message = "공지사항 아이디가 존재하지 않습니다.")
          final String paramId) {
    if (!StringUtils.isNumeric(paramId)) {
      throw new BadRequestException("숫자만 입력할 수 있습니다.");
    }

    if (annoService.delete(paramId) > 0) {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 삭제되었습니다.");
    } else {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 존재하지 않습니다.");
    }
  }
}
