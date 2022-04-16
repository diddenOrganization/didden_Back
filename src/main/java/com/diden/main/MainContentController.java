package com.diden.main;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainContentController {
    @Autowired
    MainContentService mainContentService;

    Gson gson = new Gson();

    @GetMapping(value = "/main/content/images", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> imageAll(){
        try {
            return new ResponseEntity<>(gson.toJson(mainContentService.findMainContentImageAll()), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
