package com.example.sunrise_sunset.sunrise_sunset;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Network {

    public JSONObject getJsonObj(String strUrl) throws ErrorHandler.ErrorMessage {

        InputStream in = null;
        String strJson = "";
        JSONObject jObj = null;

        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
                urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            strJson = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jObj = new JSONObject(strJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }
}
