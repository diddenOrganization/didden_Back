package com.diden.demo;

import com.diden.demo.main.MainContentService;
import com.diden.demo.main.MainContentVo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@Transactional
class MainContentServiceImplTest {

    @Autowired
    MainContentService mainContentService;

    @Test
    public void 여행_이미지_조회() throws Exception {
        //given
        List<MainContentVo> imageAll = mainContentService.findMainContentImageAll();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        //when
        jsonObject.addProperty("data", gson.toJson(imageAll));
        jsonObject.addProperty("count", imageAll.size());

        //then
        System.out.println(jsonObject.get("count"));
    }

    @Test
    void 디렉토리_안_파일_읽기() {
        try {
            File file = new File("/Users/ohjeung/Documents/사업계획/tour_image");
            System.out.println(Arrays.toString(file.listFiles()));
            for (int cnt = 0; cnt < Objects.requireNonNull(file.listFiles()).length; cnt++) {
                System.out.println(Objects.requireNonNull(file.listFiles())[cnt].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 여행_이미지_처리() {
        int max = 999999999;
        int min = 100000000;

        File imgDir = new File("/Users/ohjeung/Documents/사업계획/tour_image");
        List<String> listString = new ArrayList<>();

        for (int cnt = 0; cnt < Objects.requireNonNull(imgDir.listFiles()).length; cnt++) {
            listString.add(Objects.requireNonNull(imgDir.listFiles())[cnt].toString());
        }
        try {
            for (String fileLocation : listString) {
                SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
                int temp = (int) (Math.random() * (max - min + 1) + min);
                String contentId = "IMG" + dateParser.format(new Date()) + Objects.toString(temp);

                File imgLocation = new File(fileLocation);
                byte[] fileContent = FileUtils.readFileToByteArray(imgLocation);

                MainContentVo mainContentVo = new MainContentVo(
                        contentId
                        , ""
                        , Long.valueOf(imgLocation.length())
                        , ""
                        , ""
                        , fileContent
                        , ""
                        , FilenameUtils.getExtension(imgLocation.getName())
                        , "");
                mainContentService.saveMainContentImages(mainContentVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}