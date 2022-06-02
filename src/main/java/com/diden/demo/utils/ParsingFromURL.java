package com.diden.demo.utils;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MalformedJsonException이 발생한 이유는 gson에서 Json을 처리할 때 Json형식의 RFC 4627을 엄격하게 지키지
 * 않을 경우 발생합니다. Json data에 불필요한 값이나 띄어쓰기 있는 경우 발생할 수 있습니다.
 * 
 * Retrofit 찾아보기
 */
@ComponentScan
@Deprecated
public class ParsingFromURL {

    private final ParsingJson parsingJson = new ParsingJson();
    private final ParsingXml parsingXml = new ParsingXml();
    final static Logger logger = LoggerFactory.getLogger(ParsingFromURL.class);

    public String getParsingURL(String paramUrl) {
        try {
            URL url = new URL(paramUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            String ContentType = conn.getContentType();

            logger.info("Response code: {}", conn.getResponseCode());
            if (ContentType.indexOf("json") > 0) {
                return parsingJson.getParsingJsonFromURL(conn);
            } else if (ContentType.indexOf("xml") > 0) {
                return parsingXml.getParsingXmlFromURL(conn);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject userResult = new JsonObject();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return userResult.toString();
        }

    }
}
