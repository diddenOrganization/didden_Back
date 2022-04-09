package com.diden.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class ParsingJson {
    public String getParsingJsonFromURL(HttpURLConnection conn) {
        JsonParser jp = new JsonParser();
        JsonElement root;

        try {
            root = jp.parse(new InputStreamReader(conn.getInputStream()));
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
            JsonObject parsingResult = new JsonObject();
            parsingResult.addProperty("result", false);
            parsingResult.addProperty("error", e.getMessage());
            return parsingResult.toString();
        }

        JsonObject rootobj = new JsonObject();
        rootobj = root.getAsJsonObject();
        rootobj.addProperty("result", true);
        return rootobj.toString();
    }

    public JsonObject stringToJsonObject(String stringJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = new JsonObject();
        JsonElement jsonElement;

        jsonElement = jsonParser.parse(stringJson);
        jsonObject = jsonElement.getAsJsonObject();

        return jsonObject;
    }
}
