package com.diden.anno;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnoController {
    private final AnnoService annoService;

    public AnnoController(AnnoServiceImpl annoService) {
        this.annoService = annoService;
    }

    /**
     *
     * 공지사항 목록
     * @return type = Array, Json, String
     */
    @GetMapping(value = "/anno", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> findAll() {
        try {
            return new ResponseEntity<>(new Gson().toJson(annoService.findAll()), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 공지사항 목록 (1 건)
     * @param annoVo 공지사항 Vo, AnnoVo.java
     * @param paramId 공지사항 페이지 번호, String
     * @return type = Json, String
     */
    @GetMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> findOne(@RequestBody(required = false) AnnoVo annoVo, @PathVariable("id") String paramId) {
        try{
            return new ResponseEntity<>(new Gson().toJson(annoService.findOne(annoVo)), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 공지사항 등록
     * @param annoVo 공지사항 Vo, AnnoVo.java
     * @return 등록된 공지사항 내용 조회 (1건), Json, String
     */
    @PutMapping(value = "/anno", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody(required = false) AnnoVo annoVo) {
        try{
            annoService.save(annoVo);
            return new ResponseEntity<>(new Gson().toJson(annoService.findOne(annoVo)), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 공지사항 수정
     * @param annoVo 공지사항 Vo, AnnoVo.java
     * @param paramId 공지사항 페이지 번호, String
     * @return 수정된 공지사항 내용 조회 (1건), Json, String
     */
    @PutMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody(required = false) AnnoVo annoVo, @PathVariable(value="id") String paramId) {
        try{
            annoVo.setAnnoId(paramId);
            annoService.update(annoVo);
            return new ResponseEntity<>(new Gson().toJson(annoService.findOne(annoVo)), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 공지사항 삭제
     * @param paramId 공지사항 페이지 번호, String
     * @return 공지사항 삭제, 공백 값 반환
     */
    @DeleteMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable(value="id") String paramId) {
        try{
            AnnoVo annoVo = new AnnoVo(paramId, null, null, null);
            annoService.delete(annoVo);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
