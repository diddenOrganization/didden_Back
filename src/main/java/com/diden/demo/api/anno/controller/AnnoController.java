package com.diden.demo.api.anno.controller;

import com.diden.demo.api.anno.dto.request.AnnoDtoRequest;
import com.diden.demo.api.anno.dto.response.AnnoDtoResponse;
import com.diden.demo.common.error.exception.BadRequestException;
import com.diden.demo.common.error.exception.DataNotProcessExceptions;
import com.diden.demo.common.response.HttpResponse;
import com.diden.demo.common.utils.LazyHolderObject;
import com.diden.demo.domain.anno.service.AnnoService;
import com.diden.demo.domain.anno.vo.request.AnnoVoRequest;
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
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/anno", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnoController {
  private final AnnoService annoService;

  /**
   * 공지사항 전체목록
   *
   * @param findId
   * @param limit
   * @return
   */
  @GetMapping
  public HttpResponse<Deque<AnnoDtoResponse>> findAll(
      final Integer findId, @NotNull(message = "출력할 데이터 갯수가 존재하지 않습니다.") final Integer limit) {
    final Deque<AnnoDtoResponse> annoVoList =
        annoService.findAll(findId, limit).stream()
            .map(AnnoDtoResponse::transformDataByAnnoVo)
            .collect(Collectors.toCollection(ArrayDeque::new));

    Integer nextSeq = null;
    if (annoVoList.size() > limit) {
      nextSeq = Integer.valueOf(annoVoList.pollLast().getAnnoId());
    }

    return HttpResponse.toPageResponse(HttpStatus.OK, "공지사항 목록을 호출합니다.", annoVoList, nextSeq);
  }

  /**
   * 공지사항 1건
   *
   * @param paramId path 공지사항 번호
   * @return HttpResponse.toResponse(HttpStatus.OK, "공지사항 1건 호출합니다.",
   *     annoService.findOne(newAnnoVo));
   */
  @GetMapping(value = "/{id}")
  public HttpResponse<AnnoDtoResponse> findOne(
      @PathVariable("id") @NotBlank(message = "공지사항 아이디가 존재하지 않습니다.") final String paramId) {
    if (!StringUtils.isNumeric(paramId)) {
      throw new BadRequestException("숫자만 입력할 수 있습니다.");
    }

    final AnnoDtoRequest annoDtoRequest = AnnoDtoRequest.builder().annoId(paramId).build();
    final AnnoVo findAnno = annoService.findOne(AnnoDtoRequest.transportDataByAnnoDtoRequest(annoDtoRequest));
    final AnnoDtoResponse annoDtoResponse = AnnoDtoResponse.transformDataByAnnoVo(findAnno);

    if (annoDtoResponse == null) {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 존재하지 않습니다.", null);
    } else {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항 1건 호출합니다.", annoDtoResponse);
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
          final AnnoDtoRequest annoDtoRequest) {
    // TEST
    final AnnoDtoRequest newAnnoDtoRequest =
            AnnoDtoRequest.builder()
            .annoId(annoDtoRequest.getAnnoId())
            .annoTitle(annoDtoRequest.getAnnoTitle())
            .annoContent(annoDtoRequest.getAnnoContent())
            .build();

    final int save = annoService.save(AnnoDtoRequest.transportDataByAnnoDtoRequest(newAnnoDtoRequest));

    if (save > 0) {
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
  public HttpResponse<AnnoDtoResponse> update(
      @RequestBody(required = false) @NotNull(message = "공지사항이 존재하지 않습니다.") @Valid final AnnoDtoRequest annoDtoRequest,
      @PathVariable(value = "id") @NotBlank(message = "공지사항 아이디가 존재하지 않습니다.") final String paramId) {
    if (!StringUtils.isNumeric(paramId)) {
      throw new BadRequestException("숫자만 입력할 수 있습니다.");
    }

    final AnnoDtoRequest newAnnoDtoRequest =
            AnnoDtoRequest.builder()
            .annoId(paramId)
            .annoTitle(annoDtoRequest.getAnnoTitle())
            .annoContent(annoDtoRequest.getAnnoContent())
            .build();

    final AnnoVoRequest annoVoRequest = AnnoDtoRequest.transportDataByAnnoDtoRequest(newAnnoDtoRequest);
    final int update = annoService.update(AnnoDtoRequest.transportDataByAnnoDtoRequest(newAnnoDtoRequest));

    if (update > 0) {
      return HttpResponse.toResponse(HttpStatus.OK, "공지사항이 수정되었습니다.", AnnoDtoResponse.transformDataByAnnoVo(annoService.findOne(annoVoRequest)));
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
