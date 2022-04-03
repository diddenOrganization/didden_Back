package com.diden.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnoController {
    @Autowired
    AnnoService annoService;

    @GetMapping(value = "/anno", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> findAll() {
        try {
            return new ResponseEntity<>(annoService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> findOne(@RequestBody(required = false) AnnoVo annoVo, @PathVariable(value="id") String paramId) {
        try{
            annoVo.setAnnoId(paramId);
            return new ResponseEntity<>(annoService.findOne(annoVo), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/anno", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody(required = false) AnnoVo annoVo) {
        try{
            annoService.save(annoVo);
            return new ResponseEntity<>(annoService.findOne(annoVo), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody(required = false) AnnoVo annoVo, @PathVariable(value="id") String paramId) {
        try{
            annoVo.setAnnoId(paramId);
            annoService.update(annoVo);
            return new ResponseEntity<>(annoService.findOne(annoVo), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/anno/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestBody(required = false) AnnoVo annoVo, @PathVariable(value="id") String paramId) {
        try{
            annoVo.setAnnoId(paramId);
            annoService.delete(annoVo);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
