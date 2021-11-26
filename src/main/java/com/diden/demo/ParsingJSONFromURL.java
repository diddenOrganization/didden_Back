package com.diden.demo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParsingJSONFromURL {
    public String getParsingJSONFromURL(String paramUrl) {
        try {
            String sURL = paramUrl; // just
            // a
            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); // from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); // Convert the input
                                                                                                    // stream to a json
                                                                                                    // element
            JsonObject rootobj = new JsonObject();
            rootobj = root.getAsJsonObject(); // May be an array, may be an object.
            rootobj.addProperty("result", true);
            return rootobj.toString();
        } catch (Exception e) {
            JsonObject userResult = new JsonObject();
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return userResult.toString();
        }

    }
}
