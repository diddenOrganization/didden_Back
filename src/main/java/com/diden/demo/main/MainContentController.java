package com.diden.demo.main;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainContentController {
    private final MainContentService mainContentService;

    public MainContentController(MainContentServiceImpl mainContentService) {
        this.mainContentService = mainContentService;
    }

    @GetMapping(value = "/main/content/images", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> imageAll(){
        try {
            return new ResponseEntity<>(new Gson().toJson(mainContentService.findMainContentImageAll()), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
