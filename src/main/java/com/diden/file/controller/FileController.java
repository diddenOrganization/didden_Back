package com.diden.file.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.diden.file.service.FileService;
import com.diden.file.vo.FileVo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping(value = "/file/test")
    public void fileInsert() {
        FileVo fileVo = new FileVo();
        int max = 999999999;
        int min = 100000000;

        File imgPath = new File("/Users/ohjeung/project/demo/src/main/resources/static/img/image.jpg");
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        int temp = (int) (Math.random() * (max - min + 1) + min);
        String fileId = "IMG" + dateParser.format(date) + Objects.toString(temp);

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(imgPath);

            fileVo.setFileId(fileId);
            fileVo.setFileContent(fileContent);
            fileVo.setFileSize(imgPath.length());
            fileVo.setFileName(imgPath.getName());
            fileVo.setFileExtension(FilenameUtils.getExtension(imgPath.getName()));
            fileService.fileInsert(fileVo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("확인");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("확인");
        }
    }

    @GetMapping(value = "/file/read")
    public void fileRead() {
        FileVo fileVo = new FileVo();
        fileVo = fileService.fileRead(fileVo);

        String encodedString = Base64.getEncoder().encodeToString(fileVo.getFileContent());
        fileVo.setFileContent64(encodedString);

        String src = "data:image/" + fileVo.getFileExtension() + ";base64," + fileVo.getFileContent64();

        fileVo.setFileContent64(src);

        System.out.println(fileVo.getFileId());
        System.out.println(fileVo.getFileName());
        System.out.println(fileVo.getFileCreateDate());
        System.out.println(fileVo.getFileUpdateDate());
        System.out.println(fileVo.getFileSize());
        System.out.println(fileVo.getFileExtension());
        System.out.println(fileVo.getFileContent64());
    }

    @GetMapping(value = "/file/list", produces = "application/json; charrset=UTF-8")
    public ResponseEntity<String> fileList() {

        JsonObject fileJsonObject = new JsonObject();
        JsonArray fileJsonArray = new JsonArray();

        FileVo fileVo = new FileVo();
        List<FileVo> fileVoList = fileService.fileList(fileVo);

        fileVoList.stream().forEach(fileVoData -> {
            Gson gson = new Gson();
            JsonElement jsonFileVoData = new JsonParser().parse(gson.toJson(fileVoData));
            String encodedString = Base64.getEncoder().encodeToString(fileVoData.getFileContent());
            fileVoData.setFileContent64(encodedString);

            jsonFileVoData.getAsJsonObject().addProperty("fileUrl",
                    "data:image/" + fileVoData.getFileExtension() + ";base64," + fileVoData.getFileContent64());
            jsonFileVoData.getAsJsonObject().addProperty("fileContent", "");
            jsonFileVoData.getAsJsonObject().addProperty("fileContent64", "");
            fileJsonArray.add(jsonFileVoData);
        });
        fileJsonObject.add("data", fileJsonArray);
        return new ResponseEntity<String>(fileJsonObject.toString(), HttpStatus.OK);
    }

}
