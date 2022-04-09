package com.diden.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class ParsingXml {
    public String getParsingXmlFromURL(HttpURLConnection conn) {
        try {

            InputStreamReader inputStreamReader = new InputStreamReader((InputStream) conn.getContent(), "UTF-8");
            Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
            String streamToString = streamOfString.collect(Collectors.joining());

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(streamToString.getBytes());

            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(node);
        } catch (IOException e) {
            e.printStackTrace();
            JsonObject parsingResult = new JsonObject();
            parsingResult.addProperty("result", false);
            parsingResult.addProperty("error", e.getMessage());
            return parsingResult.toString();
        }
    }
}
