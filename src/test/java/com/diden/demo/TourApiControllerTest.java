package com.diden.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.diden.utils.ParsingFromURL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.Test;

public class TourApiControllerTest {
    @Test
    public void 관광정보_이미지() {
        String url = new String(
                "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A");
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        System.out.println(parsingFromURL.getParsingURL(url));
    }

    @Test
    public void 관광공사_얘제소스() throws UnsupportedEncodingException, IOException {
        // XmlMapper xml = new XmlMapper();

        try {
            URL url = new URL(
                    "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println(conn.getResponseCode());
            System.out.println(conn.getContentType());
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader((InputStream) conn.getContent(), "UTF-8"));
            String result = bf.readLine();

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(result.getBytes());

            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writeValueAsString(node);
            System.out.println(json);
            // Gson gson = new Gson();
            // JsonElement jsonElement = new JsonParser().parse(gson.toJson(json));
            // JsonObject jsonObject = new JsonObject();
            // jsonObject.addProperty("data", json);
            // System.out.println(jsonObject);
            // JsonObject jsonObject
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void 외국포럼_xml_to_json() throws UnsupportedEncodingException, IOException {
        URL url = new URL(
                "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A");

        InputStream inputStream = url.openStream();
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        // OutputStream outputstream = out.getOutputPayload().getOutputStream();
        byte[] b = null;
        b = new byte[inputStream.available()];
        String inputContent = new String(b);
        String sourcexml = inputContent;
        String line = null;

        while ((line = bf.readLine()) != null) {
            sourcexml += line;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonPrettyPrintString = gson.toJson(sourcexml);
        System.out.println(jsonPrettyPrintString);
    }
}
