package com.diden.demo.anno;

import com.diden.demo.config.LazyHolderObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/anno", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnoController {
  private final AnnoService annoService;

  /**
   * 공지사항 목록
   *
   * @return type = Array, Json, String
   */
  @GetMapping
  public ResponseEntity<String> findAll() {
    return ResponseEntity.ok(LazyHolderObject.getGson().toJson(annoService.findAll()));
  }

  /**
   * 공지사항 목록 (1 건)
   *
   * @param paramId 공지사항 페이지 번호, String
   * @return type = Json, String
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<String> findOne(@PathVariable("id") String paramId) {
    final AnnoVo newAnnoVo = AnnoVo.builder().annoId(paramId).build();

    return ResponseEntity.ok(LazyHolderObject.getGson().toJson(annoService.findOne(newAnnoVo)));
  }

  /**
   * 공지사항 등록
   *
   * @param annoVo 공지사항 Vo, AnnoVo.java
   * @return 등록된 공지사항 내용 조회 (1건), Json, String
   */
  @PostMapping
  public ResponseEntity<String> save(@RequestBody(required = false) AnnoVo annoVo) {
    annoService.save(annoVo);
    return ResponseEntity.noContent().build();
  }

  /**
   * 공지사항 수정
   *
   * @param annoVo 공지사항 Vo, AnnoVo.java
   * @param paramId 공지사항 페이지 번호, String
   * @return 수정된 공지사항 내용 조회 (1건), Json, String
   */
  @PutMapping(value = "/id}")
  public ResponseEntity<String> update(
      @RequestBody(required = false) AnnoVo annoVo, @PathVariable(value = "id") String paramId) {
    final AnnoVo newAnnoVo =
        AnnoVo.builder()
            .annoId(paramId)
            .annoTitle(annoVo.getAnnoTitle())
            .annoContent(annoVo.getAnnoContent())
            .build();
    annoService.update(newAnnoVo);
    return ResponseEntity.ok(LazyHolderObject.getGson().toJson(annoService.findOne(newAnnoVo)));
  }

  /**
   * 공지사항 삭제
   *
   * @param paramId 공지사항 페이지 번호, String
   * @return 공지사항 삭제, 공백 값 반환
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") String paramId) {
    annoService.delete(paramId);
    return ResponseEntity.noContent().build();
  }
}
