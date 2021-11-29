package com.diden.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.diden.demo.vo.TourItemVo;
import com.diden.demo.vo.TourVo;
import com.diden.file.service.FileService;
import com.diden.file.service.impl.FileServiceImpl;
import com.diden.file.vo.FileVo;
import com.diden.utils.ParsingFromURL;
import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

    @Mock
    FileServiceImpl fileServiceImpl;

    @Mock
    FileService fileService;

    @Test
    public void 관광_이미지_호출() {
        String url = "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A";
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        String tourImgJson = parsingFromURL.getParsingURL(url);

        Gson gson = new Gson();
        TourVo myPushList = null;
        String jsonArray = tourImgJson;

        myPushList = gson.fromJson(jsonArray, TourVo.class);

        List<TourItemVo> list = myPushList.getTourBodyVo().getTourItemsVo().getListTourItemVo();
        관광_이미지_파일처리(list);
    }

    public void 관광_이미지_파일처리(List<TourItemVo> list) {
        int max = 999999999;
        int min = 100000000;

        try {
            List<FileVo> listFileVo = new ArrayList<FileVo>();

            for (TourItemVo tourItemVo : list) {
                SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
                Date date = new Date();
                int temp = (int) (Math.random() * (max - min + 1) + min);
                String fileId = "IMG" + dateParser.format(date) + Objects.toString(temp);

                File file = File.createTempFile("image", FilenameUtils.getExtension(tourItemVo.getGalWebImageUrl()));
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

            listFileVo.forEach(fileVo -> {
                System.out.println(fileVo.getFileExtension());
                fileService.fileInsert(fileVo);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
