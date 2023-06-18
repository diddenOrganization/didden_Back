package com.diden.demo.api.file.controller;

import com.diden.demo.common.utils.ParsingFromURL;
import com.diden.demo.domain.file.service.FileService;
import com.diden.demo.domain.file.vo.FileVo;
import com.diden.demo.domain.tour.vo.TourItemVo;
import com.diden.demo.domain.tour.vo.TourVo;
import com.google.gson.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Hidden
@RestController
@Deprecated
public class FileController {
  private final FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping(value = "/file/image/put")
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

  @GetMapping(value = "/file/image/read")
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

  @GetMapping(value = "/file/image/list", produces = "application/json; charrset=UTF-8")
  public ResponseEntity<String> fileList() {

    JsonObject fileJsonObject = new JsonObject();
    JsonArray fileJsonArray = new JsonArray();

    FileVo fileVo = new FileVo();
    List<FileVo> fileVoList = fileService.fileList(fileVo);

    fileVoList.stream()
        .forEach(
            fileVoData -> {
              Gson gson = new Gson();
              JsonElement jsonFileVoData = new JsonParser().parse(gson.toJson(fileVoData));
              String encodedString =
                  Base64.getEncoder().encodeToString(fileVoData.getFileContent());
              fileVoData.setFileContent64(encodedString);

              jsonFileVoData
                  .getAsJsonObject()
                  .addProperty(
                      "fileUrl",
                      "data:image/"
                          + fileVoData.getFileExtension()
                          + ";base64,"
                          + fileVoData.getFileContent64());
              jsonFileVoData.getAsJsonObject().addProperty("fileContent", "");
              jsonFileVoData.getAsJsonObject().addProperty("fileContent64", "");
              fileJsonArray.add(jsonFileVoData);
            });
    fileJsonObject.add("data", fileJsonArray);
    return new ResponseEntity<String>(fileJsonObject.toString(), HttpStatus.OK);
  }

  @GetMapping(value = "/file/api/image/put")
  public void TourImgCall() {
    String url =
        "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A";
    ParsingFromURL parsingFromURL = new ParsingFromURL();
    String tourImgJson = parsingFromURL.getParsingURL(url);

    Gson gson = new Gson();
    TourVo myPushList = null;
    String jsonArray = tourImgJson;

    myPushList = gson.fromJson(jsonArray, TourVo.class);

    List<TourItemVo> list = myPushList.getTourBodyVo().getTourItemsVo().getListTourItemVo();
    TourImgFileProcess(list);
  }

  public void TourImgFileProcess(List<TourItemVo> list) {
    int max = 999999999;
    int min = 100000000;

    try {
      List<FileVo> listFileVo = new ArrayList<FileVo>();

      for (TourItemVo tourItemVo : list) {
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        int temp = (int) (Math.random() * (max - min + 1) + min);
        String fileId = "IMG" + dateParser.format(date) + Objects.toString(temp);

        File file =
            File.createTempFile(
                "image", FilenameUtils.getExtension(tourItemVo.getGalWebImageUrl()));
        file.deleteOnExit();

        URL url = new URL(tourItemVo.getGalWebImageUrl());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = (InputStream) conn.getContent();
        FileUtils.copyInputStreamToFile(inputStream, file);
        byte[] fileContent = FileUtils.readFileToByteArray(file);

        FileVo fileVo = new FileVo();

        fileVo.setFileId(fileId);
        fileVo.setFileContent(fileContent);
        fileVo.setFileSize(file.length());
        fileVo.setFileName(tourItemVo.getGalTitle());
        fileVo.setFileExtension(FilenameUtils.getExtension(tourItemVo.getGalWebImageUrl()));
        listFileVo.add(fileVo);
      }

      listFileVo.forEach(
          fileVo -> {
            fileService.fileInsert(fileVo);
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
